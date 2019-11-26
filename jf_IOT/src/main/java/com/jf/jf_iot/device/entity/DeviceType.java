package com.jf.jf_iot.device.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "con_deviceType")
public class DeviceType {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;//id
    private String typekey;//设备分类key
    private String typename;//分类名称

    private Date createtime;//创建时间
    private Date updatetime;//修改时间
    @Column(name = "creatorID")
    private Integer creatorID;//创建人id
    @Column(name="lastUpdateId")
    private Integer lastUpdateId;//最后一次修改人id

}
