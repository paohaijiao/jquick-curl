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

import com.github.paohaijiao.anno.JTimeout;
import com.github.paohaijiao.factory.JCurlResultFactory;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.test.model.JProduct;
import com.github.paohaijiao.test.model.JProductBean;
import com.github.paohaijiao.type.JTypeReference;
import okhttp3.MediaType;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.test
 *
 * @author Martin
 * @version 1.0.0
 * @className JResultProviderTest
 * @date 2025/6/28
 * @description
 */
public class JResultProviderTest {
    @Test
    @JTimeout(connect = 5000, read = 10000)
    public void test1() throws IOException {
        JResult result = new JResult();
        String str="{\"product\":{\"id\":1001,\"serialNumber\":20230501123456789,\"isAvailable\":true,\"productionDate\":\"2023-05-15T08:30:00Z\",\"price\":199.99,\"discountRate\":0.15,\"specifications\":{\"weight\":2.5,\"dimensions\":{\"length\":120,\"width\":80,\"height\":10}},\"tags\":[\"electronics\",\"new-arrival\",\"sale\"],\"inventory\":{\"warehouse1\":150,\"warehouse2\":75,\"total\":225}}}\n";
        result.setString(str);
        Map stringResponse = JCurlResultFactory.convertResponse(result, Map.class);
        System.out.println(str);

        System.out.println(stringResponse);
    }
    @Test
    @JTimeout(connect = 5000, read = 10000)
    public void test2() throws IOException {
        JResult result = new JResult();
        String str="{\"product\":{\"id\":1001,\"serialNumber\":20230501123456789,\"isAvailable\":true,\"productionDate\":\"2023-05-15T08:30:00Z\",\"price\":199.99,\"discountRate\":0.15,\"specifications\":{\"weight\":2.5,\"dimensions\":{\"length\":120,\"width\":80,\"height\":10}},\"tags\":[\"electronics\",\"new-arrival\",\"sale\"],\"inventory\":{\"warehouse1\":150,\"warehouse2\":75,\"total\":225}}}\n";
        result.setString(str);
        result.setMediaType(MediaType.get("application/json"));
        Map stringResponse = JCurlResultFactory.convertResponse(result, Map.class);
        System.out.println(str);
        JProduct<JProductBean> result1 = JCurlResultFactory.convertResponse(
                result,
                new JTypeReference<JProduct<JProductBean>>() {}
        );

        System.out.println(stringResponse);
    }
}
