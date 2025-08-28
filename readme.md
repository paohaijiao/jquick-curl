# jquickCurl 文档
简体中文 | [英文](./readme-en.md)
```string
    jquickcurl 是一个面向Java开发者的高性能、易用HTTP客户端框架。它充分利用Java 8的并发特性和OkHttp的功能，
简化网络请求处理并提升效率。该框架旨在提供类似cURL的编程接口，以优雅高效地实现GET、POST、PUT等HTTP操作。  
jquickcurl 强调跨平台兼容性，支持在多种操作系统环境中无缝部署，极大扩展了其适用场景，便于在服务端和移动应用开发中
集成并发挥高效特性。  通过简洁的API，jquickcurl 简化了服务间网络请求，降低了开发者在本地和微服务环境中HTTP集成的
复杂度。让开发者专注于业务逻辑，显著提高效率和性能。  无论是应对微服务的分布式挑战，还是云环境的高并发需求，jquickcurl 
凭借其灵活性和高效性，成为开发者确保快速可靠网络通信的重要工具。
```
## 使用指南
### 基础语法
```string
curl [options] [URL]
```
### 基础选项
```string
-X, --request <方法>  指定请求方法（GET、POST、PUT、DELETE、PATCH、HEAD、OPTIONS、TRACE）
-H, --header <请求头>  添加HTTP请求头
-d, --data <数据>     在POST/PUT请求中发送数据
--data-ascii <数据>   发送ASCII格式数据
--data-binary <数据>  发送二进制数据
--data-raw <数据>     发送未经处理的原始数据
--data-urlencode <数据> 发送URL编码的表单数据
-u, --user <用户:密码> 服务器用户名和密码
-L, --location        跟随重定向
--max-redirs <次数>   最大重定向次数
-o, --output <文件>   将输出写入文件而非标准输出
-F, --form <名称=内容> 指定多部分表单数据
-x, --proxy <[协议://]主机[:端口]> 使用代理
--socks5-hostname <主机[:端口]> SOCKS5代理
--http2               使用HTTP/2协议
-k, --insecure        允许不安全的服务器连接
```
# 目录

