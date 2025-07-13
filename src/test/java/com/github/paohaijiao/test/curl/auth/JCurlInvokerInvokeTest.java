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
public class JCurlInvokerInvokeTest {
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
    public  void all() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.getUserById(req);
        System.out.println(result);
    }
    @Test
    public  void users() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.users(req);
        System.out.println(result);
    }
    @Test
    public  void usersPut() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.usersPut(req);
        System.out.println(result);
    }

    @Test
    public  void usersPatch() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.usersPatch(req);
        System.out.println(result);
    }

    @Test
    public  void usersDelete() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        api.usersDelete(req);
        System.out.println();
    }
    @Test
    public  void head() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        api.usersHead(req);
        System.out.println();
    }
    @Test
    public  void usersOptions() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        JResult jResult=api.usersOptions(req);
        System.out.println(jResult);
    }
    @Test
    public  void usersTrace() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        String jResult=api.usersTrace(req);
        System.out.println(jResult);
    }
    @Test
    public  void upload() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        String jResult=api.upload(req);
        System.out.println(jResult);
    }
    @Test
    public  void upload1() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        String jResult=api.upload1(req);
        System.out.println(jResult);
    }
    @Test
    public  void downloadByte() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        byte[] bytes=api.download(req);
        Path path = Paths.get("d://test/xx1.txt");
        Files.write(path, bytes, StandardOpenOption.CREATE);
        System.out.println("jResult");
    }
    @Test
    public  void uploadWithPostParams() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        String bytes=api.uploadWithPostParams(req);
        System.out.println(bytes);
    }



}
