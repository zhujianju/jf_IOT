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
    private Integer id;//id
    private String name;//中文名
    private String englishName;//英文名
    private Integer type;//参数类型(1.动值参数,2.定值参数)
    private Integer valueType;//参数值的类型(1int,2string)
    private Integer min;//最小值
    private Integer max;//最大值
    private Integer state;//状态
    private String warnValue;//报警值
    private String classificationId;//设备分类id
}
