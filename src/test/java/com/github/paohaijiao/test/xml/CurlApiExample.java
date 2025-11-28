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
package com.github.paohaijiao.test.xml;
import com.github.paohaijiao.test.xml.model.User;
import com.github.paohaijiao.xml.factory.CurlApiFactory;

/**
 * packageName com.github.paohaijiao.test.xml
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/27
 */
public class CurlApiExample {

    public static void main(String[] args) {
        CurlApiFactory factory = new CurlApiFactory("src/main/resources/apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        User user= userApi.getUserById("1","bear token");
        System.out.println(user);
    }
}
