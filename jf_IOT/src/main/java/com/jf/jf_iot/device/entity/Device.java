package com.jf.jf_iot.device.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "con_device")
public class Device {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String id;//设备Id
    private Integer typeid;//设备分类id
    private String name;//设备名称
    private String productkey;//出入库key
    private String devicename;//设备name
    private String devicesecret;//密钥
    private Integer saleactivate;//销售激活状态0.未激活 1.已激活
    private String subscriber;//订阅地址
    private String issue;//发布地址
    private Integer lifecyclewarn;//0已入库，1已出售，2已激活，3已已停用，4失效等状态
    private Integer isenable;//是否开启。0.关闭  1.开启

    private Date createtime;//创建时间
    private Date updatetime;//修改时间
    @Column(name = "creatorID")
    private Integer creatorID;//创建人id
    @Column(name="lastUpdateId")
    private Integer lastUpdateId;//最后一次修改人id
    @Transient
    private Boolean isBind;//用户判定设备是否被绑定
    @Transient
    private DeviceType deviceType;//设备类型
    @Transient
    private DeviceParam deviceParam;//设备参数表


}
