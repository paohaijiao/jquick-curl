<p align="center">
  <img src="src/main/resources/static/jquick-logo.svg" width="240" alt="jquick-curl logo" />
</p>

<h1 align="center">jquick-curl</h1>

<p align="center">
  <a href="https://central.sonatype.com/artifact/io.github.paohaijiao/jquick-curl"><img src="https://img.shields.io/maven-central/v/io.github.paohaijiao/jquick-curl.svg?style=flat-square&label=Maven%20Central" alt="Maven Central" /></a>
  <a href="https://github.com/dromara/jquick-curl/stargazers"><img src="https://img.shields.io/github/stars/dromara/jquick-curl.svg?style=flat-square&logo=github&label=Stars" alt="GitHub Stars" /></a>
  <a href="./LICENSE"><img src="https://img.shields.io/github/license/dromara/jquick-curl.svg?style=flat-square&label=License" alt="License" /></a>
  <a href="#quick-start"><img src="https://img.shields.io/badge/JDK-8%2B-orange.svg?style=flat-square" alt="JDK 8+" /></a>
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
  <b>English</b> | <a href="./README-CN.md">简体中文</a>
</p>

**jquick-curl** is a lightweight HTTP client for Java that natively mimics the curl style API: you write a request as a plain curl command and call it as an ordinary Java method. Commands are parsed with ANTLR and executed over a pooled, high-performance transport layer, so there is no hand-written request building code. jquick-curl is a sub-project of the JQuick ecosystem and is maintained by the [Dromara](https://github.com/dromara/jquick-curl) open-source community.

⭐ This project is included in the [Awesome Java](https://github.com/akullpp/awesome-java) list.

## Features

- **Native curl style API** — describe a request with a curl command, call it with a Java method.
- **Annotation and XML configuration** — declare requests with `@JCurlCommand` or centralize them in `apis.xml`.
- **Dynamic proxy clients** — `JCurlInvoker.createProxy(UserApi.class)` turns an interface into a working client.
- **Variable substitution** — resolve `${name}` / `#{name}` placeholders from the request at runtime.
- **Conditional rendering** — render headers or options only when an XML `<if test="...">` expression is true.
- **Cookie support** — manual cookies, a file-backed cookie jar, and `Set-Cookie` persistence.
- **HTTP methods** — `GET`, `POST`, `PUT`, `PATCH`, `DELETE`, `HEAD`, `OPTIONS`, `TRACE`.
- **File transfer** — multipart upload with `-F` and download with `-o` / `--output`.
- **Batch execution** — run every `@JCurlCommand` method of a class in a single call.
- **Timeouts, retries, redirects and connection pooling** — configured once through `JQuickCurlConfig`.
- **Interceptors** — add authentication, logging or request preprocessing globally.
- **Proxy and SSL** — HTTP / SOCKS5 proxy, plus `-k` / `--insecure` to skip certificate checks.

## Quick Start

### 1. Maven dependency

```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-curl</artifactId>
    <version>2.5.0</version>
</dependency>
```

### 2. Basic GET

Declare the curl command on an interface method, then create a proxy and call it.

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

### 3. Basic POST

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

### 4. Cookie usage

jquick-curl implements the cookie options of native curl: `-b` / `--cookie` for sending cookies and `-c` / `--cookie-jar` for persisting them. Cookies are merged into a single `Cookie` request header, and every `Set-Cookie` header can be saved to a Netscape cookie file that plays the role of a persistent cookie store.

#### 4.1 Set a single cookie manually

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

#### 4.2 Set multiple cookies manually

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

Multiple cookies are separated by `;`. Both `-b` and `-H 'Cookie: ...'` end up in the same `Cookie` request header, and repeated names are merged instead of overwritten.

#### 4.3 Carry cookies automatically from a cookie jar file

```java
public interface CookieApi {

    // Load cookies from a Netscape / Mozilla cookie file: -b @file
    @JCurlCommand("curl -X GET https://httpbin.org/cookies -b @./cookies.txt")
    String withJarFile(JQuickCurlReq request);
}
```

`-b @cookies.txt` reads a Netscape format cookie file and attaches the cookies to the request automatically, which is how jquick-curl keeps cookies across calls. Note the `@` prefix before the file name.

#### 4.4 Read `Set-Cookie` from the response

Return `JQuickCurlResponseBody` instead of `String` when you need response headers.

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

#### 4.5 Persist cookies and reuse the session

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

`-c` / `--cookie-jar` writes every `Set-Cookie` header to the given file in Netscape format (seven tab-separated columns), and `-b @file` reuses it on later requests, which keeps a login session alive across calls. Never commit a real cookie file to a repository.

## More Examples

### XML configuration

Centralize API definitions in XML so request templates stay out of Java code.

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

The `<curl name="...">` attribute must match the Java method name, and `#{name}` is the placeholder used by XML proxies.

### Variable substitution and conditional rendering

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

`${...}` is used by annotation proxies and `#{...}` by XML proxies. A header inside `<if>` is appended only when the expression is true. Keep credentials in variables instead of hard-coding them in the command.

### File upload and download

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

`-F` accepts several files and can mix them with regular form fields. `--output` writes the response to disk inside the executor; alternatively declare a `byte[]` return type and save the bytes in your own code.

### Batch execution

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

Batch execution scans every public method annotated with `@JCurlCommand` and invokes them in order.

### Timeouts, connection pool and interceptors

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

`JQuickCurlConfig` is a global singleton that manages timeouts, the connection pool, retries, redirects and interceptors. A single method can override its timeouts with `@JTimeout`.

### Implemented curl options

| Category | Supported format | Purpose |
| --- | --- | --- |
| Request method | `-X <METHOD>`, `--request <METHOD>` | Select the HTTP method |
| Headers | `-H 'Name: value'`, `--header 'Name: value'` | Add a request header |
| Cookies | `-b 'name=value'`, `--cookie`, `-b @file` | Send cookies or load a cookie file |
| Cookie jar | `-c <file>`, `--cookie-jar <file>` | Persist `Set-Cookie` values to a file |
| Request data | `-d`, `--data`, `--data-ascii`, `--data-binary`, `--data-raw` | Send a request body |
| Form encoding | `--data-urlencode 'key=value'` | Send URL-encoded form data |
| Basic authentication | `-u 'user:password'`, `--user` | Generate a Basic Authorization header |
| Redirects | `-L`, `--location`, `--max-redirs <N>` | Follow redirects and cap the count |
| File upload | `-F 'file=@/path/to/file'`, `--form 'key=value'` | Multipart upload or form field |
| File download | `-o './file'`, `--output './file'` | Write response bytes to a local file |
| Proxy | `-x 'host:port'`, `--proxy`, `--socks5-hostname` | Use an HTTP or SOCKS5 proxy |
| Protocol and logging | `--http2`, `-k`, `--insecure`, `-v`, `--verbose`, `-s`, `--silent` | HTTP/2, skip cert checks, verbose or silent output |

The options above are covered by the parser and the test suite. Verify any unlisted curl option before relying on it.

## Documentation

- [apis.xml](./src/main/resources/apis.xml) — XML API definition sample.
- [Jquick-curl.dtd](./src/main/resources/paohaijiao/dtd/Jquick-curl.dtd) — DTD for the XML definition file.
- [README-CN.md](./README-CN.md) — Simplified Chinese documentation.
- [Releases](https://github.com/dromara/jquick-curl/releases) — version history and release notes.
- [Issues](https://github.com/dromara/jquick-curl/issues) — bug reports and feature requests.

## Contributing

Contributions of any kind are welcome.

1. Read [CONTRIBUTING-EN.md](./CONTRIBUTING-EN.md) (or [CONTRIBUTING.md](./CONTRIBUTING.md)) before making changes.
2. Include the version, environment, minimal reproduction and full error output when opening an issue.
3. Add or update tests before submitting a pull request, and keep each change focused.
4. Never commit secrets, tokens, personal data or production configuration.

## License

jquick-curl is released under the [Apache License 2.0](./LICENSE). When using, modifying or distributing the project, comply with the license terms, including copyright, patent and notice requirements.
