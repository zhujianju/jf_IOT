package com.jf.jf_iot.common.entity.propertiEntity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:config.properties")
public class MqttEntity {

    @Value("${mqtt.server_url}")
    private String server_url;

    @Value("${mqtt.clientid}")
    private String clientid;

    @Value("${mqtt.userName}")
    private String userName;

    @Value("${mqtt.passWord}")
    private String passWord;



}
