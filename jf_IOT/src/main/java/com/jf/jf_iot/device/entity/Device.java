package com.jf.jf_iot.device.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "con_device")
public class Device {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String typeid;
    private Integer paramid;
    private String productkey;
    private String devicename;
    private String devicesecret;
    private Integer saleactivate;
    @Transient
    private String typeName;


}
