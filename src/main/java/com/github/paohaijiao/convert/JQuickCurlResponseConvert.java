package com.github.paohaijiao.convert;

import com.github.paohaijiao.responseBody.JQuickCurlResponseBody;
import com.github.paohaijiao.transformer.JQuickValueTransformer;
import com.github.paohaijiao.transformer.type.JQuickJavaTypeReference;
import com.google.protobuf.ByteString;
import okhttp3.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;

public class JQuickCurlResponseConvert {

    public  Object convertResponse(JQuickCurlResponseBody raw, Method method) {
        Class<?> returnType = method.getReturnType();
        if (returnType.equals(Void.TYPE) || returnType.equals(java.lang.Void.class)) {
            return null;
        }
        if (byte[].class.equals(returnType)) {
            return raw.getCachedBytes();
        }
        if (InputStream.class.equals(returnType)) {
            return raw.asInputStream();
        }
        if (Reader.class.equals(returnType)) {
            return raw.asReader();
        }
        if (BufferedReader.class.equals(returnType)) {
            return raw.asBufferedReader();
        }

        if (ByteString.class.equals(returnType)) {
            return raw.toByteString();
        }
        if (JQuickCurlResponseBody.class.equals(returnType)) {
            return raw;
        }
        if (ResponseBody.class.equals(returnType) || okhttp3.ResponseBody.class.equals(returnType)) {
            return raw;
        }
        if (String.class.equals(returnType)) {
            return raw.asString();
        }
        JQuickValueTransformer transformer=new JQuickValueTransformer();
        JQuickJavaTypeReference reference=JQuickJavaTypeReference.of(method.getReturnType());
        return transformer.transform(raw.asString(),reference);
    }
}
