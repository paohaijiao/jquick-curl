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
package com.github.paohaijiao.config;
import lombok.Data;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
/**
 * packageName com.paohaijiao.javelin.config
 *
 * @author Martin
 * @version 1.0.0
 * @className JQuickCurlConfig
 * @date 2025/6/21
 * @description
 */
@Data
public class JQuickCurlConfig {



    private long connectTimeout = 1000000000;
    private long readTimeout = 1000000000;
    private long writeTimeout = 1000000000;
    private long callTimeout = 1000000000;
    private int maxIdleConnections = 1000000000;
    private long keepAliveDuration = 1000000000;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    private final List<Interceptor> interceptors = new ArrayList<>();
    private final List<Interceptor> networkInterceptors = new ArrayList<>();
    private Boolean retryOnConnectionFailure = true;
    private Boolean followRedirects = true;
    private Boolean followSslRedirects = true;


    private static final JQuickCurlConfig INSTANCE = new JQuickCurlConfig();

    private JQuickCurlConfig() {}

    public static JQuickCurlConfig getInstance() {
        return INSTANCE;
    }

    public JQuickCurlConfig connectTimeout(long timeout, TimeUnit unit) {
        this.connectTimeout = timeout;
        this.timeUnit = unit;
        return this;
    }

    public JQuickCurlConfig readTimeout(long timeout, TimeUnit unit) {
        this.readTimeout = timeout;
        this.timeUnit = unit;
        return this;
    }

    public JQuickCurlConfig writeTimeout(long timeout, TimeUnit unit) {
        this.writeTimeout = timeout;
        this.timeUnit = unit;
        return this;
    }

    public JQuickCurlConfig connectionPool(int maxIdleConnections, long keepAliveDuration, TimeUnit unit) {
        this.maxIdleConnections = maxIdleConnections;
        this.keepAliveDuration = keepAliveDuration;
        this.timeUnit = unit;
        return this;
    }

    public JQuickCurlConfig addInterceptor(Interceptor interceptor) {
        this.interceptors.add(interceptor);
        return this;
    }

    public JQuickCurlConfig addNetworkInterceptor(Interceptor interceptor) {
        this.networkInterceptors.add(interceptor);
        return this;
    }

    public JQuickCurlConfig retryOnConnectionFailure(boolean retry) {
        this.retryOnConnectionFailure = retry;
        return this;
    }

    public JQuickCurlConfig followRedirects(boolean follow) {
        this.followRedirects = follow;
        return this;
    }

    public JQuickCurlConfig followSslRedirects(boolean follow) {
        this.followSslRedirects = follow;
        return this;
    }
    public JQuickCurlConfig build() {
        return this;
    }


    public JQuickCurlConfig loadFromProperties(Properties props) {
        this.connectTimeout = Long.parseLong(props.getProperty("quick.curl.connect.timeout",
                String.valueOf(connectTimeout)));
        this.readTimeout = Long.parseLong(props.getProperty("quick.curl.read.timeout",
                String.valueOf(readTimeout)));
        this.writeTimeout = Long.parseLong(props.getProperty("quick.curl.write.timeout",
                String.valueOf(writeTimeout)));
        this.maxIdleConnections = Integer.parseInt(props.getProperty("quick.curl.pool.max.idle",
                String.valueOf(maxIdleConnections)));
        this.keepAliveDuration = Long.parseLong(props.getProperty("quick.curl.pool.keep.alive",
                String.valueOf(keepAliveDuration)));
        return this;
    }
    public JQuickCurlConfig loadFromClasspathResource(String resourceName) throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IOException("Resource not found: " + resourceName);
            }
            Properties props = new Properties();
            props.load(is);
            return loadFromProperties(props);
        }
    }
}
