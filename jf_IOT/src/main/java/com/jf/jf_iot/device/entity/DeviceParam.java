package com.jf.jf_iot.device.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "con_device_param")
public class DeviceParam {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String englishName;
    private Integer type;
    private Integer valueType;
    private Integer min;
    private Integer max;
    private Integer state;
    private String warnValue;
    private String classificationId;
}
