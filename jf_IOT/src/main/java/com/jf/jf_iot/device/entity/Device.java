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
    private Integer id;//设备Id
    private String typeid;//设备分类id
    private Integer paramid;//参数id，关联参数表id
    private String productkey;//出入库key
    private String devicename;//设备名称
    private String devicesecret;//密钥
    private Integer saleactivate;//销售激活状态0.未激活 1.已激活
    @Transient
    private Boolean isBind;//用户判定设备是否被绑定
    @Transient
    private DeviceType deviceType;//设备类型
    @Transient
    private DeviceParam deviceParam;//设备参数表


}
