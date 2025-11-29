package com.github.paohaijiao.test.xml;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.test.model.JUser;
import com.github.paohaijiao.test.xml.model.CreateResult;
import com.github.paohaijiao.test.xml.model.User;
import com.github.paohaijiao.xml.param.Param;

import java.util.List;

public interface UserApi {

    List<JUser> all(JQuickCurlReq req);

    JUser getUserById(JQuickCurlReq req);

    JUser users(JQuickCurlReq req);


    JUser usersPut(JQuickCurlReq req);

    JUser usersPatch(JQuickCurlReq req);

    Void usersDelete(JQuickCurlReq req);

    Void usersHead(JQuickCurlReq req);

    JResult usersOptions(JQuickCurlReq req);

    String usersTrace(JQuickCurlReq req);

    String upload(JQuickCurlReq req);

    String upload1(JQuickCurlReq req);

    byte[] download(JQuickCurlReq req);

    String uploadWithPostParams(JQuickCurlReq req);
}
