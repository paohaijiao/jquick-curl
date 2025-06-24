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
package com.github.paohaijiao.test;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.domain.resp.JQuickCurlResp;
import com.github.paohaijiao.factory.JMethodReferenceStrategy;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.support.JCurlCommandProcessor;
import lombok.SneakyThrows;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

/**
 * packageName com.jquick.test
 *
 * @author Martin
 * @version 1.0.0
 * @className JCurlCommandProcessor
 * @date 2025/6/21
 * @description
 */
public class JCurlCommandProcessorTest {
    @JCurlCommand(
            value = "curl -X GET --location 'http://localhost:8080/api/users/all'", expectedStatus = 200,
            validationScript = "$haha.html"
    )
    public static JResult testQueryUser(JQuickCurlReq param) {
        return null;
    }


    @Test
    public  void test() throws Exception {
        JContext context = new JContext();
        context.put("user", "test");
        JQuickCurlConfig config = JQuickCurlConfig.getInstance();
        JMethodReferenceStrategy wrapper = new JMethodReferenceStrategy();
        Method method=wrapper.getMethod(JCurlCommandProcessorTest::testQueryUser);
        JQuickCurlReq req=new  JQuickCurlReq();
        req.put("hello", "1");
        Object result=  new JCurlCommandProcessor(context,config).processMethod(null,method,req);
        System.out.println(result);
    }



}
