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
package com.github.paohaijiao.handler;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.anno.JTimeout;
import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.support.JCurlCommandProcessor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * packageName com.github.paohaijiao.handler
 *
 * @author Martin
 * @version 1.0.0
 * @className JCurlCommandInvocationHandler
 * @date 2025/6/23
 * @description
 */
@Slf4j
public class JCurlCommandInvocationHandler implements InvocationHandler {

    private JContext context=new JContext();

    private JQuickCurlConfig config=JQuickCurlConfig.getInstance();

    public JCurlCommandInvocationHandler() {
        this.context =new JContext();
        this.config =JQuickCurlConfig.getInstance();
    }
    public JCurlCommandInvocationHandler(JQuickCurlConfig config) {
        this.context =new JContext();
        this.config =config;
    }

    public JCurlCommandInvocationHandler(JContext context) {
        this.context =context;
        this.config =JQuickCurlConfig.getInstance();
    }
    public JCurlCommandInvocationHandler(JContext context, JQuickCurlConfig config) {
        this.context =context;
        this.config =config;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        JCurlCommand curlCommand = method.getAnnotation(JCurlCommand.class);
        if (curlCommand == null || !curlCommand.execute()) {
            throw new UnsupportedOperationException("Method is not annotated with @JCurlCommand");
        }
        log.info("the current command is {}", curlCommand.value());
        System.out.println( curlCommand.value());
        for (int i = 0; i < args.length; i++) {
            Object object = args[i];
            if (object instanceof JQuickCurlReq) {
                JQuickCurlReq req = (JQuickCurlReq) object;
                for(String key:req.keySet()){
                    context.put(key,req.get(key));
                }
            }
        }
        JTimeout timeout = method.getAnnotation(JTimeout.class);
        if (timeout != null&&null!=this.config ) {
            this.config.setReadTimeout(timeout.read());
            this.config.setConnectTimeout(timeout.connect());
            this.config.setWriteTimeout(timeout.write());
        }
        Class<?> returnType = method.getReturnType();
        JCurlCommandProcessor curl=  new JCurlCommandProcessor(context,config);
        Object result = curl.processMethod(null, method, args, returnType);
        Object typedResult = returnType.cast(result);
        return typedResult;
    }
    public static <T> T createProxy(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new JCurlCommandInvocationHandler());
    }
}
