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
package com.github.paohaijiao.xml;

import com.github.paohaijiao.xml.element.JQuickXmlElement;

import java.util.Arrays;
import java.util.List;

/**
 * packageName com.github.paohaijiao.xml
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/11
 */
public class JQuickCurlXmlElement implements JQuickXmlElement {
    @Override
    public String getNameSpaceName() {
        return "namespace";
    }

    @Override
    public String getRootElementTagName() {
        return "curls";
    }

    @Override
    public List<String> getChildElementTagName() {
        return Arrays.asList(new String[]{"curl"});
    }

    @Override
    public String getMethodName() {
        return "name";
    }

    @Override
    public String getMethodReturnClass() {
        return "returnClass";
    }

    @Override
    public String getMethodParamClass() {
        return "paramClass";
    }

    @Override
    public String getValue() {
        return "value";
    }
}
