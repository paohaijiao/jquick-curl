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
package com.github.paohaijiao.enums;

import com.github.paohaijiao.exception.JAssert;
import lombok.Getter;

@Getter
public enum JHttpMethod {

    GET("GET","GET"),

    POST("POST","POST"),

    PUT("PUT","PUT"),

    DELETE("DELETE","DELETE"),

    PATCH("PATCH","PATCH"),

    HEAD("HEAD","HEAD"),

    OPTIONS("OPTIONS","OPTIONS"),

    TRACE ("TRACE","TRACE"),

    CONNECT("CONNECT","CONNECT");

    private String code;
    private String name;

    JHttpMethod(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static JHttpMethod codeOf(String code){
        for(JHttpMethod httpMethod : JHttpMethod.values()){
            if(httpMethod.code.equals(code)){
                return httpMethod;
            }
        }
        JAssert.throwNewException("非法的方法");
        return null;
    }
    public static boolean requireNotHaveRequestBody(String code){
        if(JHttpMethod.GET.getCode().equals(code)){
            return true;
        } else if (JHttpMethod.HEAD.getCode().equals(code)) {
            return true;
        } else if (JHttpMethod.CONNECT.getCode().equals(code)) {
            return true;
        }  else if (JHttpMethod.TRACE .getCode().equals(code)) {
            return true;
        }else{
            return false;
        }

    }
}
