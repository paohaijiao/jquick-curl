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
package com.github.paohaijiao.test.curl.batch;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.anno.JTimeout;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.support.JQuickCurlBatchRunner;
import com.github.paohaijiao.test.model.JUser;

import java.util.List;

/**
 * packageName com.jquick.test
 *
 * @author Martin
 * @version 1.0.0
 * @className JCurlCommandTests
 * @date 2025/6/21
 * @description
 */
public class JCurlBatchCommandTest {
    public static void main(String[] args) throws Exception {
        JQuickCurlBatchRunner batch= new JQuickCurlBatchRunner();
        List<JResult> list=batch.runCurlCommands(new JCurlBatchCommandTest(),JResult.class);
        System.out.println(list);
    }

    @JTimeout(connect = 2000, read = 5000)
    @JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
    public List<JUser> testGetUsers() {
        return  null;
    }

    @JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
    public List<JUser> testCreateUser() {
        return  null;
    }
}
