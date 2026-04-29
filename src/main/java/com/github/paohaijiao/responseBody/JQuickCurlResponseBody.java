package com.github.paohaijiao.responseBody;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.Headers;
import okio.BufferedSource;
import okio.Okio;
import okio.ByteString;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JQuickCurlResponseBody extends ResponseBody {

    private final MediaType mediaType;
    private final byte[] bytes;
    private final Long contentLength;
    private final Headers headers;

    private JQuickCurlResponseBody(byte[] bytes, MediaType mediaType, Headers headers) {
        this.bytes = bytes.clone();
        this.mediaType = mediaType;
        this.contentLength = (long) bytes.length;
        this.headers = headers;
    }

    public JQuickCurlResponseBody(byte[] bytes, MediaType mediaType) {
        this(bytes, mediaType, null);
    }

    public JQuickCurlResponseBody(ByteString byteString, MediaType mediaType) {
        this(byteString.toByteArray(), mediaType, null);
    }

    public JQuickCurlResponseBody(ByteString byteString, MediaType mediaType, Headers headers) {
        this(byteString.toByteArray(), mediaType, headers);
    }

    public JQuickCurlResponseBody(String content, MediaType mediaType) {
        this(content.getBytes(StandardCharsets.UTF_8), mediaType, null);
    }

    public JQuickCurlResponseBody(String content, MediaType mediaType, Headers headers) {
        this(content.getBytes(StandardCharsets.UTF_8), mediaType, headers);
    }

    public JQuickCurlResponseBody(String content, MediaType mediaType, Charset charset) {
        this(content.getBytes(charset), mediaType, null);
    }

    public JQuickCurlResponseBody(String content, MediaType mediaType, Charset charset, Headers headers) {
        this(content.getBytes(charset), mediaType, headers);
    }

    public JQuickCurlResponseBody(InputStream inputStream, MediaType mediaType) throws IOException {
        this(readInputStreamToBytes(inputStream), mediaType, null);
    }

    public JQuickCurlResponseBody(InputStream inputStream, MediaType mediaType, Headers headers) throws IOException {
        this(readInputStreamToBytes(inputStream), mediaType, headers);
    }

    public JQuickCurlResponseBody(Reader reader, MediaType mediaType) throws IOException {
        this(readReaderToString(reader).getBytes(StandardCharsets.UTF_8), mediaType, null);
    }

    public JQuickCurlResponseBody(Reader reader, MediaType mediaType, Headers headers) throws IOException {
        this(readReaderToString(reader).getBytes(StandardCharsets.UTF_8), mediaType, headers);
    }

    public JQuickCurlResponseBody(Reader reader, MediaType mediaType, Charset charset) throws IOException {
        this(readReaderToString(reader).getBytes(charset), mediaType, null);
    }

    public JQuickCurlResponseBody(Reader reader, MediaType mediaType, Charset charset, Headers headers) throws IOException {
        this(readReaderToString(reader).getBytes(charset), mediaType, headers);
    }

    public JQuickCurlResponseBody(ResponseBody body) throws IOException {
        this(body.bytes(), body.contentType(), null);
    }

    public JQuickCurlResponseBody(ResponseBody body, Headers headers) throws IOException {
        this(body.bytes(), body.contentType(), headers);
    }

    public JQuickCurlResponseBody(File file, MediaType mediaType) throws IOException {
        this(readFileToBytes(file), mediaType, null);
    }

    public JQuickCurlResponseBody(File file, MediaType mediaType, Headers headers) throws IOException {
        this(readFileToBytes(file), mediaType, headers);
    }

    private static byte[] readInputStreamToBytes(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[8192];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    private static String readReaderToString(Reader reader) throws IOException {
        if (reader == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[8192];
        int charsRead;
        while ((charsRead = reader.read(buffer)) != -1) {
            sb.append(buffer, 0, charsRead);
        }
        return sb.toString();
    }

    private static byte[] readFileToBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return readInputStreamToBytes(fis);
        }
    }

    @Override
    public MediaType contentType() {
        return mediaType;
    }

    @Override
    public long contentLength() {
        return contentLength;
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(Okio.source(new ByteArrayInputStream(bytes)));
    }

    public Headers getHeaders() {
        return headers;
    }

    public String header(String name) {
        return headers != null ? headers.get(name) : null;
    }

    public List<String> headers(String name) {
        return headers != null ? headers.values(name) : null;
    }

    public boolean hasHeader(String name) {
        return headers != null && headers.get(name) != null;
    }

    public byte[] getCachedBytes() {
        return bytes.clone();
    }

    public ByteString toByteString() {
        return ByteString.of(bytes);
    }

    public String asString() {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String asString(Charset charset) {
        return new String(bytes, charset);
    }

    public InputStream asInputStream() {
        return new ByteArrayInputStream(bytes);
    }

    public InputStreamReader asReader() {
        return new InputStreamReader(asInputStream(), StandardCharsets.UTF_8);
    }

    public InputStreamReader asReader(Charset charset) {
        return new InputStreamReader(asInputStream(), charset);
    }

    public BufferedReader asBufferedReader() {
        return new BufferedReader(asReader());
    }

    public BufferedReader asBufferedReader(Charset charset) {
        return new BufferedReader(asReader(charset));
    }
}