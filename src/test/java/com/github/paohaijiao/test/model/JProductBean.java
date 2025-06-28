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
package com.github.paohaijiao.test.model;

import lombok.Data;

import java.util.List;

/**
 * packageName com.github.paohaijiao.test.model
 *
 * @author Martin
 * @version 1.0.0
 * @className JProductBean
 * @date 2025/6/28
 * @description
 */
@Data
public class JProductBean {
    /**
     * id : 1001
     * serialNumber : 20230501123456789
     * isAvailable : true
     * productionDate : 2023-05-15T08:30:00Z
     * price : 199.99
     * discountRate : 0.15
     * specifications : {"weight":2.5,"dimensions":{"length":120,"width":80,"height":10}}
     * tags : ["electronics","new-arrival","sale"]
     * inventory : {"warehouse1":150,"warehouse2":75,"total":225}
     */

    private int id;
    private long serialNumber;
    private boolean isAvailable;
    private String productionDate;
    private double price;
    private double discountRate;
    private List<String> tags;

}
