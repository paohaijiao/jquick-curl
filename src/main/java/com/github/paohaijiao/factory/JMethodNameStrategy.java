package com.github.paohaijiao.factory;

import java.lang.reflect.Method;

public class JMethodNameStrategy implements JMethodStrategy{
    private final Class<?> clazz;
    private final String methodName;
    private final Class<?>[] parameterTypes;

    public JMethodNameStrategy(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
    }

    @Override
    public Method getMethod() throws Exception {
        return clazz.getMethod(methodName, parameterTypes);
    }
}
