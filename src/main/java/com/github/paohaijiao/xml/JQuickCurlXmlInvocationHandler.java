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
package com.github.paohaijiao.xml;

import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.convert.JQuickCurlResponseConvert;
import com.github.paohaijiao.enums.JLogLevel;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.executor.JQuickCurlExecutor;
import com.github.paohaijiao.factory.JCurlResultFactory;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.responseBody.JQuickCurlResponseBody;
import com.github.paohaijiao.transformer.JQuickValueTransformer;
import com.github.paohaijiao.transformer.type.JQuickJavaTypeReference;
import com.github.paohaijiao.type.JTypeReference;
import com.github.paohaijiao.xml.invocation.JQuickXmlInvocationHandler;
import com.google.protobuf.ByteString;
import okhttp3.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * packageName com.github.paohaijiao.xml
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/11
 */
public class JQuickCurlXmlInvocationHandler extends JQuickXmlInvocationHandler {

    private JConsole console=new JConsole();

    @Override
    protected Object loadResult(String curlCommand, JContext context, Method method, Object[] args) {
        JQuickCurlConfig config=JQuickCurlConfig.getInstance();
        JQuickCurlExecutor executor = new JQuickCurlExecutor(context,config);
        executor.addErrorListener(error -> {String message=String.format("Failed: Line %d:%d - %s%n",  error.getLine(), error.getCharPosition(), error.getMessage());
            console.log(JLogLevel.ERROR,message);
        });
        try {
            String curlString=replaceVariables(curlCommand,context);
            console.log(JLogLevel.INFO,"Merged curl command:"+ curlString);
            JQuickCurlResponseBody raw = executor.execute(curlString);
            console.info("result:"+raw);
            JQuickCurlResponseConvert convert=new JQuickCurlResponseConvert();
            return convert.convertResponse(raw,method);
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
            console.error("Failed to convert result using JCurlResultFactory, fallback to basic conversion", e);
        }
        return null;
    }
}
