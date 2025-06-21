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
public class JQuickCurlConfig {

    private static final long DEFAULT_CONNECT_TIMEOUT = 10_000;
    private static final long DEFAULT_READ_TIMEOUT = 10_000;
    private static final long DEFAULT_WRITE_TIMEOUT = 10_000;
    private static final int DEFAULT_MAX_IDLE_CONNECTIONS = 5;
    private static final long DEFAULT_KEEP_ALIVE_DURATION = 5;

    private long connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private long readTimeout = DEFAULT_READ_TIMEOUT;
    private long writeTimeout = DEFAULT_WRITE_TIMEOUT;
    private int maxIdleConnections = DEFAULT_MAX_IDLE_CONNECTIONS;
    private long keepAliveDuration = DEFAULT_KEEP_ALIVE_DURATION;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    private final List<Interceptor> interceptors = new ArrayList<>();
    private final List<Interceptor> networkInterceptors = new ArrayList<>();
    private boolean retryOnConnectionFailure = true;
    private boolean followRedirects = true;
    private boolean followSslRedirects = true;


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

    public OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, timeUnit)
                .readTimeout(readTimeout, timeUnit)
                .writeTimeout(writeTimeout, timeUnit)
                .connectionPool(new ConnectionPool(maxIdleConnections, keepAliveDuration, timeUnit))
                .retryOnConnectionFailure(retryOnConnectionFailure)
                .followRedirects(followRedirects)
                .followSslRedirects(followSslRedirects);

        interceptors.forEach(builder::addInterceptor);
        networkInterceptors.forEach(builder::addNetworkInterceptor);
        return builder.build();
    }
    public JQuickCurlConfig loadFromProperties(Properties props) {
        this.connectTimeout = Long.parseLong(props.getProperty("quick.curl.connect.timeout",
                String.valueOf(DEFAULT_CONNECT_TIMEOUT)));
        this.readTimeout = Long.parseLong(props.getProperty("quick.curl.read.timeout",
                String.valueOf(DEFAULT_READ_TIMEOUT)));
        this.writeTimeout = Long.parseLong(props.getProperty("quick.curl.write.timeout",
                String.valueOf(DEFAULT_WRITE_TIMEOUT)));
        this.maxIdleConnections = Integer.parseInt(props.getProperty("quick.curl.pool.max.idle",
                String.valueOf(DEFAULT_MAX_IDLE_CONNECTIONS)));
        this.keepAliveDuration = Long.parseLong(props.getProperty("quick.curl.pool.keep.alive",
                String.valueOf(DEFAULT_KEEP_ALIVE_DURATION)));
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
    public long getConnectTimeout() {
        return connectTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
