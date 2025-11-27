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
package com.github.paohaijiao.test.xml.model;

/**
 * packageName com.github.paohaijiao.test.xml.model
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/27
 */
public class CreateResult {

    private boolean success;

    private String message;

    private String id;

    private String code;

    public CreateResult() {
    }

    public CreateResult(boolean success, String message, String id, String code) {
        this.success = success;
        this.message = message;
        this.id = id;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CreateResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
