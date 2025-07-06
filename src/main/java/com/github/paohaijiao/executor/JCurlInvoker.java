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
package com.github.paohaijiao.executor;

import com.github.paohaijiao.factory.JMethodReferenceStrategy;
import com.github.paohaijiao.function.JFunction;
import com.github.paohaijiao.handler.JCurlCommandInvocationHandler;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.support.JCurlCommandProcessor;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.param.JContext;


import java.lang.reflect.Method;
/**
 * packageName com.github.paohaijiao.executor
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/6
 */
public class JCurlInvoker {

    public static <T> T createProxy(Class<T> interfaceClass) {
        return JCurlCommandInvocationHandler.createProxy(interfaceClass);
    }

    /**
     * 执行方法引用
     */
    public static Object invoke(JFunction<JQuickCurlReq, JResult> methodRef, JQuickCurlReq req) throws Exception {
        return invoke(methodRef, req, new JContext(), JQuickCurlConfig.getInstance());
    }

    /**
     * 执行方法引用（带上下文）
     */
    public static Object invoke(JFunction<JQuickCurlReq, JResult> methodRef,
                                JQuickCurlReq req, JContext context) throws Exception {
        return invoke(methodRef, req, context, JQuickCurlConfig.getInstance());
    }

    /**
     * 执行方法引用（完整配置）
     */
    public static Object invoke(JFunction<JQuickCurlReq, JResult> methodRef,
                                JQuickCurlReq req, JContext context, JQuickCurlConfig config) throws Exception {
        JMethodReferenceStrategy wrapper = new JMethodReferenceStrategy();
        Method method = wrapper.getMethod(methodRef);
        return new JCurlCommandProcessor(context, config).processMethod(null, method, req);
    }
}
