package com.github.paohaijiao.test.service;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.test.model.JUser;

import java.util.List;

public interface UserService {

    @JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
    List<JUser> all(JQuickCurlReq req);

    @JCurlCommand("curl -X GET http://localhost:8080/api/users/1")
    JUser getUserById(JQuickCurlReq req);

    @JCurlCommand("curl -X POST http://localhost:8080/api/users/createUser \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe\",\"email\":\"john@example.com\"}'")
    JUser users(JQuickCurlReq req);


    @JCurlCommand("curl -X PUT http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Updated\",\"email\":\"john.updated@example.com\"}'")
    JUser usersPut(JQuickCurlReq req);

    @JCurlCommand("curl -X PATCH http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Patched\"}'")
    JUser usersPatch(JQuickCurlReq req);

    @JCurlCommand("curl -X DELETE http://localhost:8080/api/users/1")
    Void usersDelete(JQuickCurlReq req);

    @JCurlCommand("curl  -X HEAD -I http://localhost:8080/api/users/1")
    Void usersHead(JQuickCurlReq req);

    @JCurlCommand("curl -X OPTIONS http://localhost:8080/api/users/1")
    JResult usersOptions(JQuickCurlReq req);

    @JCurlCommand("curl -X TRACE http://localhost:8080/api/users/trace \\\n" +
            "-H \"Content-Type: text/plain\" \\\n" +
            "-d \"This is a trace request body\"")
    String usersTrace(JQuickCurlReq req);

    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    String upload(JQuickCurlReq req);

    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-multiple \\\n" +
            "-F \"files=@D:\\test\\test.txt\"-F \"files=@D:\\test\\test1.txt\"")
    String upload1(JQuickCurlReq req);

    @JCurlCommand("curl -X GET http://localhost:8080/api/users/download/test.txt \\\n" +
            "--output 'd://test//download.txt'")
    byte[] download(JQuickCurlReq req);

    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-with-params \\\n" +
            "-F \"userId=123\" \\\n" +
            "-F \"username=john\" \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    String uploadWithPostParams(JQuickCurlReq req);


}
