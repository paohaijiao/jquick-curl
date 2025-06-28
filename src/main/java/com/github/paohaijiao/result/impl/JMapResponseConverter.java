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
package com.github.paohaijiao.result.impl;

import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.result.JResponseConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.result
 *
 * @author Martin
 * @version 1.0.0
 * @className JStringResponseConverter
 * @date 2025/6/28
 * @description
 */
public class JMapResponseConverter implements JResponseConverter<Map> {
    @Override
    public Map convert(JResult response) throws IOException {
       String string= response.getString();
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(string, new TypeToken<Map<String, Object>>() {}.getType());
        return map;
    }
}
