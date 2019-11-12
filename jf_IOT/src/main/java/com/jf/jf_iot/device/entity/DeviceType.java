package com.jf.jf_iot.device.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "con_deviceType")
public class DeviceType {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;//id
    private String typekey;//设备分类key
    private String typename;//分类名称
}
