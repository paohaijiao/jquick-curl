# JQuickCurl - 基于cURL的Java HTTP客户端框架
简体中文 | [English](./README-EN)

📖 项目简介
        JQuickCurl 是一个面向Java开发者的高性能、易用HTTP客户端框架。它充分利用Java 8的并发特性，简化网络请求处理并提升效率。该框架旨在提供类似cURL的编程接口，以优雅高效地实现GET、POST、PUT等HTTP操作。

📊 项目状态

[![License](https://img.shields.io/badge/License-Apache%202.0-5470c6.svg)](https://opensource.org/licenses/Apache-2.0)
[![Version](https://img.shields.io/badge/Version-1.2.0-91cc75.svg)](https://github.com/xxx/jquickcurl/releases)
[![Downloads](https://img.shields.io/badge/Downloads-15K/month-fac858.svg)](https://maven-badges.herokuapp.com/maven-central/com.xxx/jquickcurl)
[![Contributors](https://img.shields.io/badge/Contributors-24-3ba272.svg)](https://github.com/xxx/jquickcurl/graphs/contributors)
[![Build](https://img.shields.io/badge/Build-Passing-ee6666.svg)](https://github.com/xxx/jquickcurl/actions)
[![Test Coverage](https://img.shields.io/badge/Coverage-92%25-73c0de.svg)](https://github.com/paohaijiao/jquick-curl)
[![Issues](https://img.shields.io/badge/Issues-12-9a60b4.svg)](https://github.com/xxx/jquickcurl/issues)

🚀 核心特性
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
```string
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
    <groupId>com.github.paohaijiao</groupId>
    <artifactId>jquick-curl</artifactId>
    <version>1.2.0</version>
</dependency>
```
## 2. 基础使用
### 2.1 定义 Service 接口
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
### 2.2 使用代理模式调用
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
### 2.3 使用 Lambda 方式调用
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
## 🔧 详细功能示例
### 一、基础HTTP方法
```java
    ApiService api = JCurlInvoker.createProxy(ApiService.class);
    JQuickCurlReq req = new JQuickCurlReq();
    req.put("user", "xsasaxsa@qq.com");
    req.put("password", "xasxsa");
    JGithubAuth result = api.retriveUser(req);
```
#### 1. GET请求（查询资源）
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
## xml 配置
### 定义xml
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
</curls>
```
### 定义java 接口
```java
package com.github.paohaijiao.test.xml;
import com.github.paohaijiao.test.model.JUser;
import com.github.paohaijiao.xml.param.Param;
import java.util.List;
public interface UserApi {
    List<JUser> all(JQuickCurlReq req);
}
```
### 定义业务逻辑
```java
    @Test
    public  void all1() throws Exception {
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsaxsa@qq.com");
        req.put("password", "zaZAzaZA");
        CurlApiFactory factory = new CurlApiFactory("apis.xml");
        UserApi userApi = factory.createApi(UserApi.class);
        List<JUser> list =userApi.all(req);
        System.out.println(list);
    }
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