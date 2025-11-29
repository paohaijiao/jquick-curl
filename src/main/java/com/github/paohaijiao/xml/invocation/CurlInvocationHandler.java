/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.xml.invocation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.enums.JLogLevel;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.executor.JQuickCurlExecutor;
import com.github.paohaijiao.factory.JCurlResultFactory;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.type.JTypeReference;
import com.github.paohaijiao.xml.method.CurlMethod;
import com.github.paohaijiao.xml.namespace.CurlNamespace;
import com.github.paohaijiao.xml.param.Param;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.xml.invocation
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/27
 */
@Slf4j
public class CurlInvocationHandler implements InvocationHandler {

    private static JConsole console=new JConsole();

    private final CurlNamespace namespace;

    public CurlInvocationHandler(CurlNamespace namespace) {
        this.namespace = namespace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }
        String methodName = method.getName();
        CurlMethod curlMethod = namespace.getMethods().get(methodName);
        JAssert.notNull(curlMethod,"No curl configuration found for method: " + methodName);
        Map<String, Object> paramMap = buildParamMap(method, args);
        JContext context = new JContext();
        context.putAll(paramMap);
        return executeCurl(curlMethod, context, method);
    }

    private Map<String, Object> buildParamMap(Method method, Object[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameters.length; i++) {
            Object argValue = args[i];
            boolean hasParamAnnotation = false;
            for (Annotation annotation : parameterAnnotations[i]) {// 检查 @Param 注解
                if (annotation instanceof Param) {
                    Param paramAnnotation = (Param) annotation;
                    paramMap.put(paramAnnotation.value(), argValue);
                    hasParamAnnotation = true;
                    break;
                }
            }
            if (!hasParamAnnotation) {
                paramMap.put(parameters[i].getName(), argValue);
            }
        }

        return paramMap;
    }

    private Object executeCurl(CurlMethod curlMethod, JContext context,  Method method) {
        String finalCurlCommand = curlMethod.getCurlCommand();
        JQuickCurlConfig config=JQuickCurlConfig.getInstance();
        JQuickCurlExecutor executor = new JQuickCurlExecutor(context,config);
        executor.addErrorListener(error -> {
            String message=String.format("Failed: Line %d:%d - %s%n",  error.getLine(), error.getCharPosition(), error.getMessage());
            console.log(JLogLevel.ERROR,message);
        });
        try {
            JResult rawResult = executor.execute(finalCurlCommand);
            log.info("result:{}",rawResult);
            Class<?> returnType = method.getReturnType();
            Type genericReturnType = method.getGenericReturnType();
            if (returnType.equals(Void.TYPE) || returnType.equals(java.lang.Void.class)) {
                return null;
            }
            return convertResult(rawResult, genericReturnType, returnType);
        } catch (JAntlrExecutionException e) {
            console.error("Curl execution error", e);
            e.getErrors().forEach(err ->{
                console.info(" - " + err.getMessage());
            });
        }
        return null;
    }
    /**
     * 使用 JCurlResultFactory 转换结果，支持泛型
     */
    private Object convertResult(JResult rawResult, Type genericReturnType, Class<?> returnType) {
        try {
            JTypeReference<?> typeReference = createTypeReference(genericReturnType);
            return JCurlResultFactory.convertResponse(rawResult, typeReference);
        } catch (Exception e) {
            log.warn("Failed to convert result using JCurlResultFactory, fallback to basic conversion", e);
            return fallbackConvertResult(rawResult, returnType);
        }
    }
    private JTypeReference<?> createTypeReference(Type genericType) {
        return new JTypeReference<Object>() {
            @Override
            public Type getType() {
                return genericType;
            }
        };
    }
    private Object fallbackConvertResult(JResult rawResult, Class<?> returnType) {
        String responseContent = extractResponseContent(rawResult);
        if (returnType == String.class) {
            return responseContent;
        } else if (returnType == Boolean.class || returnType == boolean.class) {
            return Boolean.parseBoolean(responseContent.trim());
        } else if (returnType == Integer.class || returnType == int.class) {
            return Integer.parseInt(responseContent.trim());
        } else if (returnType == Long.class || returnType == long.class) {
            return Long.parseLong(responseContent.trim());
        } else if (returnType == Double.class || returnType == double.class) {
            return Double.parseDouble(responseContent.trim());
        } else if (returnType == Float.class || returnType == float.class) {
            return Float.parseFloat(responseContent.trim());
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(responseContent, returnType);
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse response to type: " + returnType, e);
            }
        }
    }
    private String extractResponseContent(JResult rawResult) {
        if (rawResult == null) {
            return "";
        }
        try {
            Method getContentMethod = rawResult.getClass().getMethod("getContent");
            Object content = getContentMethod.invoke(rawResult);
            return content != null ? content.toString() : "";
        } catch (Exception e) {
            log.debug("JResult doesn't have getContent method, trying toString");
        }
        return rawResult.toString();
    }
}