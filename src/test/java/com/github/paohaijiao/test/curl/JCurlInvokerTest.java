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
package com.github.paohaijiao.test.curl;

import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;
import com.github.paohaijiao.handler.JCurlCommandInvocationHandler;
import com.github.paohaijiao.test.model.JGithubAuth;
import com.github.paohaijiao.test.service.ApiService;
import com.github.paohaijiao.test.service.impl.JCurlServiceImpl;
import org.junit.Test;

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
        req.put("user", "haha@qq.com");
        req.put("password", "ghp_FqXU5SYCDg3wxcy9a2lvCLDOejGbYY079A");
        JGithubAuth result = api.retriveUser(req);
        System.out.println(result);
    }
    @Test
    public  void testMethod() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "haha@qq.com");
        req.put("password", "ghp_FqXU5SYCDg3wxcy9a2lvCLDOejGbYY079A");
        Object result = JCurlInvoker.invoke(JCurlServiceImpl::userAll, req);

        System.out.println(result);
    }
}
