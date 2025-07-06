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
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.executor.JQuickCurlExecutor;
import com.github.paohaijiao.factory.JCurlResultFactory;
import com.github.paohaijiao.generic.JGenericTypeReference;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@Slf4j
public class JCurlCommandProcessor {

    private JContext context=new JContext();

    private JQuickCurlConfig config=JQuickCurlConfig.getInstance();

    public JCurlCommandProcessor() {
        this.context =new JContext();
        this.config =JQuickCurlConfig.getInstance();
    }

    public JCurlCommandProcessor(JQuickCurlConfig config) {
        this.context =new JContext();
        this.config =config;
    }

    public JCurlCommandProcessor(JContext context) {
        this.context =context;
        this.config =JQuickCurlConfig.getInstance();
    }
    public JCurlCommandProcessor(JContext context, JQuickCurlConfig config) {
        this.context =context;
        this.config =config;
    }
    public <T> T process(Object object, Method method, Object[] args,Class<T> interfaceClass) throws Exception {
        JCurlCommand annotation = method.getAnnotation(JCurlCommand.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Method must be annotated with @CurlCommand");
        }
        String curlCommand = resolveCurlCommand(annotation.value(), method, args);
        buildContext(args);
        JQuickCurlExecutor executor = new JQuickCurlExecutor(this.context,this.config);
        executor.addErrorListener(error -> {System.err.printf("Failed: Line %d:%d - %s%n", error.getLine(), error.getCharPosition(), error.getMessage());System.err.println("规则栈: " + error.getRuleStack());});
        try {
            JResult rawResult = executor.execute(curlCommand);
            log.info("result:{}",rawResult);
            if (interfaceClass.equals(Void.TYPE)) {
                return null;
            }else{
                try {
                    Type genericReturnType = method.getGenericReturnType();
                    JGenericTypeReference<T> typeReference = new JGenericTypeReference<T>() {
                        @Override
                        public Type getType() {
                            return genericReturnType;
                        }
                    };
                    T obj= JCurlResultFactory.convertResponse(rawResult, typeReference);
                   return  obj;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        } catch (JAntlrExecutionException e) {
            System.err.println("Parse Fail: " + e.getMessage());
            e.getErrors().forEach(err -> System.err.println(" - " + err.getMessage()));
        }
        return null;
    }
    private String resolveCurlCommand(String cmd, Method method, Object[] args) {
        if (args == null || args.length == 0) {
            return cmd;
        }
        Parameter[] parameters = method.getParameters();
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            params.put(parameters[i].getName(), args[i]);
        }
        String result = cmd;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            result = result.replace(placeholder, entry.getValue().toString());
        }
        return result;
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
    private <T> T handleResponse(Object instance, Response response, JCurlCommand annotation,Class<T> interfaceClass) throws Exception {
      return null;
    }
    private void injectResponseToParameters(Object instance, Method method, Response response) throws Exception {
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
     public <T> List<T> processClass(Class<?> clazz, Class<T> interfaceClass) throws Exception {
        List<T> list= new ArrayList<>();
        JCurlCommandProcessor processor = new JCurlCommandProcessor(this.context,this.config);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        for (Method method : clazz.getMethods()) {
            list.add(processor.process(instance, method, null,interfaceClass));
        }
        return list;
    }
    public <T> T processMethod(Object instance,Method method, Object[] args,Class<T> interfaceClass) throws Exception {
        JCurlCommandProcessor processor = new JCurlCommandProcessor(this.context,this.config);
        if (method.isAnnotationPresent(JCurlCommand.class)) {
            T object= processor.process(instance, method, args,interfaceClass);
            return object;
        }
        return null;
    }
    public  <T> T processMethod(Object instance,Method method, Object arg,Class<T> interfaceClass) throws Exception {
        JCurlCommandProcessor processor = new JCurlCommandProcessor(this.context,this.config);
        Object[] args = new Object[]{arg};
        if (method.isAnnotationPresent(JCurlCommand.class)) {
            T object= processor.process(instance, method, args,interfaceClass);
            return object;
        }
        return null;
    }
    private void buildContext(Object[] args){
        if (args == null || args.length == 0) {
            return;
        }
        for (Object arg : args) {
            if(arg instanceof HashMap){
                HashMap<String,?> map= (HashMap)arg;
                for(String key: map.keySet()){
                    this.context.put(key,map.get(key));
                }

            }
        }
    }
}
