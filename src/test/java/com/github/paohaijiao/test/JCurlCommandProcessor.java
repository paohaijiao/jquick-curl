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
package com.github.paohaijiao.test;

import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.support.CurlCommandProcessor;

/**
 * packageName com.jquick.test
 *
 * @author Martin
 * @version 1.0.0
 * @className JCurlCommandProcessor
 * @date 2025/6/21
 * @description
 */
public class JCurlCommandProcessor {
    @JCurlCommand(
            value = "curl -X POST 'https://api.example.com/login' " +
                    "-H 'Content-Type: application/json' " +
                    "-d '{\"username\":\"test\",\"password\":\"123456\"}'", expectedStatus = 200,
            validationScript = "$haha.html"
    )
    public void testLoginApi() throws Exception {
        CurlCommandProcessor.processClass(JCurlCommandProcessor.class);
    }
}
