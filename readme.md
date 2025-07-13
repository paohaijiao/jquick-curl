# JCurlInvoker HTTP Client Test Documentation

## Table of Contents
- [Introduction](#introduction)
- [Basic Requests](#basic-requests)
    - [List Query](#1-list-query)
    - [Get Single Item](#2-get-single-item)
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