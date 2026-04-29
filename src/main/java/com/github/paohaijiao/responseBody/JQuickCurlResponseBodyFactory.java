package com.github.paohaijiao.responseBody;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.ByteString;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class JQuickCurlResponseBodyFactory  {
    /**
     * 从 byte[] 创建
     */
    public static JQuickCurlResponseBody fromBytes(byte[] bytes, MediaType mediaType) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes cannot be null");
        }
        return new JQuickCurlResponseBody(bytes, mediaType);
    }

    /**
     * 从 ByteString 创建
     */
    public static JQuickCurlResponseBody fromByteString(ByteString byteString, MediaType mediaType) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString cannot be null");
        }
        return new JQuickCurlResponseBody(byteString.toByteArray(), mediaType);
    }

    /**
     * 从 String 创建（UTF-8）
     */
    public static JQuickCurlResponseBody fromString(String content, MediaType mediaType) {
        if (content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }
        return new JQuickCurlResponseBody(content.getBytes(StandardCharsets.UTF_8), mediaType);
    }

    /**
     * 从 String 创建（指定字符集）
     */
    public static JQuickCurlResponseBody fromString(String content, MediaType mediaType, Charset charset) {
        if (content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        return new JQuickCurlResponseBody(content.getBytes(charset), mediaType);
    }

    /**
     * 从 InputStream 创建
     */
    public static JQuickCurlResponseBody fromInputStream(InputStream inputStream, MediaType mediaType)
            throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }
        byte[] bytes = readInputStreamFully(inputStream);
        return new JQuickCurlResponseBody(bytes, mediaType);
    }

    /**
     * 从 InputStream 创建（带大小限制）
     */
    public static JQuickCurlResponseBody fromInputStream(InputStream inputStream, MediaType mediaType, int maxSizeBytes) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }
        byte[] bytes = readInputStreamWithLimit(inputStream, maxSizeBytes);
        return new JQuickCurlResponseBody(bytes, mediaType);
    }

    /**
     * 从 Reader 创建（UTF-8）
     */
    public static JQuickCurlResponseBody fromReader(Reader reader, MediaType mediaType) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException("reader cannot be null");
        }
        String content = readReaderFully(reader);
        return new JQuickCurlResponseBody(content.getBytes(StandardCharsets.UTF_8), mediaType);
    }

    /**
     * 从 Reader 创建（指定字符集）
     */
    public static JQuickCurlResponseBody fromReader(Reader reader, MediaType mediaType, Charset charset) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException("reader cannot be null");
        }
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        String content = readReaderFully(reader);
        return new JQuickCurlResponseBody(content.getBytes(charset), mediaType);
    }

    /**
     * 从 ResponseBody 创建（最重要！）
     */
    public static JQuickCurlResponseBody fromResponseBody(ResponseBody responseBody) throws IOException {
        if (responseBody == null) {
            throw new IllegalArgumentException("responseBody cannot be null");
        }
        byte[] bytes = responseBody.bytes();
        return new JQuickCurlResponseBody(bytes, responseBody.contentType());
    }

    /**
     * 从 File 创建
     */
    public static JQuickCurlResponseBody fromFile(File file, MediaType mediaType) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        byte[] bytes = readFileFully(file);
        return new JQuickCurlResponseBody(bytes, mediaType);
    }

    /**
     * 从 File 创建（自动检测 MediaType）
     */
    public static JQuickCurlResponseBody fromFile(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        String fileName = file.getName();
        MediaType mediaType = MediaType.parse(getContentTypeByFileName(fileName));
        return fromFile(file, mediaType);
    }
    private static byte[] readInputStreamFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[8192];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    private static byte[] readInputStreamWithLimit(InputStream inputStream, int maxSize) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[8192];
        int bytesRead;
        int totalRead = 0;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            totalRead += bytesRead;
            if (totalRead > maxSize) {
                throw new IOException("Input stream exceeds maximum size of " + maxSize + " bytes");
            }
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    private static String readReaderFully(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[8192];
        int charsRead;
        while ((charsRead = reader.read(buffer)) != -1) {
            sb.append(buffer, 0, charsRead);
        }
        return sb.toString();
    }

    private static byte[] readFileFully(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return readInputStreamFully(fis);
        }
    }

    private static String getContentTypeByFileName(String fileName) {
        if (fileName == null) {
            return "application/octet-stream";
        }
        String lowerName = fileName.toLowerCase();
        if (lowerName.endsWith(".txt")) return "text/plain";
        if (lowerName.endsWith(".json")) return "application/json";
        if (lowerName.endsWith(".xml")) return "application/xml";
        if (lowerName.endsWith(".html")) return "text/html";
        if (lowerName.endsWith(".css")) return "text/css";
        if (lowerName.endsWith(".js")) return "application/javascript";
        if (lowerName.endsWith(".png")) return "image/png";
        if (lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg")) return "image/jpeg";
        if (lowerName.endsWith(".gif")) return "image/gif";
        if (lowerName.endsWith(".pdf")) return "application/pdf";
        if (lowerName.endsWith(".zip")) return "application/zip";
        return "application/octet-stream";
    }
}
