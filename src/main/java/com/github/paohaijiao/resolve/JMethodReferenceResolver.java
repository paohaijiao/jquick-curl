package com.github.paohaijiao.resolve;

import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.domain.resp.JQuickCurlResp;
import com.github.paohaijiao.function.JFunction;
import com.github.paohaijiao.model.JResult;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JMethodReferenceResolver {

    public Method resolve(JFunction<JQuickCurlReq, JResult> method) {
        try {
            SerializedLambda lambda = getSerializedLambda(method);
            if (lambda == null) {
                throw new IllegalStateException("Unable to parse Lambda expression");
            }

            String className = lambda.getImplClass().replace('/', '.');
            String rawMethodName = lambda.getImplMethodName();
            String normalizedMethodName = normalizeMethodName(rawMethodName);
            Class<?> declaringClass = Class.forName(className);
            Method originalMethod = findMatchingMethod(declaringClass, normalizedMethodName,
                    lambda.getImplMethodSignature());
            if (originalMethod == null) {
                throw new IllegalStateException("Unable to find a matching method: " + normalizedMethodName);
            }
            return originalMethod;
        } catch (Exception e) {
            return null;
        }
    }
    private String normalizeMethodName(String rawName) {
        String name = rawName.startsWith("lambda$") ?
                rawName.substring("lambda$".length()) : rawName;
        int dollarIndex = name.lastIndexOf('$');
        if (dollarIndex > 0 && Character.isDigit(name.charAt(dollarIndex + 1))) {
            name = name.substring(0, dollarIndex);
        }

        return name;
    }
    private Method findMatchingMethod(Class<?> declaringClass,
                                      String methodName,
                                      String descriptor) {
        return Arrays.stream(declaringClass.getDeclaredMethods())
                .filter(m -> m.getName().equals(methodName))
                .filter(m -> matchesMethodSignature(m, descriptor))
                .findFirst()
                .orElse(null);
    }

    private boolean matchesMethodSignature(Method method, String descriptor) {
        String[] params = descriptor.split(";");
        return method.getParameterCount() == params.length - 1;
    }

    private SerializedLambda getSerializedLambda(Object lambda) throws Exception {
        try {
            Method replaceMethod = lambda.getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(lambda);
        } catch (Exception e) {
            throw new Exception("Unable to obtain serializedLambda", e);
        }
    }
}
