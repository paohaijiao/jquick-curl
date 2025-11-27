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
package com.github.paohaijiao.factory;

import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.domain.resp.JQuickCurlResp;
import com.github.paohaijiao.function.JFunction;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.resolve.JMethodReferenceResolver;
import com.github.paohaijiao.support.JCurlCommandProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JMethodReferenceStrategy  {
    private static JConsole console = new JConsole();
    private JContext context=new JContext();
    private JQuickCurlConfig config=JQuickCurlConfig.getInstance();
    public JMethodReferenceStrategy(JContext context){
        this.context = context;
        this.config =  JQuickCurlConfig.getInstance();
    }
    public JMethodReferenceStrategy(JQuickCurlConfig config){
        this.context = context;
        this.config =  config;
    }
    public JMethodReferenceStrategy(JContext context,JQuickCurlConfig config){
        this.context = context;
        this.config =  config;
    }
    public JMethodReferenceStrategy( ){
        this.context = new JContext();
        this.config =  JQuickCurlConfig.getInstance();
    }

    public  Method getMethod(JFunction<? , ?> method) {
        try {
            Method lambdaMethod = Arrays.stream(method.getClass().getDeclaredMethods())
                    .filter(m -> !m.isSynthetic() && m.getName().equals("apply"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Unable to find the apply method for Lambda"));
            Method originalMethod = new JMethodReferenceResolver().resolve(method);
            if (originalMethod == null) {
                throw new IllegalStateException("Unable to parse the original method pointed to by the method reference");
            }
            Annotation[] annotations = originalMethod.getAnnotations();
            System.out.println("Successfully obtained method annotation: " + Arrays.toString(annotations));
            return originalMethod;
        } catch (Exception e) {
            console.error("get Method Failed",e);
        }
        return null;
    }
}
