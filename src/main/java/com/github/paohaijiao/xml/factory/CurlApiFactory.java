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
package com.github.paohaijiao.xml.factory;

import com.github.paohaijiao.exception.JAssert;
import com.github.paohaijiao.xml.invocation.CurlInvocationHandler;
import com.github.paohaijiao.xml.namespace.CurlNamespace;
import com.github.paohaijiao.xml.parser.CurlXmlParser;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.xml.factory
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/27
 */
public class CurlApiFactory {

    private final Map<String, CurlNamespace> namespaceMap;

    public CurlApiFactory(String xmlPath) {
        CurlXmlParser parser = new CurlXmlParser();
        this.namespaceMap = parser.parse(xmlPath);
    }

    @SuppressWarnings("unchecked")
    public <T> T createApi(Class<T> apiInterface) {
        String interfaceName = apiInterface.getName();
        CurlNamespace namespace = namespaceMap.get(interfaceName);
        JAssert.notNull(namespace, "no curl configuration found for interface: " + interfaceName);
        return (T) Proxy.newProxyInstance(apiInterface.getClassLoader(), new Class[]{apiInterface}, new CurlInvocationHandler(namespace));
    }
}
