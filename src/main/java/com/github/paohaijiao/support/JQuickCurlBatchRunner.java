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
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.paohaijiao.javelin.support
 *
 * @author Martin
 * @version 1.0.0
 * @className JQuickCurlRunner
 * @date 2025/6/21
 * @description
 */
public class JQuickCurlBatchRunner {

    private JContext context=new JContext();

    private JQuickCurlConfig config=JQuickCurlConfig.getInstance();

    public JQuickCurlBatchRunner() {
        this.context =new JContext();
        this.config =JQuickCurlConfig.getInstance();
    }
    public JQuickCurlBatchRunner(JQuickCurlConfig config) {
        this.context =new JContext();
        this.config =config;
    }

    public JQuickCurlBatchRunner(JContext context) {
        this.context =context;
        this.config =JQuickCurlConfig.getInstance();
    }
    public JQuickCurlBatchRunner(JContext context, JQuickCurlConfig config) {
        this.context =context;
        this.config =config;
    }
    public  List<JResult> runCurlCommands(Object obj) throws Exception {
        List<JResult> list=new ArrayList<>();
        for (Method method : obj.getClass().getMethods()) {
            if (method.isAnnotationPresent(JCurlCommand.class)) {
                Object instance = obj.getClass().newInstance();
                method.invoke(instance);
                JResult result=  new JCurlCommandProcessor( this.context,this.config).processMethod(null,method,null);
                list.add(result);
            }
        }
        return list;
    }
}
