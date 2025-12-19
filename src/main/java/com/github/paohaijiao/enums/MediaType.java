package com.github.paohaijiao.enums;

import lombok.Getter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum MediaType {

    TEXT_PLAIN("text/plain", StandardCharsets.UTF_8, "txt", "text", "conf", "def", "list", "log", "in", "ini"),
    TEXT_HTML("text/html", StandardCharsets.UTF_8, "html", "htm"),
    TEXT_CSS("text/css", StandardCharsets.UTF_8, "css"),
    TEXT_CSV("text/csv", StandardCharsets.UTF_8, "csv"),
    TEXT_JAVASCRIPT("text/javascript", StandardCharsets.UTF_8, "js"),
    TEXT_XML("text/xml", StandardCharsets.UTF_8, "xml"),
    TEXT_MARKDOWN("text/markdown", StandardCharsets.UTF_8, "md", "markdown"),

    APPLICATION_JSON("application/json", StandardCharsets.UTF_8, "json"),
    APPLICATION_XML("application/xml", StandardCharsets.UTF_8, "xml"),
    APPLICATION_PDF("application/pdf", null, "pdf"),
    APPLICATION_ZIP("application/zip", null, "zip"),
    APPLICATION_GZIP("application/gzip", null, "gz"),
    APPLICATION_OCTET_STREAM("application/octet-stream", null),
    APPLICATION_JAVASCRIPT("application/javascript", StandardCharsets.UTF_8, "js"),
    APPLICATION_XHTML_XML("application/xhtml+xml", StandardCharsets.UTF_8, "xhtml"),
    APPLICATION_RSS_XML("application/rss+xml", StandardCharsets.UTF_8, "rss"),
    APPLICATION_ATOM_XML("application/atom+xml", StandardCharsets.UTF_8, "atom"),
    APPLICATION_YAML("application/yaml", StandardCharsets.UTF_8, "yaml", "yml"),
    APPLICATION_PROTOBUF("application/protobuf", null, "pb", "proto"),
    APPLICATION_MSWORD("application/msword", null, "doc", "dot"),
    APPLICATION_VND_MS_EXCEL("application/vnd.ms-excel", null, "xls", "xlt"),
    APPLICATION_VND_MS_POWERPOINT("application/vnd.ms-powerpoint", null, "ppt", "pot"),

    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded", StandardCharsets.UTF_8),
    MULTIPART_FORM_DATA("multipart/form-data", StandardCharsets.UTF_8),

    IMAGE_JPEG("image/jpeg", null, "jpeg", "jpg", "jpe"),
    IMAGE_PNG("image/png", null, "png"),
    IMAGE_GIF("image/gif", null, "gif"),
    IMAGE_BMP("image/bmp", null, "bmp", "dib"),
    IMAGE_WEBP("image/webp", null, "webp"),
    IMAGE_SVG_XML("image/svg+xml", StandardCharsets.UTF_8, "svg", "svgz"),
    IMAGE_TIFF("image/tiff", null, "tiff", "tif"),
    IMAGE_ICO("image/vnd.microsoft.icon", null, "ico"),
    IMAGE_HEIC("image/heic", null, "heic", "heif"),

    AUDIO_MPEG("audio/mpeg", null, "mp3", "mpga", "mp2", "mp2a", "m2a", "m3a"),
    AUDIO_OGG("audio/ogg", null, "oga", "ogg", "spx"),
    AUDIO_WAV("audio/wav", null, "wav"),
    AUDIO_WEBM("audio/webm", null, "weba"),
    AUDIO_AAC("audio/aac", null, "aac"),
    AUDIO_MIDI("audio/midi", null, "mid", "midi", "kar"),

    VIDEO_MP4("video/mp4", null, "mp4", "mp4v", "mpg4"),
    VIDEO_OGG("video/ogg", null, "ogv"),
    VIDEO_WEBM("video/webm", null, "webm"),
    VIDEO_AVI("video/x-msvideo", null, "avi"),
    VIDEO_MPEG("video/mpeg", null, "mpeg", "mpg", "mpe", "m1v", "m2v"),
    VIDEO_QUICKTIME("video/quicktime", null, "qt", "mov"),
    VIDEO_3GPP("video/3gpp", null, "3gp", "3gpp"),
    VIDEO_FLV("video/x-flv", null, "flv"),

    FONT_TTF("font/ttf", null, "ttf"),
    FONT_OTF("font/otf", null, "otf"),
    FONT_WOFF("font/woff", null, "woff"),
    FONT_WOFF2("font/woff2", null, "woff2"),
    FONT_EOT("application/vnd.ms-fontobject", null, "eot");
    private static final Map<String, MediaType> EXTENSION_MAP = new HashMap<>();
    private static final Map<String, MediaType> VALUE_MAP = new HashMap<>();

    private final String code;

    private final Charset charset;

    private final String[] extensions;

    MediaType(String code, Charset charset, String... extensions) {
        this.code = code;
        this.charset = charset;
        this.extensions = extensions != null ? extensions : new String[0];
    }

    public static MediaType codeOf(String code) {
        if (code == null) return null;
        for (MediaType mediaType : values()) {
            if (mediaType.getCode().trim().equalsIgnoreCase(code.trim())) {
                return mediaType;
            }
        }
        return null;
    }

    public boolean isText() {
        return this.getCode().startsWith("text/") ||
                this.getCode().startsWith("application/json") ||
                this.getCode().startsWith("application/xml") ||
                this.getCode().startsWith("application/xhtml+xml") ||
                this.getCode().startsWith("application/rss+xml") ||
                this.getCode().startsWith("application/atom+xml") ||
                this.getCode().startsWith("application/yaml") ||
                this.getCode().startsWith("application/x-www-form-urlencoded") ||
                this.getCode().startsWith("multipart/form-data") ||
                this.getCode().startsWith("image/svg+xml");
    }

    public boolean isBinary() {
        return !isText();
    }

}
