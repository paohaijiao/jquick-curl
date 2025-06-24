package com.github.paohaijiao.domain.resp;

import com.github.paohaijiao.model.JResult;
import lombok.Data;

import java.util.Date;
@Data
public class JQuickCurlResp {

    private Integer status;

    private Date date;

    private JResult data;
}
