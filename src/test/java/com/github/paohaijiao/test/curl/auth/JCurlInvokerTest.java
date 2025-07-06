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
package com.github.paohaijiao.test.curl.auth;

import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;
import com.github.paohaijiao.function.JFunction;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.test.model.JGithubAuth;
import com.github.paohaijiao.test.model.JUser;
import com.github.paohaijiao.test.service.ApiService;
import com.github.paohaijiao.test.service.UserService;
import com.github.paohaijiao.test.service.UserServiceImpl;
import com.github.paohaijiao.test.service.impl.JCurlServiceImpl;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * packageName com.github.paohaijiao.test.curl
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/6
 */
public class JCurlInvokerTest {
    @Test
    public  void retriveUser() throws Exception {
        ApiService api = JCurlInvoker.createProxy(ApiService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JGithubAuth result = api.retriveUser(req);
        System.out.println(result);
    }
    @Test
    public  void testMethod() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JGithubAuth result = JCurlInvoker.invoke(JCurlServiceImpl::retriveUser, req,JGithubAuth.class);
        System.out.println(result);
    }
    @Test
    public  void all() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        List<JUser> result = api.all(req);
        System.out.println(result);
    }
    @Test
    public  void all1() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JContext context = new JContext();
        JQuickCurlConfig config = JQuickCurlConfig.getInstance();
        Object result = JCurlInvoker.invoke(UserServiceImpl::all, req,JGithubAuth.class);
        TypeToken<List<JUser>> typeToken = new TypeToken<List<JUser>>() {};
        List<JUser> list = JCurlInvoker.invoke(
                UserServiceImpl::all,
                req,
                context,
                config,typeToken.getType()
        );
        System.out.println(list);
    }
}
