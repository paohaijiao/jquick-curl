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
package com.github.paohaijiao.interceptor;

import com.github.paohaijiao.enums.JCurlLevelLog;
import com.github.paohaijiao.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import okio.Buffer;

import java.util.concurrent.TimeUnit;

/**
 * packageName com.paohaijiao.javelin.interceptor
 *
 * @author Martin
 * @version 1.0.0
 * @className JLoggingInterceptor
 * @date 2025/6/21
 * @description
 */
@Slf4j
public class JLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private final JCurlLevelLog level;
    public JLoggingInterceptor() {
        this(JCurlLevelLog.ALL);
    }

    public JLoggingInterceptor(JCurlLevelLog level) {
        this.level = level;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (level == JCurlLevelLog.NONE) {
            return chain.proceed(request);
        }
        logRequest(request);
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            log.error("<-- HTTP FAILED: " + e);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        try{
            Response clonedResponse = HttpUtil.cloneResponse(response);
            logResponse(response, tookMs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    private void logRequest(Request request) throws IOException {
        log.info("--> {} {}", request.method(), request.url());
        if (level == JCurlLevelLog.HEADERS || level == JCurlLevelLog.ALL) {
            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                log.info("{}: {}", headers.name(i), headers.value(i));
            }

            if (level == JCurlLevelLog.ALL && request.body() != null) {
                RequestBody requestBody = request.body();
                if (isPlaintext(requestBody.contentType())) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    log.info("\n{}", buffer.readString(UTF8));
                } else {
                    log.info("--> [binary body omitted, content-type: {}]", requestBody.contentType());
                }
            }
        }
        log.info("--> END {}", request.method());
    }
    private void logResponse(Response response, long tookMs) throws IOException {
        log.info("<-- {} {} ({}ms)", response.code(), response.message(), tookMs);
        if (level == JCurlLevelLog.HEADERS || level == JCurlLevelLog.ALL) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                log.info("{}: {}", headers.name(i), headers.value(i));
            }
            if (level == JCurlLevelLog.ALL && response.body() != null) {
                ResponseBody responseBody = response.body();
                if (isPlaintext(responseBody.contentType())) {
                    String bodyString = responseBody.string();
                    log.info("\n{}", bodyString);
                    response = response.newBuilder().body(ResponseBody.create(bodyString, responseBody.contentType()))
                            .build();
//                    ResponseBody clonedBody = responseBody.peekBody(Long.MAX_VALUE);
//                    log.info("\n{}", clonedBody.string());
                } else {
                    log.info("<-- [binary body omitted, content-type: {}]", responseBody.contentType());
                }
            }
        }
        log.info("<-- END HTTP");
    }
    private static boolean isPlaintext(MediaType mediaType) {
        if (mediaType == null) return false;
        String type = mediaType.type();
        String subtype = mediaType.subtype();
        return ("text".equals(type) ||
                "json".equals(subtype) ||
                "xml".equals(subtype) ||
                "html".equals(subtype) ||
                "x-www-form-urlencoded".equals(subtype));
    }
}
