package com.github.paohaijiao.test.responsebody;

import com.github.paohaijiao.responseBody.JQuickCurlResponseBody;
import com.github.paohaijiao.responseBody.JQuickCurlResponseBodyFactory;
import okhttp3.*;
import okio.ByteString;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class JQuickCurlFactory {
    public static void main(String[] args) throws IOException {
        JQuickCurlResponseBodyFactory factory = new JQuickCurlResponseBodyFactory();
        // 1. 从 byte[] 创建
        byte[] data = "Hello World".getBytes();
        factory.fromBytes(data, MediaType.parse("text/plain"));

        // 2. 从 ByteString 创建
        ByteString byteString = ByteString.encodeUtf8("Hello ByteString");
         factory.fromByteString(byteString, MediaType.parse("text/plain"));
        factory.fromString("Hello String", MediaType.parse("text/plain"));

        // 4. 从 InputStream 创建
        InputStream is = new ByteArrayInputStream("Hello InputStream".getBytes());
        factory.fromInputStream(is, MediaType.parse("text/plain"));

        // 5. 从 Reader 创建
        Reader reader = new StringReader("Hello Reader");
       factory.fromReader(reader, MediaType.parse("text/plain"));

        // 6. 从 ResponseBody 创建（最常用）
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://httpbin.org/get")
                .build();

        try (Response response = client.newCall(request).execute()) {
            JQuickCurlResponseBody body6 = factory
                    .fromResponseBody(response.body());
            testMultipleReads(body6);
        }

        // 7. 从文件创建
        File file = new File("test.txt");
        JQuickCurlResponseBody body7 = factory
                .fromFile(file, MediaType.parse("text/plain"));

        // 8. 从文件创建（自动检测 MIME 类型）
        JQuickCurlResponseBody body8 = factory.fromFile(file);
    }

    private static void testMultipleReads(JQuickCurlResponseBody body) throws IOException {
        // 第一次读取
        String content1 = body.asString();
        System.out.println("First read: " + content1);

        // 第二次读取
        String content2 = body.asString();
        System.out.println("Second read: " + content2);

        // 获取字节数组
        byte[] bytes = body.getCachedBytes();
        System.out.println("Bytes length: " + bytes.length);

        // 获取 ByteString
        ByteString bs = body.toByteString();
        System.out.println("ByteString: " + bs.utf8());

        // 使用 InputStream
        try (InputStream is = body.asInputStream()) {
            byte[] buffer = new byte[1024];
            int len = is.read(buffer);
            System.out.println("From InputStream: " + new String(buffer, 0, len));
        }

        // 使用 BufferedReader
        try (BufferedReader br = body.asBufferedReader()) {
            System.out.println("From BufferedReader: " + br.readLine());
        }
    }
}
