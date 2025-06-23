package com.github.paohaijiao;

import com.paohaijiao.javelin.adaptor.JQuickAdaptor;
import com.github.paohaijiao.exception.JAntlrExecutionException;
import com.github.paohaijiao.executor.JQuickCurlExecutor;
import com.paohaijiao.javelin.resource.JQuickReader;
import com.paohaijiao.javelin.resource.impl.JQuickReSourceFileReader;

public class RestMain {
    public static void main(String[] args) {
        JQuickCurlExecutor executor = new JQuickCurlExecutor();
        executor.addErrorListener(error -> {
            System.err.printf("错误: 行%d:%d - %s%n",
                    error.getLine(), error.getCharPosition(), error.getMessage());
            System.err.println("规则栈: " + error.getRuleStack());
        });
        try {
            JQuickReader fileReader = new JQuickReSourceFileReader("rule.txt");
            JQuickAdaptor context = new JQuickAdaptor(fileReader);
            System.out.println(context.getRuleContent());
            Object result = executor.execute(context.getRuleContent());
            System.out.println("结果: " + result);
        } catch (JAntlrExecutionException e) {
            System.err.println("解析失败: " + e.getMessage());
            e.getErrors().forEach(err ->
                    System.err.println(" - " + err.getMessage()));
        }
    }
}
