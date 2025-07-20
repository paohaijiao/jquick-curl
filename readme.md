# jquickCurl HTTP Client Test Documentation
jquickcurl is a high-performance, easy-to-use http client framework for java developers. it fully uses java 8's concurrency and okhttp's features
to simplify network request handling and boost efficiency. this framework aims to offer a curl-like programming interface for elegant and efficient
http operations like get, post, and put. jquickcurl emphasizes cross-platform compatibility, supporting seamless deployment across os environments.
this greatly expands its applicability across scenarios, facilitating integration and leveraging efficient features for both server-side and mobile
app development. jquickcurl simplifies service-to-service network requests with its concise api, reducing http integration complexity for developers
in both local and microservices environments. jquickcurl shows exceptional capabilities in cloud application development too. it optimizes resource use,
boosts cloud app speed and stability, lets developers focus on business logic, and significantly enhances efficiency and performance. whether tackling
microservices' distributed challenges or cloud environments' high concurrency, jquickcurl is a vital tool for developers to ensure fast, reliable
network communication through its flexibility and efficiency.
## Usage Guide
### Basic Syntax
```string
curl [options] [URL...]
```
### Basic Options
```string
-X, --request <method>  Specify request method (GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, TRACE)
-H, --header <header>   Add HTTP header to request
-d, --data <data>      Send data in POST/PUT request
--data-ascii <data> Send ASCII data
--data-binary <data> Send binary data
--data-raw <data>  Send raw data without processing
--data-urlencode <data> Send URL-encoded form data
-u, --user <user:pass> Server user and password
-L, --location         Follow redirects
--max-redirs <num> Maximum number of redirects to follow
-o, --output <file>    Write output to file instead of stdout
-F, --form <name=content> Specify multipart form data
-x, --proxy <[protocol://]host[:port]> Use proxy
--socks5-hostname <host[:port]> SOCKS5 proxy
--http2            Use HTTP/2
-k, --insecure         Allow insecure server connections
-v, --verbose          Make the operation more talkative
-s, --silent           Silent mode (don't output errors)
```
## Table of Contents
- [Introduction](#introduction)
- [Basic Requests](#basic-requests)
    - [List Query](#1-list-query)
    - [Get Single Item](#2-get_single_item)
    - [POST Request](#3-post-request)
    - [PUT Request](#4-put-request)
    - [PATCH Request](#5-patch-request)
    - [DELETE Request](#6-delete-request)
    - [HEAD Request](#7-head-request)
    - [OPTIONS Request](#8-options-request)
    - [TRACE Request](#9-trace-request)
- [File Operations](#file-operations)
    - [Single File Upload](#10-single-file-upload)
    - [Multiple File Upload](#11-multiple-file-upload)
    - [File Download](#12-file-download)
    - [File Upload with Parameters](#13-file-upload-with-parameters)
    - [Batch Run](#14-batch-run)
    - [Lamda Support](#15-lamda-support)
    - [Basic Auth](#16-basic-auth)
    - [interceptor](#17-interceptor)
    - [Gloabl Variable Support](#18-global-variable-support)
- [Appendix](#appendix)

## Introduction
This document provides comprehensive examples for using JCurlInvoker, a Java-based HTTP client that uses cURL-style annotations to simplify API testing and integration.

## Basic Requests

### 1. List Query
**Interface Definition:**
```java
@JCurlCommand("curl -X GET --location 'http://localhost:8080/api/users/all'")
List<JUser> all(JQuickCurlReq req);
```
**how to use in java code:**
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
### 2. Get Single Item
**Interface Definition:**
```java
@JCurlCommand("curl -X GET http://localhost:8080/api/users/1")
```
**how to use in java code:**
```java
  UserService api = JCurlInvoker.createProxy(UserService.class);
  JQuickCurlReq req = new JQuickCurlReq();
  JUser result = api.getUserById(req);
```
### 3. POST Request
**Interface Definition:**
```java
    @JCurlCommand("curl -X POST http://localhost:8080/api/users \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe\",\"email\":\"john@example.com\"}'")
    JUser users(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JUser result = api.users(req);
```
### 4. PUT Request
**Interface Definition:**
```java
    @JCurlCommand("curl -X PUT http://localhost:8080/api/users/1 \\\n" +
            "-H \"Content-Type: application/json\" \\\n" +
            "-d '{\"name\":\"John Doe Updated\",\"email\":\"john.updated@example.com\"}'")
    JUser usersPut(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JUser result = api.usersPut(req);
```
### 5. PATCH Request
**Interface Definition:**
```java
    @JCurlCommand("curl -X PATCH http://localhost:8080/api/users/1 \\\n" +
        "-H \"Content-Type: application/json\" \\\n" +
        "-d '{\"name\":\"John Doe Patched\"}'")
JUser usersPatch(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JUser result = api.usersPatch(req);
```
### 6. DELETE Request
**Interface Definition:**
```java
@JCurlCommand("curl -X DELETE http://localhost:8080/api/users/1")
Void usersDelete(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
api.usersDelete(req);
```
### 7. HEAD Request
**Interface Definition:**
```java
 @JCurlCommand("curl  -X HEAD -I http://localhost:8080/api/users/1")
    Void usersHead(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
api.usersHead(req);
```
### 8. OPTIONS Request
**Interface Definition:**
```java
 @JCurlCommand("curl -X OPTIONS http://localhost:8080/api/users/1")
    JResult usersOptions(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JResult jResult=api.usersOptions(req);
```
### 9. TRACE Request
**Interface Definition:**
```java
 @JCurlCommand("curl -X TRACE http://localhost:8080/api/users/trace \\\n" +
            "-H \"Content-Type: text/plain\" \\\n" +
            "-d \"This is a trace request body\"")
```
**how to use in java code:**
```java
String usersTrace(JQuickCurlReq req);
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
String jResult=api.usersTrace(req);
```
### 10. Single File Uploadt
**Interface Definition:**
```java
@JCurlCommand("curl -X POST http://localhost:8080/api/users/upload \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
```
**how to use in java code:**
```java
 UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        String jResult=api.upload(req);
```
### 11. Multiple File Upload
**Interface Definition:**
```java
@JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-multiple \\\n" +
"-F \"files=@D:\\test\\test.txt\"-F \"files=@D:\\test\\test1.txt\"")
String upload1(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
String jResult=api.upload1(req);
```
### 12. File Download
**Interface Definition:**
```java
    @JCurlCommand("curl -X GET http://localhost:8080/api/users/download/test.txt \\\n" +
            "--output 'd://test//download.txt'")
    byte[] download(JQuickCurlReq req);
```
**how to use in java code:**
```java
 UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        byte[] bytes=api.download(req);
        Path path = Paths.get("d://test/xx1.txt");
        Files.write(path, bytes, StandardOpenOption.CREATE);
```
### 13. File Upload with Parameters
**Interface Definition:**
```java
 @JCurlCommand("curl -X POST http://localhost:8080/api/users/upload-with-params \\\n" +
            "-F \"userId=123\" \\\n" +
            "-F \"username=john\" \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
    String uploadWithPostParams(JQuickCurlReq req);
```
**how to use in java code:**
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
String bytes=api.uploadWithPostParams(req);
```
### 14. Batch Run
```java
JQuickCurlBatchRunner batch= new JQuickCurlBatchRunner();
List<JResult> list=batch.runCurlCommands(new JCurlBatchCommandTest(),JResult.class);
```
### 15. Lamda Support
```java
JQuickCurlReq req = new JQuickCurlReq();
JUser result = JCurlInvoker.invoke(UserServiceImpl::getUserById, req,JUser.class);
```
### 16. basic auth
```java
    @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
    JGithubAuth retriveUser(JQuickCurlReq req);
```
### 17. interceptor
if you want process some business logic  before or after lauch a new curl request ,
you could implements Interceptor interface and pass the Interceptor via JQuickCurlConfig 
then it will effect 
### 18. global variable
if you want change the api host , parameter, method and any else base the different env, you could define the variable 
in your code  such as ${variableName},then you can pass the different value via JContext reference, finally 
the curl request will follow your variableName to execute.
Useage:
**Interface Definition:**
```java
@JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
JGithubAuth retriveUser(JQuickCurlReq req);
```
**how to use in java code:**
```java
 ApiService api = JCurlInvoker.createProxy(ApiService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        req.put("user", "xsasaxsa@qq.com");
        req.put("password", "xasxsa");
        JGithubAuth result = api.retriveUser(req);
```