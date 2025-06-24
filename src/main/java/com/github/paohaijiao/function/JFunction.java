package com.github.paohaijiao.function;

import com.github.paohaijiao.domain.req.JQuickCurlReq;
import com.github.paohaijiao.domain.resp.JQuickCurlResp;
import com.github.paohaijiao.model.JResult;

import java.io.Serializable;
import java.util.function.Function;

public interface JFunction <T extends JQuickCurlReq, R extends JResult> extends Function<T, R>, Serializable {

}
