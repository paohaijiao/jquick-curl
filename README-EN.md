# jquickCurl  Documentation
```string
  jquickcurl is a high-performance, easy-to-use http client framework for java developers. it fully uses java 8's 
concurrency and okhttp's features to simplify network request handling and boost efficiency. this framework aims 
to offer a curl-like programming interface for elegant and efficient http operations like get, post, and put. 
jquickcurl emphasizes cross-platform compatibility, supporting seamless deployment across os environments.
this greatly expands its applicability across scenarios, facilitating integration and leveraging efficient features 
for both server-side and mobile app development. jquickcurl simplifies service-to-service network requests with its 
concise api, reducing http integration complexity for developers in both local and microservices environments. jquickcurl 
shows exceptional capabilities in cloud application development too. it optimizes resource use,boosts cloud app speed 
and stability, lets developers focus on business logic, and significantly enhances efficiency and performance. whether 
tackling microservices' distributed challenges or cloud environments' high concurrency, jquickcurl is a vital tool for 
developers to ensure fast, reliable network communication through its flexibility and efficiency.
```
## Usage Guide
### Basic Syntax
```string
curl [options] [URL]
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
```
## Table of Contents
- [jquickCurl Documentation](#jquickcurl-documentation)
- [Usage Guide](#usage-guide)
  - [Basic Syntax](#basic-syntax)
  - [Basic Options](#basic-options)
- [Introduction](#introduction)
- [Basic Requests](#basic-requests)
  - [1. List Query](#1-list-query)
  - [2. Get Single Item](#2-get-single-item)
  - [3. POST Request](#3-post-request)
  - [4. PUT Request](#4-put-request)
  - [5. PATCH Request](#5-patch-request)
  - [6. DELETE Request](#6-delete-request)
  - [7. HEAD Request](#7-head-request)
  - [8. OPTIONS Request](#8-options-request)
  - [9. TRACE Request](#9-trace-request)
- [File Operations](#file-operations)
  - [10. Single File Upload](#10-single-file-upload)
  - [11. Multiple File Upload](#11-multiple-file-upload)
  - [12. File Download](#12-file-download)
  - [13. File Upload with Parameters](#13-file-upload-with-parameters)
  - [14. Batch Run](#14-batch-run)
  - [15. Lambda Support](#15-lamda-support)
  - [16. Basic Auth](#16-basic-auth)
  - [17. Interceptor](#17-interceptor)
  - [18. Global Variable Support](#18-global-variable-support)
- [Appendix](#appendix)

## Introduction
This document provides comprehensive examples for using JCurlInvoker, a Java-based HTTP client that uses cURL-style annotations to simplify API testing and integration.

## Basic Requests

1. List Query
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

2. Get Single Item
```java
@JCurlCommand("curl -X GET http://localhost:8080/api/users/1")
```
```java
  UserService api = JCurlInvoker.createProxy(UserService.class);
  JQuickCurlReq req = new JQuickCurlReq();
  JUser result = api.getUserById(req);
```

3. POST Request
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

4. PUT Request
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

5. PATCH Request
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

6. DELETE Request
```java
@JCurlCommand("curl -X DELETE http://localhost:8080/api/users/1")
Void usersDelete(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
api.usersDelete(req);
```
7. HEAD Request
```java
 @JCurlCommand("curl  -X HEAD -I http://localhost:8080/api/users/1")
    Void usersHead(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
api.usersHead(req);
```

8. OPTIONS Request
```java
 @JCurlCommand("curl -X OPTIONS http://localhost:8080/api/users/1")
    JResult usersOptions(JQuickCurlReq req);
```
```java
UserService api = JCurlInvoker.createProxy(UserService.class);
JQuickCurlReq req = new JQuickCurlReq();
JResult jResult=api.usersOptions(req);
```

9. TRACE Request
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
10. Single File Uploadt
```java
@JCurlCommand("curl -X POST http://localhost:8080/api/users/upload \\\n" +
            "-F \"file=@D:\\test\\test.txt\"")
```
```java
 UserService api = JCurlInvoker.createProxy(UserService.class);
        JQuickCurlReq req = new JQuickCurlReq();
        String jResult=api.upload(req);
```

11. Multiple File Upload
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
12. File Download
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
13. File Upload with Parameters
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

14. Batch Run
```java
JQuickCurlBatchRunner batch= new JQuickCurlBatchRunner();
List<JResult> list=batch.runCurlCommands(new JCurlBatchCommandTest(),JResult.class);
```
15. Lamda Support
```java
JQuickCurlReq req = new JQuickCurlReq();
JUser result = JCurlInvoker.invoke(UserServiceImpl::getUserById, req,JUser.class);
```

16. basic auth
```java
    @JCurlCommand("curl -u ${user}:${password} https://api.github.com/user\n -X GET")
    JGithubAuth retriveUser(JQuickCurlReq req);
```
17. interceptor
```string
if you want process some business logic  before or after lauch a new curl request ,
you could implements Interceptor interface and pass the Interceptor via JQuickCurlConfig 
then it will effect 
```
18. global variable
```string
if you want change the api host , parameter, method and any else base the different env, you could define the variable 
in your code  such as **${variableName}**,then you can pass the different value via JContext reference, finally 
the curl request will follow your variableName to execute.
```
Useage:
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

# **Generating Power with Love (and Caffeine) ☕**

Thank you for using this open-source project! It is completely free and will be maintained continuously, but the developers do need your support.

---

## **How You Can Help**

1. **Buy Me a Coffee**  
   If this project has saved you time or money, please consider supporting me with a small donation.

2. **Where Your Donation Goes**
- Server costs to keep the project running.
- Feature development to add more value.
- Documentation optimization for a better user experience.

3. **Every Cent Counts**  
   Even a donation of just 1 cent motivates me to debug late into the night!



## **Why Donate?**
✔️ Keep the project **free and ad-free** forever.  
✔️ Support timely responses to issues and community inquiries.  
✔️ Enable planned features for the future.

Thank you for being a partner in making the open-source world better!

--- 

### **Additional Notes**
- The project is maintained with love and caffeine.
- Your support ensures its sustainability and growth.
---

## **🌟 Support Now**
Feel free to leave a message via [email](mailto:goudingcheng@gmail.com) when sponsoring. Your name will be included in the **"Special Thanks"** list in the project's README file!
![OCBC Pay Now](./src/main/resources/pay/paynow.jpg)
![Touch n Go ](./src/main/resources/pay/tngGo.jpg)
---