## 基础功能
- [1. 列表查询](#1-列表查询)
- [2. 获取单条数据](#2-获取单条数据)
- [3. POST请求](#3-post请求)
- [4. PUT请求](#4-put请求)
- [5. PATCH请求](#5-patch请求)
- [6. DELETE请求](#6-delete请求)
- [7. HEAD请求](#7-head请求)
- [8. OPTIONS请求](#8-options请求)
- [9. TRACE请求](#9-trace请求)

## 文件操作
- [10. 单文件上传](#10-单文件上传)
- [11. 多文件上传](#11-多文件上传)
- [12. 文件下载](#12-文件下载)
- [13. 带参数的文件上传](#13-带参数的文件上传)

## 高级功能
- [14. 批量执行](#14-批量执行)
- [15. Lambda支持](#15-lambda支持)
- [16. 基础认证](#16-基础认证)
- [17. 拦截器](#17-拦截器)
- [18. 全局变量支持](#18-全局变量支持)

## 附录
- [使用指南](#使用指南)
  - [基础语法](#基础语法)
  - [基础选项](#基础选项)
- [简介](#简介)

## 简介
本文档提供JCurlInvoker的全面使用示例。这是一个基于Java的HTTP客户端，通过cURL风格的注解简化API测试与集成。

## 基础请求

1. 列表查询
```java
@JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
List<JUser> all(JQuickCurlReq req);
```

```java
List<JUser> all(JQuickCurlReq req);
JQuickCurlReq req = new JQuickCurlReq();
JContext context = new JContext();
JQuickCurlConfig config = JQuickCurlConfig.getInstance();
Object result = JCurlInvoker.invoke(UserServiceImpl::all, req,JGithubAuth.class);
TypeToken<List<JUser>> typeToken = new TypeToken<List<JUser>>() {};
List<JUser> list = JCurlInvoker.invoke(
        UserServiceImpl::all,
        req,
        context,
        config,typeToken.getType()
);
```

2. 获取单条数据
```java
@JCurlCommand("curl -X GET http://localhost:8080/api/users/1")
JUser getUserById(JQuickCurlReq req);
```
```java
  UserService api = JCurlInvoker.createProxy(UserService.class);
  JQuickCurlReq req = new JQuickCurlReq();
  JUser result = api.getUserById(req);
```

3. POST请求
```java
    @JCurlCommand("curl -X POST http://localhost:8080/api/users \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe\",\"email\":\"john@example.com\"}'")
    JUser users(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JUser result = api.users(req);
```

4. PUT请求
```java
    @JCurlCommand("curl -X PUT http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Updated\",\"email\":\"john.updated@example.com\"}'")
    JUser usersPut(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JUser result = api.usersPut(req);
```

5. PATCH请求
```java
    @JCurlCommand("curl -X PATCH http://localhost:8080/api/users/1 \\\n" +
        "-H \"Content-Type: application/json\" \\\n" +
        "-d '{\"name\":\"John Doe Patched\"}'")
JUser usersPatch(JQuickCurlReq req);
```

```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JUser result = api.usersPatch(req);
```

6. DELETE请求
```java
@JCurlCommand("curl -X DELETE http://localhost:8080/api/users/1")
Void usersDelete(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
api.usersDelete(req);
```
7. HEAD请求
```java
 @JCurlCommand("curl  -X HEAD -I http://localhost:8080/api/users/1")
    Void usersHead(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
api.usersHead(req);
```

8. OPTIONS请求
```java
 @JCurlCommand("curl -X OPTIONS http://localhost:8080/api/users/1")
    JResult usersOptions(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JResult jResult=api.usersOptions(req);
```

9. TRACE请求
```java
 @JCurlCommand("curl -X TRACE http://localhost:8080/api/users/trace \\\n" +
            "-H \"Content-Type: text/plain\" \\\n" +
            "-d \"This is a trace request body\"")
```
```java
String usersTrace(JQuickCurlReq req);
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
String jResult=api.usersTrace(req);
```
10. 单文件上传
```java
@JCurlCommand("curl -X POST http://localhost:8080/api/users/upload \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
```
```java
 UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        String jResult=api.upload(req);
```

11. 多文件上传
```java
@JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-multiple \\\n" +
"-F \"files=@D:\\test\\test.txt\"-F \"files=@D:\\test\\test1.txt\"")
String upload1(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
String jResult=api.upload1(req);
```
12. 文件下载
```java
    @JCurlCommand("curl -X GET http://localhost:8080/api/users/download/test.txt \\\n" +
            "--output 'd://test//download.txt'")
    byte[] download(JQuickCurlReq req);
```
```java
 UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        byte[] bytes=api.download(req);
        Path path = Paths.get("d://test/xx1.txt");
        Files.write(path, bytes, StandardOpenOption.CREATE);
```
13. 带参数的文件上传
```java
 @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-with-params \\\n" +
            "-F \"userId=123\" \\\n" +
            "-F \"username=john\" \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    String uploadWithPostParams(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
String bytes=api.uploadWithPostParams(req);
```

14. 批量执行
```java
JQuickCurlBatchRunner batch= new JQuickCurlBatchRunner();
List<JResult> list=batch.runCurlCommands(new JCurlBatchCommandTest(),JResult.class);
```

15. Lambda支持
```java
JQuickCurlReq req = new JQuickCurlReq();
JUser result = JCurlInvoker.invoke(UserServiceImpl::getUserById, req,JUser.class);
```

16. 基础认证
```java
    @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
    JGithubAuth retriveUser(JQuickCurlReq req);
```
17. 拦截器
```string
如果你想在发起新的curl请求之前或之后处理一些业务逻辑，您可以实现拦截器接口 Interceptor 就像JLoggingInterceptor接口一样，并通过JQuickCurlConfig传递拦截器
```
18. 全局变量
```string
如果你想更改api主机、参数、方法和基于不同环境的任何其他内容，你可以定义变量
在代码中，如**${variableName}**，然后可以通过JContext引用传递不同的值，最后
curl请求将跟随您的variableName执行。
```
使用方式:
```java
@JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
JGithubAuth retriveUser(JQuickCurlReq req);
```
```java
 ApiService api = JCurlInvoker.createProxy(ApiService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JGithubAuth result = api.retriveUser(req);
```
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