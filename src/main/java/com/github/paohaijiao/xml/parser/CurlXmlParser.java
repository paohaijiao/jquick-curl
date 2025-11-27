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
package com.github.paohaijiao.xml.parser;

import com.github.paohaijiao.xml.method.CurlMethod;
import com.github.paohaijiao.xml.namespace.CurlNamespace;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName com.github.paohaijiao.xml.parser
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/11/27
 */
public class CurlXmlParser {

    public Map<String, CurlNamespace> parse(String xmlPath) {
        Map<String, CurlNamespace> namespaceMap = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlPath));
            NodeList curlsNodes = document.getElementsByTagName("curls");
            for (int i = 0; i < curlsNodes.getLength(); i++) {
                Element curlsElement = (Element) curlsNodes.item(i);
                CurlNamespace curlNamespace = parseCurlsElement(curlsElement);
                namespaceMap.put(curlNamespace.getNamespace(), curlNamespace);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse curl XML: " + xmlPath, e);
        }

        return namespaceMap;
    }

    private CurlNamespace parseCurlsElement(Element curlsElement) {
        CurlNamespace namespace = new CurlNamespace();
        String namespaceName = curlsElement.getAttribute("namespace");
        namespace.setNamespace(namespaceName);

        NodeList curlNodes = curlsElement.getElementsByTagName("curl");
        for (int i = 0; i < curlNodes.getLength(); i++) {
            Element curlElement = (Element) curlNodes.item(i);
            CurlMethod method = parseCurlElement(curlElement);
            namespace.addMethod(method.getName(), method);
        }
        return namespace;
    }

    private CurlMethod parseCurlElement(Element curlElement) {
        CurlMethod method = new CurlMethod();
        method.setName(curlElement.getAttribute("name"));
        method.setReturnClass(curlElement.getAttribute("returnClass"));
        method.setCurlCommand(curlElement.getTextContent().trim());
        return method;
    }
}
