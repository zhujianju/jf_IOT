package com.jf.jf_iot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {
    //用户类异常码
    USER_REQUST_ERROR(4000,"错误请求，参数错误"),
    USER_NOT_FOND(4004,"用户不存在"),
    USER_NOT_LOGIN(4004,"用户未登录"),
    USER_NOT_AUTHO(4005,"用户没有分配权限"),
    USER_NOT_ROOT(4005,"需要管理员才能进行此操作"),
    USER_PASSWORD_ERROR(4001,"用户密码错误"),
    //设备异常
    DEVICE_NOT_FOND(3004,"没有查询到设备"),

    //设备类型异常
    DEVICETYOE_NOT_FOND(5004,"没有查询到设备类型"),
    DEVICETYOE_KEY_ISBIND(5006,"当前设备分类已经被设备绑定"),
    DEVICETYOE_CREATE_ERROR(5000,"系统问题，分类保存失败。"),
    DEVICETYOE_DELETE_ERROR(5001,"系统问题，分类删除失败。"),

    //设备参数异常
    DEVICEPARAM_NOT_FOND(6004,"暂时没有添加分类参数")

    ;
    private int code;//异常状态吗
    private String message;//异常信息

}
