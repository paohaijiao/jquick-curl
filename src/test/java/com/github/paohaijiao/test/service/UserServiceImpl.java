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
package com.github.paohaijiao.test.service;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.test.model.JUser;

import java.util.List;

/**
 * packageName com.github.paohaijiao.test.service
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/6
 */
public class UserServiceImpl {
    @JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
    public static List<JUser> all(JQuickCurlReq req){
        return null;
    }

    @JCurlCommand("curl -X GET http://localhost:8080/api/users/1")
    public static JUser getUserById(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl -X POST http://localhost:8080/api/users \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe\",\"email\":\"john@example.com\"}'")
    public static JUser users(JQuickCurlReq req){
        return null;
    }

    @JCurlCommand("curl -X PUT http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Updated\",\"email\":\"john.updated@example.com\"}'")
    public static JUser usersPut(JQuickCurlReq req){
        return null;
    }

    @JCurlCommand("curl -X PATCH http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Patched\"}'")
    public static JUser usersPatch(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl -X DELETE http://localhost:8080/api/users/1")
    public static Void usersDelete(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl  -X HEAD -I http://localhost:8080/api/users/1")
    public static Void usersHead(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl -X OPTIONS http://localhost:8080/api/users/1")
    public static JResult usersOptions(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    public static String upload(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-multiple \\\n" +
            "-F \"files=@D:\\test\\test.txt\"-F \"files=@D:\\test\\test1.txt\"")
    public static String upload1(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl -X GET http://localhost:8080/api/users/download/test.txt \\\n" +
            "--output 'd://test//download.txt'")
    public static byte[] download(JQuickCurlReq req){
        return null;
    }
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-with-params \\\n" +
            "-F \"userId=123\" \\\n" +
            "-F \"username=john\" \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    public static String uploadWithPostParams(JQuickCurlReq req){
        return null;
    }
}
