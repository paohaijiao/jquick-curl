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
package com.github.paohaijiao.test.xml;
import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.test.model.JGithubAuth;
import com.github.paohaijiao.test.model.JUser;
import com.github.paohaijiao.test.service.ApiService;
import com.github.paohaijiao.test.service.UserServiceImpl;
import com.github.paohaijiao.test.xml.model.User;
import com.github.paohaijiao.xml.factory.CurlApiFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * packageName com.github.paohaijiao.test.xml
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/27
 */
public class CurlApiExample {



    @Test
    public  void all1() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        List<JUser> list =userApi.all(req);
        System.out.println(list);
    }
    @Test
    public  void testMethod() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        JUser list =userApi.getUserById(req);
        System.out.println(list);
    }
    @Test
    public  void users() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.users(req));
    }
    @Test
    public  void usersPut() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersPut(req));
    }
    @Test
    public  void usersPatch() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersPatch(req));
    }
    @Test
    public  void usersDelete() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersDelete(req));
    }
    @Test
    public  void usersHead() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersHead(req));
    }
    @Test
    public  void usersOptions() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersOptions(req));
    }
    @Test
    public  void upload() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.upload(req));
    }
    @Test
    public  void upload1() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.upload1(req));
    }
    @Test
    public  void download() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        byte[] r=userApi.download(req);
        Path path = Paths.get("d://test/xx1.txt");
        Files.write(path, r, StandardOpenOption.CREATE);
    }
    @Test
    public  void uploadWithPostParams() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        String r=JCurlInvoker.invoke(UserServiceImpl::uploadWithPostParams, req, String.class);
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        String r1=userApi.uploadWithPostParams(req);
        System.out.println(r1);
    }
}
