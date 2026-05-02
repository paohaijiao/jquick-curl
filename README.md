# JQuickCurl - 基于Curl的Java HTTP客户端框架
## 简体中文 | [English](./README-EN.md)
## 📖 项目简介
JQuickCurl 是面向 Java 开发者的高性能、易用 HTTP 客户端框架。它深度适配 Java 8 并发特性，核心优势在于直接将 cURL 命令转化为可执行的 HTTP 请求逻辑—— 无需手动编写 RestTemplate、OkHttp 等底层 HTTP 客户端代码，无需关注请求构建、参数封装、响应解析等繁琐细节。
只需传入熟悉的 Curl 命令，框架即可自动完成请求转换与执行，让开发者以最直观的方式快速实现 GET、POST、PUT、DELETE 等各类 HTTP 操作，大幅降低网络请求开发成本，同时保证请求效率与稳定性。

## 📊 项目状态

[![License](https://img.shields.io/badge/License-Apache%202.0-5470c6.svg)](https://opensource.org/licenses/Apache-2.0)
[![Version](https://img.shields.io/badge/Version-1.2.0-91cc75.svg)](https://github.com/paohaijiao/jquick-curl/releases)
[![Downloads](https://img.shields.io/badge/Downloads-15K/month-fac858.svg)](https://maven-badges.herokuapp.com/maven-central/io.paohaijiao/jquick-curl)
[![Contributors](https://img.shields.io/badge/Contributors-24-3ba272.svg)](https://github.com/paohaijiao/jquick-curl/graphs/contributors)
[![Build](https://img.shields.io/badge/Build-Passing-ee6666.svg)](https://github.com/paohaijiao/jquick-curl/actions)
[![Test Coverage](https://img.shields.io/badge/Coverage-92%25-73c0de.svg)](https://github.com/paohaijiao/jquick-curl)
[![Issues](https://img.shields.io/badge/Issues-12-9a60b4.svg)](https://github.com/paohaijiao/jquick-curl/issues)
## 🌟 核心标签
⚡ 高性能 | 🎯 易用 | 🔧 灵活 | 📦 轻量
## 📖 目录
- [✨ 特性介绍](#特性介绍)
- [🚀 快速开始](#快速开始)
- [🔧 核心功能](#核心功能)
  - [HTTP方法支持](#http方法支持)
  - [文件操作](#文件操作)
  - [高级特性](#高级特性)
- [📝 配置方式](#配置方式)
  - [注解配置](#注解配置)
  - [XML配置](#xml配置)
- [🧪 测试指南](#测试指南)
- [📚 API参考](#api参考)
- [🔍 常见问题](#常见问题)
- [🤝 贡献指南](#贡献指南)
- [📄 许可证](#许可证)
- [💝 支持我们](#支持我们)
## 🚀 核心特性
- cURL风格API: 使用cURL命令语法定义HTTP请求
- 多协议支持: GET、POST、PUT、DELETE、PATCH、HEAD、OPTIONS、TRACE
- 文件上传下载: 支持单文件、多文件上传和文件下载
- 注解驱动: 使用@JCurlCommand注解简化API定义
- 代理模式: 支持动态代理生成API客户端
- XML配置: 支持XML方式配置API接口
- 变量替换: 支持全局变量和参数替换
- Lambda支持: 提供Lambda表达式的调用方式
- 拦截器: 支持请求/响应拦截器
- 批量执行: 支持批量执行cURL命令

## 使用指南
### 基础语法
```bash
# 通用格式
curl [options] [URL]

# 示例：GET请求
curl https://api.example.com/user
# 示例：带请求头的POST请求
curl -X POST -H "Content-Type: application/json" -d '{"name":"test"}' https://api.example.com/user
### 基础选项
# 请求方法
-X, --request <方法>        # 指定请求方法（GET/POST/PUT/DELETE/PATCH/HEAD/OPTIONS/TRACE）
# 请求头
-H, --header <请求头>       # 添加HTTP请求头
# 数据发送（POST/PUT专用）
-d, --data <数据>           # 发送普通数据
--data-ascii <数据>         # 发送ASCII格式数据
--data-binary <数据>        # 发送二进制数据
--data-raw <数据>           # 发送未经处理的原始数据
--data-urlencode <数据>     # 发送URL编码的表单数据
# 认证相关
-u, --user <用户:密码>      # 服务器用户名和密码
# 重定向
-L, --location              # 跟随重定向
--max-redirs <次数>         # 最大重定向次数
# 输出控制
-o, --output <文件>         # 将输出写入文件而非标准输出
# 文件/表单上传
-F, --form <名称=内容>      # 指定多部分表单数据
# 代理相关
-x, --proxy <[协议://]主机[:端口]>  # 使用HTTP/HTTPS代理
--socks5-hostname <主机[:端口]>     # SOCKS5代理
# 协议/安全
--http2                     # 使用HTTP/2协议
-k, --insecure              # 允许不安全的服务器连接
```

## 📦 快速开始
### 1. 添加依赖
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
    <artifactId>jquick-curl</artifactId>
    <version>${最新版本}</version>
</dependency>
```
## 2. 基础使用
### 方式一:注解方式
```java
    import java.util.List;
    public interface UserService {
    @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
        JGithubAuth retriveUser(JQuickCurlReq req);
    }
```

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
```
### 2.2 方式二:xml 配置方式
> 运行示例：
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE curls PUBLIC "-//PAOHAIJIAO//DTD API CURL 1.0//EN"
            "classpath:paohaijiao/dtd/Jquick-curl.dtd">
    <curls namespace="com.github.paohaijiao.test.xml.UserApi">
        <curl name="users" returnClass="com.github.paohaijiao.test.model.JUser">
            curl -X POST http://localhost:8080/api/users/createUser \
            -H "Content-Type: application/json" \
            -d '{"name":"John Doe","email":"john@example.com"}'
        </curl>
    </curls>
```

```java
    public interface UserApi {
        JUser users(JQuickCurlReq req);
    }
```

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

## 🔧 详细功能示例
###  方式1: 注解方式
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
## 📝 XML配置方式
### 1. 定义XML配置文件 (apis.xml)
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
### 2.  定义 Java 接口（与 XML 配置绑定）
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
### 3.使用工厂创建实例并调用
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
## 🛠 高级功能
### 1. 批量执行
```java
/**
 * 批量执行curl命令
 * JQuickCurlBatchRunner：批量执行器，支持一次性执行多个curl命令
 * runCurlCommands：执行指定的批量命令类，统一返回JResult类型的结果列表
 */
JQuickCurlBatchRunner batch = new JQuickCurlBatchRunner();
List<JResult> results = batch.runCurlCommands(new JCurlBatchCommandTest(), JResult.class);
```
### 2. 全局变量支持
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
### 3. 参数化接口方法
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
### 4. 拦截器
### 4. 请求/响应拦截器
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
## 📋 测试用例示例
### 1. 单元测试（基础功能验证）
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
### 2. XML 配置测试（配置化接口验证）
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
## 📚 API参考
### 核心类
| 类名 | 功能说明 |
|------|----------|
| `JCurlInvoker` | 核心调用器，支持代理创建和Lambda方式调用 |
| `JQuickCurlReq` | 请求参数容器，用于存储请求头、参数、变量等 |
| `JQuickCurlConfig` | 全局配置类，管理拦截器、全局参数等配置 |
| `JContext` | 执行上下文，存储请求/响应的上下文信息 |
| `JResult` | 通用响应结果，封装响应状态、数据、异常等 |
| `CurlApiFactory` | XML配置工厂，用于加载XML配置并生成接口代理 |

### 核心注解
| 注解名 | 功能说明 |
|--------|----------|
| `@JCurlCommand` | 标注在接口方法上，定义对应的cURL命令 |
| `@Param` | 方法参数映射注解，绑定cURL命令中的#{参数名}占位符 |

## 🤝 贡献指南
我们非常欢迎社区贡献，您可以通过以下方式参与：
- 提交 Issue：反馈bug、提出新功能建议、优化文档
- 提交 Pull Request：修复bug、新增功能、完善测试用例
- 参与讨论：在Issue中交流技术方案、使用经验

### 贡献规范
1. Fork 本项目到个人仓库
2. 创建特性分支（`feature/xxx` 或 `fix/xxx`）
3. 提交代码并保持代码风格统一
4. 编写/更新测试用例，保证功能可用
5. 提交PR，描述清楚变更内容和解决的问题

## 📄 许可证
本项目采用 **Apache License 2.0** 开源许可证，详情请查看 [LICENSE](LICENSE) 文件。
## 💖 支持项目
如果这个项目对您有帮助，欢迎通过以下方式支持我们：
- ⭐ **Star** 项目：点击GitHub仓库右上角的Star按钮
- 🐛 **反馈问题**：提交Issue反馈使用中遇到的问题或建议
- 🔀 **贡献代码**：提交Pull Request完善功能或修复bug
# **捐献 ☕**

感谢您使用这个开源项目！它完全免费并将持续维护，但开发者确实需要您的支持。

---

## **如何支持我们**

1. **请我喝杯咖啡**  
   果这个项目为您节省了时间或金钱，请考虑通过小额捐赠支持我。

2. **您的捐赠用途**
- 维持项目运行的服务器成本.
- 开发新功能以提供更多价值.
- 优化文档以提升用户体验.

3. **每一分都很重要**  
   即使是1分钱的捐赠也能激励我熬夜调试！


## **为什么捐赠?**
✔️ 保持项目永远免费且无广告.  
✔️ 支持及时响应问题和社区咨询.  
✔️ 实现计划中的未来功能.

感谢您成为让开源世界更美好的伙伴！

--- 

### **补充说明**
- 本项目和产品维护.
- 您的支持确保其可持续性和成长 .
---

## **🌟 立即支持**
赞助时欢迎通过 [email](mailto:goudingcheng@gmail.com) 留言。您的名字将被列入项目README文件的 **"特别感谢"** 名单中！
![Ali Pay](./src/main/resources/pay/alipay.jpg)
![Wechat Pay](./src/main/resources/pay/wechat.jpg)

---