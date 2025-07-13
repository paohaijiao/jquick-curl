# JCurlInvoker HTTP Client Test Documentation

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