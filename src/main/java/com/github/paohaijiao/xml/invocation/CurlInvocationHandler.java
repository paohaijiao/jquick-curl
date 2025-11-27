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
import com.github.paohaijiao.xml.method.CurlMethod;
import com.github.paohaijiao.xml.namespace.CurlNamespace;
import com.github.paohaijiao.xml.param.Param;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.xml.invocation
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/27
 */
public class CurlInvocationHandler implements InvocationHandler {

    private final CurlNamespace namespace;

    public CurlInvocationHandler(CurlNamespace namespace) {
        this.namespace = namespace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        CurlMethod curlMethod = namespace.getMethods().get(methodName);
        if (curlMethod == null) {
            throw new RuntimeException("No curl configuration found for method: " + methodName);
        }
        Map<String, Object> paramMap = buildParamMap(method, args);// 构建参数映射
        return executeCurl(curlMethod, paramMap, method.getReturnType()); // 执行 curl 命令
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
            if (!hasParamAnnotation) {// 如果没有 @Param 注解，使用参数名
                paramMap.put(parameters[i].getName(), argValue);
            }
        }

        return paramMap;
    }

    private Object executeCurl(CurlMethod curlMethod, Map<String, Object> paramMap, Class<?> returnType) {
        String finalCurlCommand = replacePlaceholders(curlMethod.getCurlCommand(), paramMap);
        String response = executeCurlCommand(finalCurlCommand);
        return parseResponse(response, returnType);
    }

    private String replacePlaceholders(String curlCommand, Map<String, Object> paramMap) {
        String result = curlCommand;
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            if (result.contains(placeholder)) {
                result = result.replace(placeholder, escapeForShell(String.valueOf(entry.getValue())));
            }
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            if (result.contains(placeholder)) {
                result = result.replace(placeholder, escapeForShell(String.valueOf(entry.getValue())));
            }
        }

        return result;
    }

    private String escapeForShell(String value) {
        return value.replace("'", "'\\''")
                .replace("\"", "\\\"")
                .replace("`", "\\`")
                .replace("$", "\\$")
                .replace("&", "\\&")
                .replace("|", "\\|")
                .replace(";", "\\;")
                .replace("<", "\\<")
                .replace(">", "\\>")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace(" ", "\\ ");
    }

    private String executeCurlCommand(String curlCommand) {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", curlCommand});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Curl command failed with exit code: " + exitCode);
            }
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute curl command: " + curlCommand, e);
        }
    }

    private Object parseResponse(String response, Class<?> returnType) {
        if (returnType == String.class) {
            return response;
        } else if (returnType == Boolean.class || returnType == boolean.class) {
            return Boolean.parseBoolean(response.trim());
        } else if (returnType == Integer.class || returnType == int.class) {
            return Integer.parseInt(response.trim());
        } else if (returnType == Long.class || returnType == long.class) {
            return Long.parseLong(response.trim());
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(response, returnType);
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse response to type: " + returnType, e);
            }
        }
    }
}