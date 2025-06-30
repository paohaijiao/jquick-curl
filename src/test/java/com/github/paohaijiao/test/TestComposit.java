package com.github.paohaijiao.test;

import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.handler.JCurlCommandInvocationHandler;
import com.github.paohaijiao.model.SysUser;
import org.junit.Test;

import java.util.List;


public class TestComposit {
    @Test
    public  void test() throws Exception {
        TestCurlService service = JCurlCommandInvocationHandler.createProxy(TestCurlService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        List<SysUser> result = service.getResult(req);
        System.out.println(result);
    }
}
