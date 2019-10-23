package com.jf.jf_iot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {

    USER_NOT_FOND(404,"用户不存在"),
    USER_NOT_AUTHO(405,"用户未分配权限")
    ;
    private int code;//异常状态吗
    private String message;//异常信息

}
