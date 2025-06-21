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
package com.github.paohaijiao.util;

import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;

/**
 * packageName com.paohaijiao.javelin.util
 *
 * @author Martin
 * @version 1.0.0
 * @className HttpUtil
 * @date 2025/6/21
 * @description
 */
public class HttpUtil {
    public static Response cloneResponse(Response original) throws IOException {
        if (original.body() == null) {
            return original;
        }
        BufferedSource source = original.body().source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer().clone();
        ResponseBody clonedBody = ResponseBody.create(
                original.body().contentType(),
                original.body().contentLength(),
                buffer
        );
        return original.newBuilder()
                .body(clonedBody)
                .build();
    }

}
