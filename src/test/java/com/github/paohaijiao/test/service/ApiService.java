package com.github.paohaijiao.test.service;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.test.model.JGithubAuth;

public interface ApiService {
    @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
    JGithubAuth retriveUser(JQuickCurlReq req);

    @JCurlCommand("curl -X GET https://api.example.com/data")
    String fetchData();


}
