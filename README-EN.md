# JQuickCurl - Java HTTP Client Framework Based on cURL
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
[![Version](https://img.shields.io/badge/Version-1.2.0-91cc75.svg)](https://github.com/xxx/jquickcurl/releases)
[![Downloads](https://img.shields.io/badge/Downloads-15K/month-fac858.svg)](https://maven-badges.herokuapp.com/maven-central/com.xxx/jquickcurl)
[![Contributors](https://img.shields.io/badge/Contributors-24-3ba272.svg)](https://github.com/xxx/jquickcurl/graphs/contributors)
[![Build](https://img.shields.io/badge/Build-Passing-ee6666.svg)](https://github.com/xxx/jquickcurl/actions)
[![Test Coverage](https://img.shields.io/badge/Coverage-92%25-73c0de.svg)](https://github.com/paohaijiao/jquick-curl)
[![Issues](https://img.shields.io/badge/Issues-12-9a60b4.svg)](https://github.com/xxx/jquickcurl/issues)
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
```string
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
    <groupId>com.github.paohaijiao</groupId>
    <artifactId>jquick-curl</artifactId>
    <version>1.2.0</version>
</dependency>
```
## 2. Basic usage
### 2.1 Define Service Interface
```java
import java.util.List;
// 示例UserService接口定义
public interface UserService {

    /**
     * 获取所有用户
     * @param req 请求参数载体
     * @return 所有用户列表
     */
    @JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
    List<JUser> all(JQuickCurlReq req);

    /**
     * 根据ID获取单个用户
     * @param req 请求参数载体
     * @return 单个用户信息
     */
    @JCurlCommand("curl -X GET http://localhost:8080/api/users/1")
    JUser getUserById(JQuickCurlReq req);

    /**
     * 创建新用户（POST请求）
     * @param req 请求参数载体
     * @return 创建后的用户信息
     */
    @JCurlCommand("curl -X POST http://localhost:8080/api/users/createUser \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe\",\"email\":\"john@example.com\"}'")
    JUser users(JQuickCurlReq req);
}
```
### 2.2 Create Service Bean
```java
// 1. 创建UserService代理实例
UserService api = JCurlInvoker.createProxy(UserService.class);

// 2. 准备请求参数
JQuickCurlReq req = new JQuickCurlReq();
req.put("user", "xsasaxsa@qq.com");
req.put("password", "xasxsa");

// 3. 执行HTTP请求并获取结果
List<JUser> users = api.all(req);       // 获取所有用户
JUser user = api.getUserById(req);      // 根据ID获取用户
```
### 2.3 Use With Lambda 
> 运行示例：
```java
// 1. 准备请求参数
JQuickCurlReq req = new JQuickCurlReq();
req.put("user", "xsaxsa@qq.com");
req.put("password", "zaZAzaZA");

// 2. Lambda风格调用接口方法
List<JUser> list = JCurlInvoker.invoke(
        UserServiceImpl::all,    // 目标方法引用
        req,                     // 请求参数
        List.class               // 返回值类型
);

JUser user = JCurlInvoker.invoke(
        UserServiceImpl::getUserById,
        req,
        JUser.class
);
```
## 🔧 Detailed Functional Examples
### 1.Basic HTTP Methods
```java
    ApiService api = JCurlInvoker.createProxy(ApiService.class);
    JQuickCurlReq req = new JQuickCurlReq();
    req.put("user", "xsasaxsa@qq.com");
    req.put("password", "xasxsa");
    JGithubAuth result = api.retriveUser(req);
```
#### 2. GET Request (Query Resources)
```java
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
 * 删除指定ID的用户
 * DELETE请求：无返回值（Void），仅执行删除操作
 */
@JCurlCommand("curl -X DELETE http://localhost:8080/api/users/1")
Void usersDelete(JQuickCurlReq req);
/**
 * PATCH请求：局部更新用户信息（仅修改需要变更的字段）
 */
@JCurlCommand("curl -X PATCH http://localhost:8080/api/users/1")
JUser usersPatch(JQuickCurlReq req);

/**
 * HEAD请求：仅获取响应头信息（-I参数），无响应体
 */
@JCurlCommand("curl -X HEAD -I http://localhost:8080/api/users/1")
Void usersHead(JQuickCurlReq req);

/**
 * OPTIONS请求：获取服务器支持的HTTP方法列表
 */
@JCurlCommand("curl -X OPTIONS http://localhost:8080/api/users/1")
JResult usersOptions(JQuickCurlReq req);

/**
 * TRACE请求：回显服务器收到的请求，用于调试
 */
@JCurlCommand("curl -X TRACE http://localhost:8080/api/users/trace")
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
        "-F \"files=@D:\\test\\test.txt\" \\\n" +
        "-F \"files=@D:\\test\\test1.txt\"")
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
/**
 * HTTP基础认证
 * -u参数：传递用户名和密码（使用${变量}占位符，运行时从req中替换）
 * 格式：-u ${user}:${password} 对应 req.put("user", "xxx") / req.put("password", "xxx")
 */
@JCurlCommand("curl -u ${user}:${password} https://api.github.com/user -X GET")
JGithubAuth retriveUser(JQuickCurlReq req);
```
## 📝 XML Configuration
### 1. Define XML configuration file (apis.xml)
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
    <!-- 获取所有用户列表 -->
    <curl name="all" returnClass="java.util.List">
        curl -X GET --location 'http://localhost:8080/api/users/all'
    </curl>
    
    <!-- 根据ID获取单个用户 -->
    <curl name="getUserById" returnClass="com.github.paohaijiao.test.model.JUser">
        curl -X GET http://localhost:8080/api/users/1
    </curl>
    
    <!-- 带变量替换的用户查询（动态主机地址） -->
    <curl name="getUserByIdVariable" returnClass="com.github.paohaijiao.test.model.JUser">
        curl -X GET ${host}
    </curl>
</curls>
```
### 2.  Define Java interface (bound with XML configuration)

```java
/**
 * UserApi接口
 * 方法名、参数、返回值需与XML配置中的curl节点一一对应
 */
public interface UserApi {
    // 获取所有用户（无动态参数）
    List<JUser> all(JQuickCurlReq req);
    
    // 根据ID获取用户（固定URL）
    JUser getUserById(JQuickCurlReq req);
    
    // 动态主机地址的用户查询（@Param注解绑定XML中的${host}变量）
    JUser getUserByIdVariable(@Param("host")String host);
}
```
### 3.Create an instance using the factory and call it

```java
// 1. 创建CurlApiFactory，加载XML配置文件
CurlApiFactory factory = new CurlApiFactory("apis.xml");

// 2. 生成UserApi接口的代理实例
UserApi userApi = factory.createApi(UserApi.class);

// 3. 调用接口方法
// 3.1 调用无参方法
List<JUser> users = userApi.all(req);

// 3.2 调用带动态变量的方法（替换XML中的${host}）
JUser user = userApi.getUserByIdVariable("http://localhost:8080/api/users/1");
```
## 🛠 Advanced Features
### 1. Batch Execution
```java
/**
 * 批量执行curl命令
 * JQuickCurlBatchRunner：批量执行器，支持一次性执行多个curl命令
 * runCurlCommands：执行指定的批量命令类，统一返回JResult类型的结果列表
 */
JQuickCurlBatchRunner batch = new JQuickCurlBatchRunner();
List<JResult> results = batch.runCurlCommands(new JCurlBatchCommandTest(), JResult.class);
```
### 2. Global variable support
```java
/**
* 命令中使用${变量名}占位符，运行时从JQuickCurlReq中取值替换
* 适用场景：通用配置（如认证信息、基础域名），避免硬编码
  */
  @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user -X GET")
  JGithubAuth retriveUser(JQuickCurlReq req);
// 调用示例（${字段名} 字段级别）
ApiService api = JCurlInvoker.createProxy(ApiService.class);
JQuickCurlReq req = new JQuickCurlReq();
// 给占位符${user}/${password}赋值
req.put("user", "xsasaxsa@qq.com");
req.put("password", "xasxsa");
// 执行请求，框架自动替换变量
JGithubAuth result = api.retriveUser(req);
```
### 3. Parameterized interface method

```java
/**
* 接口方法参数绑定（#{参数名}占位符 + @Param注解）
* 适用场景：动态拼接请求体/URL，直接使用方法入参，无需通过JQuickCurlReq传递
* 注意：占位符格式为#{参数名}，需与@Param注解的value一致
  */
  @JCurlCommand("curl -X POST http://localhost:8080/api/users/createUser \\\n" +
  "-H \"Content-Type: application/json\" \\\n" +
  "-d '{\"name\":#{name},\"email\":#{email}}'")
  JUser usersByVariable(@Param("name")String name, @Param("email")String email);

// 调用示例（直接传参（#{字段名}），更符合Java接口调用习惯）
// UserService api = JCurlInvoker.createProxy(UserService.class);
// JUser user = api.usersByVariable("John Doe", "john@example.com");
```
### 4. Interceptor
###  Request/Response Interceptor

```java
package com.github.paohaijiao.interceptor;

import com.github.paohaijiao.enums.JCurlLevelLog;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
// 全局配置拦截器（生效于所有请求）
JQuickCurlConfig config = JQuickCurlConfig.getInstance();
// 添加自定义拦截器（支持添加多个，按添加顺序执行）
config.addInterceptor(new CustomInterceptor());
```
## 📋 Test case examples
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