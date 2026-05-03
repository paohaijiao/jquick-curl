# JQuickCurl - Java HTTP Client Framework Based on Curl
## [简体中文](./README) | English
## 📖 Project Introduction
JQuickCurl is a high-performance, easy-to-use HTTP client framework designed for Java developers.
It is deeply adapted to the concurrency features of Java 8, and its core advantage lies in directly 
converting cURL commands into executable HTTP request logic - without the need to manually write 
underlying HTTP client code such as RestTemplate and OkHttp, and without paying attention to tedious 
details such as request construction, parameter encapsulation, and response parsing. By simply passing 
in familiar cURL commands, the framework can automatically complete request conversion and execution, 
allowing developers to quickly implement various HTTP operations such as GET, POST, PUT, DELETE in the
most intuitive way, significantly reducing network request development costs while ensuring request 
efficiency and stability.
## 📊 Project Status

[![License](https://img.shields.io/badge/License-Apache%202.0-5470c6.svg)](https://opensource.org/licenses/Apache-2.0)
[![Version](https://img.shields.io/badge/Version-1.2.0-91cc75.svg)](https://github.com/paohaijiao/jquick-curl/releases)
[![Downloads](https://img.shields.io/badge/Downloads-15K/month-fac858.svg)](https://maven-badges.herokuapp.com/maven-central/io.paohaijiao/jquick-curl)
[![Contributors](https://img.shields.io/badge/Contributors-24-3ba272.svg)](https://github.com/paohaijiao/jquick-curl/graphs/contributors)
[![Build](https://img.shields.io/badge/Build-Passing-ee6666.svg)](https://github.com/paohaijiao/jquick-curl/actions)
[![Test Coverage](https://img.shields.io/badge/Coverage-92%25-73c0de.svg)](https://github.com/paohaijiao/jquick-curl)
[![Issues](https://img.shields.io/badge/Issues-12-9a60b4.svg)](https://github.com/paohaijiao/jquick-curl/issues)
## 🌟 Core Label
⚡ High Performance | 🎯 User Friendly | 🔧 Flexible | 📦 LightWeight
## 📖 目录
- [✨ Characteristic Introduction](#Characteristic Introduction)
- [🚀 Quick Start](#Quick Start)
- [🔧 Core Functionality](#Core Functionality)
  - [HTTP method support](#HTTP method support)
  - [File Operations](#File Operations)
  - [Advanced Features](#Advanced Features)
- [📝 Configuration Method](#Configuration Method)
  - [Annotation Configuration](#Annotation Configuration)
  - [XML Configuration](#XML Configuration)
- [🧪 Testing Guide](#Testing Guide)
- [📚 API Reference](#API Reference)
- [🔍 FAQ](#FAQ)
- [🤝 Contribution Guide](#Contribution Guide)
- [📄 License](#License)
- [💝 Support US](#Support US)
## 🚀 Core Feature
- CURL style API: Define HTTP requests using cURL command syntax
- Multi protocol support: GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, TRACE
- File upload and download: supports single file, multiple file upload, and file download
- Annotation driven: Simplify API definition using @ JCurlCommand annotation
- Proxy mode: supports dynamic proxy generation API client
- XML configuration: Supports configuring API interfaces in XML format
- Variable replacement: supports global variable and parameter replacement
- Lambda support: provides a way to call Lambda expressions
- Interceptor: Supports request/response interceptors
- Batch execution: supports batch execution of cURL commands

## User Guide
### Basic Grammar
```bash
# Universal format
curl [options] [URL]

# Example: GET request
curl https://api.example.com/user
# Example: POST request with request header
curl -X POST -H "Content-Type: application/json" -d '{"name":"test"}' https://api.example.com/user
### Basic Options
# Request Method
-X, --request <方法>        # Specify the request method（GET/POST/PUT/DELETE/PATCH/HEAD/OPTIONS/TRACE）
# Request Header
-H, --header <请求头>       # Add HTTP request header
# Data transmission (POS/PUT specific)
-d, --data <数据>           # Sending regular data
--data-ascii <数据>         # Send ASCII format data
--data-binary <数据>        # Sending binary data
--data-raw <数据>           # Sending unprocessed raw data
--data-urlencode <数据>     # Send URL encoded form data
# Authorization related
-u, --user <用户:密码>      # Server username and password
# redirect
-L, --location              # Follow redirect
--max-redirs <次数>         # Maximum number of redirects
# output control
-o, --output <文件>         # Write output to a file instead of standard output
# File/Form Upload
-F, --form <名称=内容>      # Specify multi part form data
# Agency related
-x, --proxy <[协议://]主机[:端口]>  #Use HTTP/HTTPS proxy
--socks5-hostname <主机[:端口]>     # SOCKS5 Proxy
# Protocol/Security
--http2                     # Using HTTP/2 protocol
-k, --insecure              # Allow insecure server connections
```

## 📦 Quick Start
### 1. Add dependency
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-curl</artifactId>
    <version>${latest.version}</version>
</dependency>
```
## 2. Basic Usage
### Method 1: Annotation method
> Suitable for simple and scattered API calls, closely related to configuration and code: it is necessary to define the input and output parameters of the interface
> Features: Declare curl commands directly on interface methods through @ JCurlCommand annotation, suitable for rapid development and debugging
- **Declare an interface**, annotate the curl command with the @ JCurlCommand annotation, and pass parameters through the ${variable name} placeholder.
```java
    import java.util.List;
    public interface UserService {
        @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
        JGithubAuth retriveUser(JQuickCurlReq req);
    }
```
> Initiate call
> Create a proxy object using JCurlInvoker. creatProxy () * *, passing parameters through JQuickCurlReq.
> You can also use the **Lamda(::)** method of JCurlInvoker.invoice to call the rest interface


```java
    @Test
    public  void retriveUser() throws Exception {
        ApiService api = JCurlInvoker.createProxy(ApiService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "1234567@qq.com");
        req.put("password", "123456");
        JGithubAuth result = api.retriveUser(req);
        System.out.println(result);
    }
    @Test
    public  void testMethod() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        JUser result = JCurlInvoker.invoke(UserServiceImpl::getUserById, req,JUser.class);
        System.out.println(result);
    }
```

### 2.2 Method 2: XML configuration method
> Features: Define curl commands in an XML file, declare method signatures for interfaces, and separate configuration from code.
> Suitable for unified management of multiple APIs, with separate configuration and code for easy maintenance
> Step 1: Write an XML configuration file (apis. xml)
> Create an XML file in the resources directory and define each API using the **<curl>** tag.

```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE curls PUBLIC "-//PAOHAIJIAO//DTD API CURL 1.0//EN"
            "classpath:paohaijiao/dtd/Jquick-curl.dtd">
    <!-- 
      XML配置说明：
      - namespace：绑定对应的Java接口全类名
      - curl节点：每个节点对应一个接口方法
        - name：方法名（需与Java接口方法名一致）
        - returnClass：方法返回值类型（全类名）
     -->
    <curls namespace="com.github.paohaijiao.test.xml.UserApi">
        <curl name="users" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X POST http://localhost:8080/api/users/createUser \
            -H "Content-Type: application/json" \
            -d '{"name":"John Doe","email":"john@example.com"}'
        </curl>
    </curls>
```
>Define an interface
>The method name in the interface must correspond one-to-one with the name attribute of the * *<curl>tag in XML.
>Collaborate with @ Param annotation **to pass dynamic parameters**
```java
    public interface UserApi {
        JUser users(@Param("参数1") String 参数1, @Param("参数2") String 参数2);
    }
```
>Initiate call
>Load XML configuration through factory mode and **create API proxy objects**.
```java
    @Test
    public  void users() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "123456@qq.com");
        req.put("password", "123456");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        JUser user=userApi.users(req);
        System.out.println(user);
    }
```

##  🔧  Detailed functional examples
### Method 1: Annotation Method
```java

public interface UserService {
    /**
     * 获取用户列表
     * GET请求：无请求体，直接通过URL获取资源
     */
    @JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
    List<JUser> all(JQuickCurlReq req);
    /**
     * 根据用户ID查询单个用户信息
     * GET请求：无请求体，直接通过URL获取资源
     */
    @JCurlCommand("curl -X GET http://localhost:8080/api/users/1")
    JUser getUserById(JQuickCurlReq req);
    /**
     * 创建新用户
     * POST请求：携带JSON格式请求体，指定Content-Type为application/json
     */
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/createUser \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe\",\"email\":\"john@example.com\"}'")
    JUser users(JQuickCurlReq req);
    /**
     * 全量更新用户信息
     * PUT请求：替换指定ID的完整用户信息，需传递全部字段
     */
    @JCurlCommand("curl -X PUT http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Updated\",\"email\":\"john.updated@example.com\"}'")
    JUser usersPut(JQuickCurlReq req);
    /**
     * PATCH请求：局部更新用户信息（仅修改需要变更的字段）
     */
    @JCurlCommand("curl -X PATCH http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Patched\"}'")
    JUser usersPatch(JQuickCurlReq req);
    /**
     * 删除指定ID的用户
     * DELETE请求：无返回值（Void
     */
    @JCurlCommand("curl -X DELETE http://localhost:8080/api/users/1")
    Void usersDelete(JQuickCurlReq req);
    /**
     * HEAD请求：仅获取响应头信息（-I参数），无响应体
     */
    @JCurlCommand("curl  -X HEAD -I http://localhost:8080/api/users/1")
    Void usersHead(JQuickCurlReq req);
    /**
     * OPTIONS请求：获取服务器支持的HTTP方法列表
     */
    @JCurlCommand("curl -X OPTIONS http://localhost:8080/api/users/1")
    JResult usersOptions(JQuickCurlReq req);
    /**
     * TRACE请求：回显服务器收到的请求，用于调试
     */
    @JCurlCommand("curl -X TRACE http://localhost:8080/api/users/trace \\\n" +
            "-H \"Content-Type: text/plain\" \\\n" +
            "-d \"This is a trace request body\"")
    String usersTrace(JQuickCurlReq req);
    /**
     * 上传单个文件
     * -F参数：指定multipart/form-data格式，@符号后为本地文件路径
     */
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    String upload(JQuickCurlReq req);
    /**
     * 批量上传多个文件
     * 多个-F参数：同名参数（files）传递多个文件，服务端接收文件列表
     */
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-multiple \\\n" +
            "-F \"files=@D:\\test\\test.txt\"-F \"files=@D:\\test\\test1.txt\"")
    String upload1(JQuickCurlReq req);
    /**
     * 下载文件到指定路径
     * --output参数：将响应内容写入本地文件，返回字节数组（byte[]）便于处理
     */
    @JCurlCommand("curl -X GET http://localhost:8080/api/users/download/test.txt \\\n" +
            "--output 'd://test//download.txt'")
    byte[] download(JQuickCurlReq req);
    /**
     * 上传文件并携带额外表单参数
     * 混合-F参数：既有普通表单字段（userId/username），也有文件字段（file）
     */
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-with-params \\\n" +
            "-F \"userId=123\" \\\n" +
            "-F \"username=john\" \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    String uploadWithPostParams(JQuickCurlReq req);
}
```

```string
    @Test
    public  void retriveUser() throws Exception {
        ApiService api = JCurlInvoker.createProxy(ApiService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JGithubAuth result = api.retriveUser(req);
        System.out.println(result);
    }
    @Test
    public  void all() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.getUserById(req);
        System.out.println(result);
    }
    @Test
    public  void users() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.users(req);
        System.out.println(result);
    }
    @Test
    public  void usersPut() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.usersPut(req);
        System.out.println(result);
    }

    @Test
    public  void usersPatch() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JUser result = api.usersPatch(req);
        System.out.println(result);
    }

    @Test
    public  void usersDelete() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        api.usersDelete(req);
        System.out.println();
    }
    @Test
    public  void head() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        api.usersHead(req);
        System.out.println();
    }
    @Test
    public  void usersOptions() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        JResult jResult=api.usersOptions(req);
        System.out.println(jResult);
    }
    @Test
    public  void usersTrace() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        String jResult=api.usersTrace(req);
        System.out.println(jResult);
    }
    @Test
    public  void upload() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        String jResult=api.upload(req);
        System.out.println(jResult);
    }
    @Test
    public  void upload1() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        String jResult=api.upload1(req);
        System.out.println(jResult);
    }
    @Test
    public  void downloadByte() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        byte[] bytes=api.download(req);
        Path path = Paths.get("d://test/xx1.txt");
        Files.write(path, bytes, StandardOpenOption.CREATE);
        System.out.println("jResult");
    }
    @Test
    public  void uploadWithPostParams() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        String bytes=api.uploadWithPostParams(req);
        System.out.println(bytes);
    }
```
## XML Detailed Configuration Method
### 1. Define XML configuration file (apis. xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE curls PUBLIC "-//PAOHAIJIAO//DTD API CURL 1.0//EN"
            "classpath:paohaijiao/dtd/Jquick-curl.dtd">
    <curls namespace="com.github.paohaijiao.test.xml.UserApi">
        <curl name="all" returnClass="java.util.List">
            curl -X GET --location 'http://localhost:8080/api/users/all'
        </curl>
    
        <curl name="getUserById" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X GET http://localhost:8080/api/users/1
        </curl>
        <curl name="getUserByIdVariable" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X GET #{host} \
            <if test="a==1"> -H "Content-Type: application/json" </if>
        </curl>
    
        <curl name="users" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X POST http://localhost:8080/api/users/createUser \
            -H "Content-Type: application/json" \
            -d '{"name":"John Doe","email":"john@example.com"}'
        </curl>
        <curl name="usersByVariable" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X POST http://localhost:8080/api/users/createUser \
            -H "Content-Type: application/json" \
            -d '{"name":#{name},"email":#{email}}'
        </curl>
    
        <curl name="usersPut" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X PUT http://localhost:8080/api/users/1 \
            -H "Content-Type: application/json" \
            -d '{"name":"John Doe Updated","email":"john.updated@example.com"}'
        </curl>
    
        <curl name="usersPatch" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X PATCH http://localhost:8080/api/users/1 \
            -H "Content-Type: application/json" \
            -d '{"name":"John Doe Patched"}'
        </curl>
    
        <curl name="usersDelete" returnClass="java.lang.Void">
            curl -X DELETE http://localhost:8080/api/users/1
        </curl>
    
        <curl name="usersHead" returnClass="java.lang.Void">
            curl -X HEAD -I http://localhost:8080/api/users/1
        </curl>
    
        <curl name="usersOptions" returnClass="com.github.paohaijiao.model.JResult">
            curl -X OPTIONS http://localhost:8080/api/users/1
        </curl>
    
        <curl name="usersTrace" returnClass="java.lang.String">
            curl -X TRACE http://localhost:8080/api/users/trace \
            -H "Content-Type: text/plain" \
            -d "This is a trace request body"
        </curl>
    
        <curl name="upload" returnClass="java.lang.String">
            curl -X POST http://localhost:8080/api/users/upload \
            -F "file=@D:\test\pie.xlsx"
        </curl>
    
        <curl name="upload1" returnClass="java.lang.String">
            curl -X POST http://localhost:8080/api/users/upload-multiple \
            -F "files=@D:\test\pie.xlsx" \
            -F "files=@D:\test\pie2.xlsx"
        </curl>
    
        <curl name="download" returnClass="byte[]">
            curl -X GET http://localhost:8080/api/users/download/pie.xlsx \
            -H "Content-Type: application/octet-stream" \
            --output 'd://test//piett.xlsx'
        </curl>
    
        <curl name="uploadWithPostParams" returnClass="java.lang.String">
            curl -X POST http://localhost:8080/api/users/upload-with-params \
            -F "userId=123" \
            -F "username=john" \
            -F "file=@D:\test\pie.xlsx"
        </curl>
</curls>
```
### 2. Define Java interface (bound with XML configuration)
```java

public interface UserApi {

    List<JUser> all(JQuickCurlReq req);

    JUser getUserById(JQuickCurlReq req);

    JUser getUserByIdVariable(@Param("host")String host,@Param("a") int a);

    JUser users(JQuickCurlReq req);

    JUser usersByVariable(@Param("name")String name,@Param("email")String email);

    JUser usersPut(JQuickCurlReq req);

    JUser usersPatch(JQuickCurlReq req);

    Void usersDelete(JQuickCurlReq req);

    Void usersHead(JQuickCurlReq req);

    void usersOptions(JQuickCurlReq req);

    String usersTrace(JQuickCurlReq req);

    String upload(JQuickCurlReq req);

    String upload1(JQuickCurlReq req);

    byte[] download(JQuickCurlReq req);

    String uploadWithPostParams(JQuickCurlReq req);
}
```
### 3. Create an instance using the factory and call it
```java
     @Test
    public  void all1() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        System.out.println(factory);
        UserApi userApi = factory.createApi(UserApi.class);
        List<JUser> list =userApi.all(req);
        System.out.println(list);
    }
    @Test
    public  void testMethod() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        System.out.println(factory);
        UserApi userApi = factory.createApi(UserApi.class);
        JUser list =userApi.getUserById(req);
        System.out.println(list);
    }
    @Test
    public  void getUserByIdVariable() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        System.out.println(factory);
        UserApi userApi = factory.createApi(UserApi.class);
        JUser list =userApi.getUserByIdVariable("http://localhost:8080/api/users/1",1);
        System.out.println(list);
    }
    @Test
    public  void users() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(factory);
        System.out.println(userApi.users(req));
    }
    @Test
    public  void usersPut() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersPut(req));
    }
    @Test
    public  void usersPatch() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersPatch(req));
    }
    @Test
    public  void usersDelete() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersDelete(req));
    }
    @Test
    public  void usersHead() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersHead(req));
    }
    @Test
    public  void usersOptions() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        userApi.usersOptions(req);
        System.out.println();
    }
    @Test
    public  void usersByVariable() throws Exception {
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.usersByVariable("\"张三\"","\"aa@qq.com\""));
    }
    @Test
    public  void upload() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.upload(req));
    }
    @Test
    public  void upload1() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        System.out.println(userApi.upload1(req));
    }
    @Test
    public  void download() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        byte[] r=userApi.download(req);
        Path path = Paths.get("d://test/pief.xlsx");
        Files.write(path, r, StandardOpenOption.CREATE);
    }
    @Test
    public  void uploadWithPostParams() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        JQuickParseHandler parser = new JQuickCurlXmlParseFactory();
        JQuickFactory factory = new JQuickXmlFactory(parser,"apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        String r1=userApi.uploadWithPostParams(req);
        System.out.println(r1);
    }
```
##  🛠  Advanced features
### 1. batch execution
```java
/**
 * 批量执行curl命令
 * JQuickCurlBatchRunner：批量执行器，支持一次性执行多个curl命令
 * runCurlCommands：执行指定的批量命令类，统一返回JResult类型的结果列表
 */
public static void main(String[] args) throws Exception {
    JQuickCurlBatchRunner batch= new JQuickCurlBatchRunner();
    List<JQuickCurlResponseBody> list=batch.runCurlCommands(new JCurlBatchCommandTest(),JQuickCurlResponseBody.class);
    System.out.println(list);
}
```
### 2. Global variable support
```java
/**
* 命令中使用${变量名}占位符，运行时从JQuickCurlReq中取值替换
* 适用场景：通用配置（如认证信息、基础域名），避免硬编码
  */
    public interface ApiService {
        // 调用示例（${字段名} 字段级别）
        @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user -X GET")
        JGithubAuth retriveUser(JQuickCurlReq req);
    }
    @Test
    public  void retriveUser() throws Exception {
        ApiService api = JCurlInvoker.createProxy(ApiService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        // 给占位符${user}/${password}赋值
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        // 执行请求，框架自动替换变量
        JGithubAuth result = api.retriveUser(req);
    }
```
### 3. Parameterized interface method
```java
    /**
    * 接口方法参数绑定（#{参数名}占位符 + @Param注解）
    * 适用场景：动态拼接请求体/URL，直接使用方法入参，无需通过JQuickCurlReq传递
    * 注意：占位符格式为#{参数名}，需与@Param注解的value一致
      */
    public interface ApiService {
        @JCurlCommand("curl -X POST http://localhost:8080/api/users/createUser \\\n" +
                "-H \"Content-Type: application/json\" \\\n" +
                "-d '{\"name\":#{name},\"email\":#{email}}'")
        JUser usersByVariable(@Param("name") String name, @Param("email") String email);
    }
    // 调用示例（直接传参（#{字段名}），更符合Java接口调用习惯）
    @Test
    public  void retriveUser() throws Exception {
        UserService api = JCurlInvoker.createProxy(UserService.class);
        JUser user = api.usersByVariable("John Doe", "john@example.com");
    }
```
### 4. interceptor
### 4. Request/Response Interceptor

```java
@Slf4j
public class CustomInterceptor implements Interceptor {

   private static final Charset UTF8 = StandardCharsets.UTF_8;

   private final JCurlLevelLog level;

   public JLoggingInterceptor() {
      this(JCurlLevelLog.ALL);
   }

   public JLoggingInterceptor(JCurlLevelLog level) {
      this.level = level;
   }

   @Override
   public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      // 示例：统一添加Token请求头
      // request.addHeader("Authorization", "Bearer " + getToken());
      Response response;
      try {
         response = chain.proceed(request);
      } catch (Exception e) {
         log.error("<-- HTTP FAILED: " + e);
         throw e;
      }
      // 示例：统一添加响应
      return response;
   }
    @Override
    public void init()  {
        // 全局配置拦截器（生效于所有请求）
        JQuickCurlConfig config = JQuickCurlConfig.getInstance();
        // 添加自定义拦截器（支持添加多个，按添加顺序执行）
        config.addInterceptor(new CustomInterceptor());
    }
```
##  📋  Test case examples
### 1. Unit testing (basic functional verification)
```java
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * JCurlInvoker 核心功能单元测试
 * 验证代理调用、参数传递、文件下载等基础功能
 */
public class JCurlInvokerInvokeTest {
    
    /**
     * 测试基础认证接口调用（全局变量替换）
     * 验证${user}/${password}占位符替换和接口返回值解析
     */
    @Test
    public void retriveUser() throws Exception {
        // 创建API代理实例
        ApiService api = JCurlInvoker.createProxy(ApiService.class);
        
        // 准备请求参数（绑定全局变量）
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        
        // 执行请求并获取结果
        JGithubAuth result = api.retriveUser(req);
        System.out.println(result); // 打印结果用于调试
    }
    
    /**
     * 测试文件下载功能
     * 验证字节数组返回值处理及本地文件写入
     */
    @Test
    public void downloadByte() throws Exception {
        // 创建API代理实例
        UserService api = JCurlInvoker.createProxy(UserService.class);
        // 准备请求参数
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        // 执行下载请求，获取字节数组
        byte[] bytes = api.download(req);
        // 将下载的字节写入本地文件
        Path path = Paths.get("d://test/xx1.txt");
        Files.write(path, bytes, StandardOpenOption.CREATE);
    }
}
```
### 2. XML configuration testing (configured interface validation)
```java
import org.junit.Test;
import java.util.List;
/**
* XML配置方式的接口调用测试
* 验证XML配置加载、接口代理生成、配置化接口调用
  */
public class CurlApiExample {

  /**
    * 测试XML配置的接口调用
    * 验证apis.xml配置加载及all方法的执行结果
      */
      @Test
      public void all1() throws Exception {
          // 准备请求参数
          JQuickCurlReq req = new JQuickCurlReq();
          req.put("user", "xsaxsa@qq.com");
          req.put("password", "zaZAzaZA");
          // 加载XML配置文件，创建工厂实例
          CurlApiFactory factory = new CurlApiFactory("apis.xml");
          // 生成XML绑定的接口代理
          UserApi userApi = factory.createApi(UserApi.class);
          // 执行接口方法，获取结果并打印
          List<JUser> list = userApi.all(req);
          System.out.println(list);
      }
}
```
## 📚 API reference
### Core Class
| Class Name         | Functional Description|
|--------------------|----------|
| `JCurlInvoker`     | Core caller, supporting proxy creation and Lambda calling|
| `JQuickCurlReq`    | Request parameter container, used to store request headers, parameters, variables, etc |
| `JQuickCurlConfig` | Global configuration class, managing interceptors, global parameters, and other configurations |
| `JContext`         | Execute context and store context information for requests/responses |
| `JResult`          | Universal response results, encapsulating response status, data, exceptions, etc |
| `CurlApiFactory`   | XML configuration factory, used to load XML configurations and generate interface proxies|

### Core Annotations
| Annotation Name| Functional Description|
|--------|----------|
| `@JCurlCommand` | Annotate on the interface method and define the corresponding cURL command |
| `@Param` | Method parameter mapping annotation, bind # {parameter name} placeholder in cURL command |

## 🤝 Contribution Guide
We warmly welcome community contributions, and you can participate in the following ways：
- Submit Issue: Provide bug feedback, suggest new features, optimize documentation
- Submit Pull Request: Fix bugs, add new features, and improve test cases
- Participate in discussion: Exchange technical solutions and usage experience in the issue

### Contribution standards
1. Fork this project to personal warehouse
2. Create feature branch （`feature/xxx` or `fix/xxx`）
3. Submit code and maintain consistent code style
4. Write/update test cases to ensure functionality is available
5. Submit a PR that clearly describes the changes and the issues addressed
6. 
## 📄 License
This project is licensed under the **Apache License 2.0** open-source license. For details, please refer to the [LICENSE](LICENSE) file.

## 💖 Support the Project
If this project is helpful to you, please support us in the following ways:
- ⭐ **Star** the project: Click the Star button in the upper right corner of the GitHub repository
- 🐛 **Report issues**: Submit an Issue to report problems or suggestions encountered during use
- 🔀 **Contribute code**: Submit a Pull Request to improve features or fix bugs

Thank you for using this open-source project! It is completely free and will be continuously maintained, but developers truly need your support.

---

## **How to Support Us**

1. **Buy Me a Coffee**  
   If this project has saved you time or money, please consider supporting me with a small donation.

2. **Where Your Donation Goes**
- Covering server costs to keep the project running.
- Developing new features to deliver more value.
- Optimizing documentation to enhance user experience.

3. **Every Penny Counts**  
   Even a donation of just one cent can motivate me to debug code late into the night!
## **Why Donate?**
✔️ Keep the project forever free and ad-free.  
✔️ Support timely responses to issues and community inquiries.  
✔️ Realize planned future features.

Thank you for being a partner in making the open-source world better!

--- 

### **Additional Notes**
- Maintenance of this project and related products.
- Your support ensures its sustainability and growth.
---

## **🌟 Support Us Now**
Feel free to leave a message via [email](mailto:goudingcheng@gmail.com) when making a donation. Your name will be listed in the **"Special Thanks"** section of the project's README file!
![Pay Now](./src/main/resources/pay/paynow.jpg)
![TNG go](./src/main/resources/pay/tngGo.jpg)

---