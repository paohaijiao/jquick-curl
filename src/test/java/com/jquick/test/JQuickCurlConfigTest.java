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
package com.jquick.test;

import com.github.paohaijiao.config.JQuickCurlConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * packageName com.jquick.test
 *
 * @author Martin
 * @version 1.0.0
 * @className JQuickCurlConfig
 * @date 2025/6/21
 * @description
 */
public class JQuickCurlConfigTest {
    @Test
    public void test1() throws IOException {
        // 配置全局参数
        JQuickCurlConfig config = JQuickCurlConfig.getInstance()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectionPool(10, 5, TimeUnit.MINUTES);
                //.addInterceptor(new LoggingInterceptor());

        // 创建客户端
        OkHttpClient client = config.createClient();
        Request request = new Request.Builder()
                .url("https://localhost:8080/data")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
