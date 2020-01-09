package com.jf.jf_iot.common.entity.propertiEntity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:config.properties")
/*@PropertySource(value = "classpath:config.yml",factory = MyPropertiesSoucerFactory.class)*/
public class WxEntity {

    /**
     * 小程序appid
     */
    @Value("${wx.appid}")
    private String appid;
    /**
     * 小程序小程序密钥
     */
    @Value("${wx.secret}")
    private String appSecret;

}
