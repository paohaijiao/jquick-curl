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
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.test.model.JGithubAuth;
import com.github.paohaijiao.test.model.JUser;
import com.github.paohaijiao.test.service.ApiService;
import com.github.paohaijiao.test.service.UserService;
import com.github.paohaijiao.test.service.UserServiceImpl;
import com.github.paohaijiao.test.service.impl.JCurlServiceImpl;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * packageName com.github.paohaijiao.test.curl
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/6
 */
public class JCurlInvokerMethodTest {
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
    @Test
    public  void testMethod() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        JUser result = JCurlInvoker.invoke(UserServiceImpl::getUserById, req,JUser.class);
        System.out.println(result);
    }
    @Test
    public  void users() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JUser result = JCurlInvoker.invoke(UserServiceImpl::users, req,JUser.class);
        System.out.println(result);
    }
    @Test
    public  void usersPut() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JUser result = JCurlInvoker.invoke(UserServiceImpl::usersPut, req,JUser.class);
        System.out.println(result);
    }
    @Test
    public  void usersPatch() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JUser result = JCurlInvoker.invoke(UserServiceImpl::usersPatch, req,JUser.class);
        System.out.println(result);
    }
    @Test
    public  void usersDelete() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JCurlInvoker.invoke(UserServiceImpl::usersDelete, req,Void.class);
        System.out.println();
    }
    @Test
    public  void usersHead() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JCurlInvoker.invoke(UserServiceImpl::usersHead, req,Void.class);
        System.out.println();
    }
    @Test
    public  void usersOptions() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JResult r=JCurlInvoker.invoke(UserServiceImpl::usersOptions, req, JResult.class);
        System.out.println();
    }
    @Test
    public  void upload() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        String r=JCurlInvoker.invoke(UserServiceImpl::upload, req, String.class);
        System.out.println();
    }
    @Test
    public  void upload1() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        String r=JCurlInvoker.invoke(UserServiceImpl::upload1, req, String.class);
        System.out.println();
    }
    @Test
    public  void download() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        byte[] r=JCurlInvoker.invoke(UserServiceImpl::download, req, byte[].class);
        Path path = Paths.get("d://test/xx1.txt");
        Files.write(path, r, StandardOpenOption.CREATE);
        System.out.println();
    }
    @Test
    public  void uploadWithPostParams() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        String r=JCurlInvoker.invoke(UserServiceImpl::uploadWithPostParams, req, String.class);
        System.out.println(r);
    }
}
