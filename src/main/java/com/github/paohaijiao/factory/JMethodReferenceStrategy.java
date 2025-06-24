package com.github.paohaijiao.factory;

import com.github.paohaijiao.config.JQuickCurlConfig;
import com.github.paohaijiao.console.JConsole;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.domain.resp.JQuickCurlResp;
import com.github.paohaijiao.function.JFunction;
import com.github.paohaijiao.model.JResult;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.resolve.JMethodReferenceResolver;
import com.github.paohaijiao.support.JCurlCommandProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JMethodReferenceStrategy  {
    private static JConsole console = new JConsole();
    private JContext context=new JContext();
    private JQuickCurlConfig config=JQuickCurlConfig.getInstance();
    public JMethodReferenceStrategy(JContext context){
        this.context = context;
        this.config =  JQuickCurlConfig.getInstance();
    }
    public JMethodReferenceStrategy(JQuickCurlConfig config){
        this.context = context;
        this.config =  config;
    }
    public JMethodReferenceStrategy(JContext context,JQuickCurlConfig config){
        this.context = context;
        this.config =  config;
    }
    public JMethodReferenceStrategy( ){
        this.context = new JContext();
        this.config =  JQuickCurlConfig.getInstance();
    }

    public  Method getMethod(JFunction<JQuickCurlReq, JResult> method) {
        try {
            Method lambdaMethod = Arrays.stream(method.getClass().getDeclaredMethods())
                    .filter(m -> !m.isSynthetic() && m.getName().equals("apply"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Unable to find the apply method for Lambda"));
            Method originalMethod = new JMethodReferenceResolver().resolve(method);
            if (originalMethod == null) {
                throw new IllegalStateException("Unable to parse the original method pointed to by the method reference");
            }
            Annotation[] annotations = originalMethod.getAnnotations();
            System.out.println("Successfully obtained method annotation: " + Arrays.toString(annotations));
            JQuickCurlReq req = new JQuickCurlReq();
            method.apply(req);
            return originalMethod;
        } catch (Exception e) {
            console.error("get Method Failed",e);
        }
        return null;
    }
}
