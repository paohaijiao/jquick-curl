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
import com.github.paohaijiao.xml.handler.JQuickParseHandler;
import com.github.paohaijiao.xml.invocation.JQuickXmlInvocationHandler;

/**
 * packageName com.github.paohaijiao.xml
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/4/11
 */
public class JQuickCurlXmlParseFactory implements JQuickParseHandler {
    @Override
    public JQuickXmlElement createJQuickXmlElement() {
        return new JQuickCurlXmlElement();
    }

    @Override
    public JQuickXmlInvocationHandler createlInvocationHandler() {
        return new JQuickCurlXmlInvocationHandler();
    }
}
