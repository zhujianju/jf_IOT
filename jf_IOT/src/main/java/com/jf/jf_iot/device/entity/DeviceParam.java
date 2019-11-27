package com.jf.jf_iot.device.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "con_device_param")
public class DeviceParam {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;//id
    @Column(name = "classification_id")
    private Integer classificationId;//设备分类id
    private String name;//中文名
    private String englishName;//英文名
    private Integer type;//参数类型(1.动值参数,2.定值参数)
    private Integer valueType;//参数值的类型(1int,2string)
    private Integer min;//最小值
    private Integer max;//最大值
    private String unit;//最小值
    private Integer state;//状态：0禁用，1启用
    private String warnValue;//报警值

    private Date createtime;//创建时间
    private Date updatetime;//修改时间
    @Column(name = "creatorID")
    private Integer creatorID;//创建人id
    @Column(name="lastUpdateId")
    private Integer lastUpdateId;//最后一次修改人id

}
