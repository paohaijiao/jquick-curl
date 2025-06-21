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
package com.github.paohaijiao.support;
import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.config.JQuickCurlConfig;
import okhttp3.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.paohaijiao.javelin.support
 *
 * @author Martin
 * @version 1.0.0
 * @className CurlCommandProcessor
 * @date 2025/6/21
 * @description
 */
public class CurlCommandProcessor {
    private final OkHttpClient client;
    private final Map<Class<?>, Object> contextObjects;

    public CurlCommandProcessor() {
        this(JQuickCurlConfig.getInstance().createClient());
    }

    public CurlCommandProcessor(OkHttpClient client) {
        this.client = client;
        this.contextObjects = new HashMap<>();
    }
    public void registerContextObject(Class<?> clazz, Object object) {
        contextObjects.put(clazz, object);
    }
    public Object process(Object instance, Method method, Object[] args) throws Exception {
        JCurlCommand annotation = method.getAnnotation(JCurlCommand.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Method must be annotated with @CurlCommand");
        }
        String curlCommand = resolveCurlCommand(annotation.value(), method, args);
        Request request = parseCurlCommand(curlCommand);
        if (!annotation.execute()) {
            return request;
        }

        try (Response response = client.newCall(request).execute()) {
            validateStatusCode(annotation, response);
            return handleResponse(instance, method, response, annotation);
        }
    }
    private String resolveCurlCommand(String template, Method method, Object[] args) {
        if (args == null || args.length == 0) {
            return template;
        }
        Parameter[] parameters = method.getParameters();
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            params.put(parameters[i].getName(), args[i]);
        }
        String result = template;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            result = result.replace(placeholder, entry.getValue().toString());
        }
        return result;
    }
    private Request parseCurlCommand(String curlCommand) {
        curlCommand = curlCommand.trim().replaceAll("\\s+", " ");
//        CurlLexer lexer = new CurlLexer(CharStreams.fromString(curlCommand));
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        CurlParser parser = new CurlParser(tokens);
//        ParseTree tree = parser.curlCommand();
//        // 使用访问者模式转换为 OkHttp 请求
//        CurlToOkHttpVisitor visitor = new CurlToOkHttpVisitor();
//        return visitor.visit(tree);
        return null;
    }
    private void validateStatusCode(JCurlCommand annotation, Response response) {
        if (annotation.expectedStatus() > 0 &&
                response.code() != annotation.expectedStatus()) {
            throw new AssertionError(String.format(
                    "Expected HTTP status %d but was %d for URL: %s",
                    annotation.expectedStatus(),
                    response.code(),
                    response.request().url()
            ));
        }
    }
    private Object handleResponse(Object instance, Method method, Response response,
                                  JCurlCommand annotation) throws Exception {
        if (method.getReturnType() == Response.class) {
            return response;
        }

        if (method.getReturnType() == String.class) {
            ResponseBody body = response.body();
            return body != null ? body.string() : null;
        }
        injectResponseToParameters(instance, method, response);
        if (!annotation.validationScript().isEmpty()) {
            //validateWithScript(annotation.validationScript(), response);
        }

        return null;
    }
    private void injectResponseToParameters(Object instance, Method method,
                                            Response response) throws Exception {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].getType();
            if (type == Response.class) {
                args[i] = response;
            } else if (type == Request.class) {
                args[i] = response.request();
            } else if (type == String.class) {
                ResponseBody body = response.body();
                args[i] = body != null ? body.string() : null;
            } else {
                args[i] = null;
            }
        }
        method.invoke(instance, args);
    }
    public static void processClass(Class<?> clazz) throws Exception {
        OkHttpClient client = JQuickCurlConfig.getInstance().createClient();
        CurlCommandProcessor processor = new CurlCommandProcessor(client);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(JCurlCommand.class)) {
                processor.process(instance, method, null);
            }
        }
    }
}
