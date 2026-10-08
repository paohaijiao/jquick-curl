<p align="center">
  <img src="src/main/resources/static/jquick-logo.svg" width="240" alt="jquick-curl logo" />
</p>

<h1 align="center">jquick-curl</h1>

<p align="center">
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-curl"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-curl.svg?style=flat-square&label=Maven%20Central" alt="Maven Central" /></a>
  <a href="https://github.com/dromara/jquick-curl/stargazers"><img src="https://img.shields.io/github/stars/dromara/jquick-curl.svg?style=flat-square&logo=github&label=Stars" alt="GitHub Stars" /></a>
  <a href="./LICENSE"><img src="https://img.shields.io/github/license/dromara/jquick-curl.svg?style=flat-square&label=License" alt="License" /></a>
  <a href="#快速开始"><img src="https://img.shields.io/badge/JDK-8%2B-orange.svg?style=flat-square" alt="JDK 8+" /></a>
  <a href="https://github.com/akullpp/awesome-java"><img src="https://awesome.re/mentioned-badge.svg" alt="Awesome Java" /></a>
</p>
<p align="center">
  🌐 <a href="https://www.jquick.org">JQuick Website</a> ·
  📖 <a href="https://github.com/paohaijiao">GitHub</a> ·
  📦 <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/javelin">Maven Central</a>
</p>
<p align="center">
  🔗 <a href="https://github.com/paohaijiao/jquick-curl">jquick-curl</a> ·
  📂 <a href="https://github.com/paohaijiao/jquick-path">jquick-path</a> ·
  📊 <a href="https://github.com/paohaijiao/jquick-excel">jquick-excel</a> ·
  📄 <a href="https://github.com/paohaijiao/jquick-pdf">jquick-pdf</a> ·
  ☕ <a href="https://github.com/paohaijiao/jquick-java">jquick-java</a> ·
  🗄️ <a href="https://github.com/paohaijiao/jquick-sql">jquick-sql</a>
</p>
<p align="center">
  <a href="./README.md">English</a> | <b>简体中文</b>
</p>

