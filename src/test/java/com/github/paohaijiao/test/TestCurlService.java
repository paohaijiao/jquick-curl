package com.github.paohaijiao.test;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.anno.JTimeout;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.model.SysUser;

import java.util.List;

public interface TestCurlService {

    @JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
    @JTimeout(connect = 2000, read = 5000)
    List<SysUser> getResult(JQuickCurlReq param);
}
