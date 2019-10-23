package com.jf.jf_iot.user.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user")
@Data
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;//用户名称
    private String account;//用户账号
    private String password;//密码
    private String cid;//身份证
    private String emil;//邮箱
    private String mobile;//手机号
    @Column(name="clientID")
    private Integer clientID;//客户id  如果为空；则表示不是客户，是管理员或者经销商
    private Integer autho;//权限级别 1.客户  2.经销商  3.管理员
    @Column(name="createTime")
    private Date createTime;//创建时间
    @Column(name="updateTime")
    private Date updateTime;//修改时间
}
