package com.github.paohaijiao.result;

import com.github.paohaijiao.model.JResult;
import okhttp3.Response;

import java.io.IOException;

public interface JResponseConverter <T> {


    T convert(JResult response) throws IOException;
}