**jquick-curl** 是一款面向 Java 的轻量 HTTP 客户端，原生模仿 curl 风格 API：把一条普通的 curl 命令写成 Java 方法即可发起请求。命令由 ANTLR 解析，并通过带连接池的高性能传输层执行，因此无需手写任何请求构建代码。jquick-curl 是 JQuick 生态的子项目，由 [Dromara](https://github.com/dromara/jquick-curl) 开源社区维护。

⭐ 本项目已被 [Awesome Java](https://github.com/akullpp/awesome-java) 列表收录。

## 特性

- **原生 curl 风格 API** —— 用 curl 命令描述请求，用 Java 方法调用。
- **注解与 XML 双配置** —— 使用 `@JCurlCommand` 声明请求，或集中维护在 `apis.xml` 中。
- **动态代理客户端** —— `JCurlInvoker.createProxy(UserApi.class)` 把接口变成可用的客户端。
- **变量替换** —— 运行时从请求中解析 `${name}` / `#{name}` 占位符。
- **条件渲染** —— 仅当 XML 中的 `<if test="...">` 表达式成立时，才拼入对应的 header 或选项。
- **Cookie 支持** —— 手动设置 cookie、基于文件的 cookie 存储、`Set-Cookie` 持久化。
- **HTTP 方法** —— `GET`、`POST`、`PUT`、`PATCH`、`DELETE`、`HEAD`、`OPTIONS`、`TRACE`。
- **文件传输** —— 通过 `-F` 做 multipart 上传，通过 `-o` / `--output` 下载。
- **批量执行** —— 一次调用执行类中所有 `@JCurlCommand` 方法。
- **超时、重试、重定向与连接池** —— 通过 `JQuickCurlConfig` 统一配置。
- **拦截器** —— 全局添加鉴权、日志或请求预处理逻辑。
- **代理与 SSL** —— 支持 HTTP / SOCKS5 代理，`-k` / `--insecure` 可跳过证书校验。

## 快速开始

### 1. Maven 依赖

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-curl</artifactId>
    <version>2.5.0</version>
</dependency>
```

### 2. 基础 GET 请求

在接口方法上声明 curl 命令，然后创建代理并调用即可。

```java
import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;

public interface UserApi {

    // GET request: returns the response body as a String
    @JCurlCommand("curl -X GET https://httpbin.org/get")
    String list(JQuickCurlReq request);
}

class GetDemo {
    public static void main(String[] args) throws Exception {
        UserApi api = JCurlInvoker.createProxy(UserApi.class);
        String body = api.list(new JQuickCurlReq());
        System.out.println(body);
    }
}
```

### 3. 基础 POST 请求

```java
import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;

public interface OrderApi {

    // POST request with a JSON body
    @JCurlCommand("curl -X POST https://httpbin.org/post " +
            "-H 'Content-Type: application/json' " +
            "-d '{\"sku\":\"A-1001\",\"count\":2}'")
    String create(JQuickCurlReq request);
}

class PostDemo {
    public static void main(String[] args) throws Exception {
        OrderApi api = JCurlInvoker.createProxy(OrderApi.class);
        String body = api.create(new JQuickCurlReq());
        System.out.println(body);
    }
}
```

### 4. Cookie 完整用法

jquick-curl 实现了原生 curl 的 cookie 选项：使用 `-b` / `--cookie` 发送 cookie，使用 `-c` / `--cookie-jar` 持久化 cookie。所有 cookie 会合并进同一个 `Cookie` 请求头，响应中的每个 `Set-Cookie` 都可以写入 Netscape 格式的 cookie 文件，该文件即充当持久化的 cookie 存储（CookieStore）。

#### 4.1 手动设置单个 Cookie

```java
import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;

public interface CookieApi {

    // Single cookie: -b / --cookie 'name=value'
    @JCurlCommand("curl -X GET https://httpbin.org/cookies -b 'sessionId=abc123'")
    String withSingleCookie(JQuickCurlReq request);
}

class SingleCookieDemo {
    public static void main(String[] args) throws Exception {
        CookieApi api = JCurlInvoker.createProxy(CookieApi.class);
        System.out.println(api.withSingleCookie(new JQuickCurlReq()));
    }
}
```

#### 4.2 手动设置多个 Cookie

```java
public interface CookieApi {

    // Multiple cookies: separate them with a semicolon inside one -b option
    @JCurlCommand("curl -X GET https://httpbin.org/cookies " +
            "-b 'sessionId=abc123; theme=dark; lang=en'")
    String withMultipleCookies(JQuickCurlReq request);

    // Equivalent form: set the Cookie header directly with -H
    @JCurlCommand("curl -X GET https://httpbin.org/cookies " +
            "-H 'Cookie: sessionId=abc123; theme=dark; lang=en'")
    String withCookieHeader(JQuickCurlReq request);
}
```

多个 cookie 之间用 `;` 分隔。`-b` 与 `-H 'Cookie: ...'` 最终都会写入同一个 `Cookie` 请求头，同名 cookie 会合并而不是直接覆盖。

#### 4.3 从 cookie 文件自动携带 Cookie

```java
public interface CookieApi {

    // Load cookies from a Netscape / Mozilla cookie file: -b @file
    @JCurlCommand("curl -X GET https://httpbin.org/cookies -b @./cookies.txt")
    String withJarFile(JQuickCurlReq request);
}
```

`-b @cookies.txt` 会读取 Netscape 格式的 cookie 文件并自动附加到请求，这是 jquick-curl 跨请求保持 cookie 的方式。注意文件名前需要加 `@` 前缀。

#### 4.4 从响应中提取 Set-Cookie

需要读取响应头时，把返回类型声明为 `JQuickCurlResponseBody` 而不是 `String`。

```java
import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;
import com.github.paohaijiao.responseBody.JQuickCurlResponseBody;

import java.util.List;

public interface LoginApi {

    // Return the raw response body object to access headers
    @JCurlCommand("curl -X GET 'https://httpbin.org/cookies/set?sessionId=abc123'")
    JQuickCurlResponseBody setCookie(JQuickCurlReq request);
}

class ReadSetCookieDemo {
    public static void main(String[] args) throws Exception {
        LoginApi api = JCurlInvoker.createProxy(LoginApi.class);
        JQuickCurlResponseBody response = api.setCookie(new JQuickCurlReq());

        // First Set-Cookie header
        String first = response.header("Set-Cookie");
        // All Set-Cookie headers (redirects may return several)
        List<String> all = response.headers("Set-Cookie");
        // Response payload
        String body = response.asString();

        System.out.println(first);
        System.out.println(all);
        System.out.println(body);
    }
}
```

#### 4.5 Cookie 持久化与登录态复用

```java
import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.executor.JCurlInvoker;
import com.github.paohaijiao.responseBody.JQuickCurlResponseBody;

public interface SessionApi {

    // Step 1: sign in and save every Set-Cookie into cookies.txt (-c / --cookie-jar)
    @JCurlCommand("curl -X POST https://httpbin.org/post " +
            "-H 'Content-Type: application/json' " +
            "-d '{\"user\":\"ada\",\"password\":\"secret\"}' " +
            "-c ./cookies.txt")
    JQuickCurlResponseBody login(JQuickCurlReq request);

    // Step 2: reuse the persisted cookies on the next request (-b @file)
    @JCurlCommand("curl -X GET https://httpbin.org/cookies -b @./cookies.txt")
    String profile(JQuickCurlReq request);
}

class SessionDemo {
    public static void main(String[] args) throws Exception {
        SessionApi api = JCurlInvoker.createProxy(SessionApi.class);
        JQuickCurlReq request = new JQuickCurlReq();

        // Writes ./cookies.txt in Netscape format
        api.login(request);

        // Cookies from the file are attached automatically
        System.out.println(api.profile(request));
    }
}
```

`-c` / `--cookie-jar` 会把每个 `Set-Cookie` 以 Netscape 格式（制表符分隔的 7 列）写入指定文件，后续请求用 `-b @文件` 复用，即可在多次调用之间保持登录态。请勿把真实的 cookie 文件提交到代码仓库。

## 更多示例

### XML 配置

把接口定义集中到 XML 中，让请求模板与 Java 业务代码彻底分离。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE curls PUBLIC "-//PAOHAIJIAO//DTD API CURL 1.0//EN"
        "classpath:paohaijiao/dtd/Jquick-curl.dtd">
<curls namespace="com.example.UserApi">
    <curl name="getUser" returnClass="java.lang.String">
        curl -X GET https://api.example.com/users/#{id} -b @./cookies.txt
    </curl>
</curls>
```

```java
import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.xml.JQuickCurlXmlParseFactory;
import com.github.paohaijiao.xml.factory.JQuickFactory;
import com.github.paohaijiao.xml.factory.JQuickXmlFactory;
import com.github.paohaijiao.xml.handler.JQuickParseHandler;

public interface UserApi {
    String getUser(JQuickCurlReq request);
}

class XmlDemo {
    public static void main(String[] args) throws Exception {
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser, "apis.xml");
        UserApi api = factory.createApi(UserApi.class);
        System.out.println(api.getUser(new JQuickCurlReq()));
    }
}
```

`<curl name="...">` 的属性值必须与 Java 方法名一致，`#{name}` 是 XML 代理使用的变量占位符。

### 变量替换与条件渲染

```java
public interface AuthApi {

    // ${...} placeholders are resolved from JQuickCurlReq
    @JCurlCommand("curl -X GET https://api.example.com/me -u ${user}:${password}")
    String currentUser(JQuickCurlReq request);
}
```

```java
JQuickCurlReq request = new JQuickCurlReq();
request.put("user", "ada");
request.put("password", System.getenv("API_PASSWORD"));
String me = JCurlInvoker.createProxy(AuthApi.class).currentUser(request);
```

```xml
<curl name="search" returnClass="java.lang.String">
    curl -X GET #{host}/search
    <if test="withTrace == true"> -H "X-Trace: #{traceId}" </if>
</curl>
```

`${...}` 用于注解代理，`#{...}` 用于 XML 代理；`<if>` 中的 header 只在表达式成立时才会拼入命令。敏感信息请通过变量注入，不要硬编码在命令里。

### 文件上传与下载

```java
public interface FileApi {

    // Multipart upload: -F 'file=@/path/to/file'
    @JCurlCommand("curl -X POST https://api.example.com/files -F 'file=@./report.pdf'")
    String upload(JQuickCurlReq request);

    // Upload a file together with normal form fields
    @JCurlCommand("curl -X POST https://api.example.com/import " +
            "-F 'userId=1001' -F 'file=@./report.pdf'")
    String uploadWithForm(JQuickCurlReq request);

    // Download: -o / --output writes the response bytes to a local file
    @JCurlCommand("curl -X GET https://api.example.com/files/report.pdf --output './download/report.pdf'")
    byte[] download(JQuickCurlReq request);
}
```

`-F` 支持同时上传多个文件，并可与普通表单字段混用；`--output` 由执行器直接写入磁盘，也可以声明 `byte[]` 返回类型后在业务代码中自行保存。

### 批量执行

```java
import com.github.paohaijiao.anno.JCurlCommand;
import com.github.paohaijiao.responseBody.JQuickCurlResponseBody;
import com.github.paohaijiao.support.JQuickCurlBatchRunner;

import java.util.List;

public class BatchCommands {

    @JCurlCommand("curl -X GET https://httpbin.org/get")
    public String first() {
        return null;
    }

    @JCurlCommand("curl -X GET https://httpbin.org/uuid")
    public String second() {
        return null;
    }
}

class BatchDemo {
    public static void main(String[] args) throws Exception {
        JQuickCurlBatchRunner runner = new JQuickCurlBatchRunner();
        List<JQuickCurlResponseBody> results =
                runner.runCurlCommands(new BatchCommands(), JQuickCurlResponseBody.class);
        results.forEach(r -> System.out.println(r.asString()));
    }
}
```

批量执行会扫描目标类中所有带 `@JCurlCommand` 的公开方法，并按顺序依次调用。

### 超时、连接池与拦截器

```java
import com.github.paohaijiao.config.JQuickCurlConfig;
import okhttp3.Interceptor;

import java.util.concurrent.TimeUnit;

JQuickCurlConfig.getInstance()
        .connectTimeout(3, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .connectionPool(50, 5, TimeUnit.MINUTES)
        .maxRetryCount(2)
        .followRedirects(true);
```

```java
// Add a global interceptor, for example a Bearer token
Interceptor auth = chain -> chain.proceed(
        chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + System.getenv("API_TOKEN"))
                .build());

JQuickCurlConfig.getInstance().addInterceptor(auth);
```

`JQuickCurlConfig` 是全局单例配置，统一管理超时、连接池、重试、重定向与拦截器；单个方法的超时可用 `@JTimeout` 覆盖。

### 已支持的 curl 选项

| 类别 | 支持的写法 | 用途 |
| --- | --- | --- |
| 请求方法 | `-X <METHOD>`、`--request <METHOD>` | 指定 HTTP 方法 |
| 请求头 | `-H 'Name: value'`、`--header 'Name: value'` | 添加请求头 |
| Cookie | `-b 'name=value'`、`--cookie`、`-b @file` | 发送 cookie 或加载 cookie 文件 |
| Cookie 存储 | `-c <file>`、`--cookie-jar <file>` | 把 `Set-Cookie` 持久化到文件 |
| 请求数据 | `-d`、`--data`、`--data-ascii`、`--data-binary`、`--data-raw` | 发送请求体 |
| 表单编码 | `--data-urlencode 'key=value'` | 发送 URL 编码表单数据 |
| Basic 认证 | `-u 'user:password'`、`--user` | 生成 Basic Authorization 请求头 |
| 重定向 | `-L`、`--location`、`--max-redirs <N>` | 跟随重定向并限制次数 |
| 文件上传 | `-F 'file=@/path/to/file'`、`--form 'key=value'` | multipart 上传或普通表单字段 |
| 文件下载 | `-o './file'`、`--output './file'` | 把响应字节写入本地文件 |
| 代理 | `-x 'host:port'`、`--proxy`、`--socks5-hostname` | 使用 HTTP 或 SOCKS5 代理 |
| 协议与日志 | `--http2`、`-k`、`--insecure`、`-v`、`--verbose`、`-s`、`--silent` | HTTP/2、跳过证书校验、详细或静默输出 |

以上选项均已由解析器与测试用例覆盖；未列出的 curl 选项请先验证后再使用。

## 文档

- [apis.xml](./src/main/resources/apis.xml) —— XML 接口定义示例。
- [Jquick-curl.dtd](./src/main/resources/paohaijiao/dtd/Jquick-curl.dtd) —— XML 定义文件的 DTD。
- [README.md](./README.md) —— English documentation.
- [Releases](https://github.com/dromara/jquick-curl/releases) —— 版本历史与更新说明。
- [Issues](https://github.com/dromara/jquick-curl/issues) —— 问题反馈与功能建议。

## 贡献

欢迎任何形式的贡献。

1. 修改代码前请先阅读 [CONTRIBUTING.md](./CONTRIBUTING.md)。
2. 提交 Issue 时请附上版本、运行环境、最小复现步骤和完整错误输出。
3. 提交 Pull Request 前请补充或更新测试，并保持每次改动聚焦。
4. 请勿提交密钥、Token、个人数据或生产环境配置。

## 许可证

jquick-curl 基于 [Apache License 2.0](./LICENSE) 开源。使用、修改或分发本项目时，请遵守协议条款，包括版权、专利与声明要求。
