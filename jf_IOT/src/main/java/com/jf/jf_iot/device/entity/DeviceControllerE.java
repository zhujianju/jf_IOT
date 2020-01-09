package com.jf.jf_iot.device.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 设备控制器类
 */
@Data
@Table(name = "con_device_controller")
public class DeviceControllerE {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private Integer code;
    private String state;
    private Integer deviceId;
    private Date createtime;
    private Date updatetime;
    private Integer creatorid;
    private Integer lastupdateid;

}
