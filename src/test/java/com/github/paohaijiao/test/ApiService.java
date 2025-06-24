package com.github.paohaijiao.test;

import com.github.paohaijiao.anno.JCurlCommand;

public interface ApiService {

    @JCurlCommand("curl -X GET https://api.example.com/data")
    String fetchData();
}
