package com.github.paohaijiao.test;

import com.github.paohaijiao.anno.JTimeout;
import com.github.paohaijiao.factory.JMethodReferenceStrategy;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.parser.JQuickCurlLexer;
import com.github.paohaijiao.parser.JQuickCurlParser;
import com.github.paohaijiao.visitor.JQuickCurlCommonVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class JMethodTest {

    @Test
    @JTimeout(connect = 5000, read = 10000)
    public void test1() throws Exception {
//        JMethodReferenceStrategy strategy = new JMethodReferenceStrategy<>(JMethodTest::test1);
//        Method method = strategy.getMethod();
//        System.out.println("Method name: " + method.getName()); // 输出: length
//        System.out.println("Declaring class: " + method.getDeclaringClass()); // 输出: class java.lang.String
    }
}
