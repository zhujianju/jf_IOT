package com.jf.jf_iot.common.entity;

import com.jf.jf_iot.common.enums.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {
    private int status;//异常状态码
    private String message;//异常信息
    private Long timestamp;//异常时间戳

    public ExceptionResult(ExceptionEnum em) {
        this.status=em.getCode();
        this.message=em.getMessage();
        this.timestamp=System.currentTimeMillis();
    }
}
