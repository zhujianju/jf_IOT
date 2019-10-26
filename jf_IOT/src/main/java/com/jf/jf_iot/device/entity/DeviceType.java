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
    private Integer id;
    private String typekey;
    private String typename;
}
