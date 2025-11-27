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
package com.github.paohaijiao.factory;

import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.model.JSONObject;
import com.github.paohaijiao.result.JResponseConverter;
import com.github.paohaijiao.result.impl.*;
import com.github.paohaijiao.serializer.JSONSerializer;
import com.github.paohaijiao.type.JTypeReference;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.factory
 *
 * @author Martin
 * @version 1.0.0
 * @className JCurlResultFactory
 * @date 2025/6/28
 * @description
 */
public class JCurlResultFactory<T> {

    private static final Map<Class<?>, JResponseConverter<?>> converters = new HashMap<>();
    static {
        converters.put(String.class, new JStringResponseConverter());
        converters.put(JResult.class, new JPassthroughResponseConverter());
        converters.put(Map.class, new JMapResponseConverter());
        converters.put(JSONObject.class, new JMapResponseConverter());
        converters.put(Object.class, new JObjectResponseConverter());
        converters.put(byte[].class, new JByteResponseConverter());
    }
    @SuppressWarnings("unchecked")
    public static <T> T convertResponse(JResult response, Class<T> targetType) throws IOException {
        JResponseConverter<T> converter = (JResponseConverter<T>) converters.get(targetType);
        if (converter == null) {
            if(targetType instanceof Object){
                JSONSerializer serializer = JSONSerializerFactory.createJQuickSerializer();
                String result=response.getString();
                T t = serializer.deserialize(result, targetType);
                return t;
            }else{
                throw new IllegalArgumentException("No converter registered for type: " + targetType);
            }
        }
        return converter.convert(response);
    }

    /**
     * Convert response to generic type
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertResponse(JResult response, JTypeReference<T> typeReference) throws IOException {
        if (response == null) {
            throw new IllegalArgumentException("response cannot be null");
        }
        if (typeReference == null) {
            throw new IllegalArgumentException("type reference cannot be null");
        }
        Type type = typeReference.getType();
        if (type instanceof Class) {
            return convertResponse(response, (Class<T>) type);
        }
        if (type == byte.class || type == Byte.TYPE) {
            return convertResponse(response, (Class<T>) type);

        }


        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            JResultGenericConverter<T> converter = new JResultGenericConverter<>(rawType);
            return converter.convert(response);
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }
    /**
     * Register custom converter
     */
    public static <T> void registerConverter(Class<T> targetType, JResponseConverter<T> converter) {
        converters.put(targetType, converter);
    }

}
