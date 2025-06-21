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
package com.jquick.test;

import com.github.paohaijiao.anno.JTimeout;
import com.github.paohaijiao.model.JResult;
import com.paohaijiao.javelin.param.JContext;
import com.github.paohaijiao.parser.JQuickCurlLexer;
import com.github.paohaijiao.parser.JQuickCurlParser;
import com.github.paohaijiao.visitor.JQuickCurlCommonVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

public class JQuickCurlTest {
    @Test
    @JTimeout(connect = 5000, read = 10000)
    public void test1() throws IOException {
        String input = "curl -X GET --location 'http://localhost:8080/api/users/all'";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }

    @Test
    public void test2() throws IOException {
        String input = "curl -X GET http://localhost:8080/api/users/1\n";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void test3() throws IOException {
        String input = "curl -X POST http://localhost:8080/api/users \\\n" +
                "-H \"Content-Type: application/json\" \\\n" +
                "-d '{\"name\":\"John Doe\",\"email\":\"john@example.com\"}'";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void put() throws IOException {
        String input = "curl -X PUT http://localhost:8080/api/users/1 \\\n" +
                "-H \"Content-Type: application/json\" \\\n" +
                "-d '{\"name\":\"John Doe Updated\",\"email\":\"john.updated@example.com\"}'";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void patch() throws IOException {
        String input = "curl -X PATCH http://localhost:8080/api/users/1 \\\n" +
                "-H \"Content-Type: application/json\" \\\n" +
                "-d '{\"name\":\"John Doe Patched\"}'";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void delete() throws IOException {
        String input = "curl -X DELETE http://localhost:8080/api/users/1\n";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void head() throws IOException {
        String input = "curl  -X HEAD -I http://localhost:8080/api/users/1\n";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void options() throws IOException {
        String input = "curl -X OPTIONS http://localhost:8080/api/users/1\n";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void TRACE() throws IOException {
        String input = "curl -X TRACE http://localhost:8080/api/users/trace \\\n" +
                "-H \"Content-Type: text/plain\" \\\n" +
                "-d \"This is a trace request body\"";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void upload() throws IOException {
        String input = "curl -X POST http://localhost:8080/api/users/upload \\\n" +
                "-F \"file=@D:\\test\\test.txt\"";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void upload1() throws IOException {
        String input = "curl -X POST http://localhost:8080/api/users/upload-multiple \\\n" +
                "-F \"files=@D:\\test\\test.txt\"" +
                "-F \"files=@D:\\test\\test1.txt\"";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void download() throws IOException {
        String input = "curl -X GET http://localhost:8080/api/users/download/test.txt \\\n" +
                "--output 'd://test//download.txt'";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        result.getBytes();
        System.out.println(result);
    }
    @Test
    public void uploadWithParams() throws IOException {
        String input = "curl -X POST http://localhost:8080/api/users/upload-with-params \\\n" +
                "-F \"userId=123\" \\\n" +
                "-F \"username=john\" \\\n" +
                "-F \"file=@D:\\test\\test.txt\"";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void uploadWithPostParams() throws IOException {//;type=application/json
        String input = "curl -X POST http://localhost:8080/api/users/upload-with-dto \\\n" +
                "-F \"data={\\\"userId\\\":\\\"123\\\",\\\"username\\\":\\\"john\\\"}\" " +
                "-F \"file=@D:\\test\\test1.txt\"";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        JContext params = new JContext();
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
    @Test
    public void testvariable() throws IOException {
        JContext params = new JContext();
        String input = "curl -X GET --location ${url}";
        System.out.println(input);
        JQuickCurlLexer lexer = new JQuickCurlLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JQuickCurlParser parser = new JQuickCurlParser(tokens);
        ParseTree tree = parser.curlCommand();
        params.put("url","http://localhost:8080/api/users/all");
        JQuickCurlCommonVisitor visitor = new JQuickCurlCommonVisitor(params);
        JResult result = (JResult)visitor.visit(tree);
        System.out.println(result);
    }
}
