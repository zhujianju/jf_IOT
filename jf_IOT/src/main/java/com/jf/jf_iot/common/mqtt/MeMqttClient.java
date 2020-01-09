package com.jf.jf_iot.common.mqtt;

import com.jf.jf_iot.common.entity.propertiEntity.MqttEntity;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Mqtt客户端
 */
@Component
public class MeMqttClient {
    @Autowired
    private MqttEntity mqttEntity;
        /*public static final String SERVER_URL = "tcp://139.159.132.64:1883";

        public static final String TOPIC = "/ba558e69-2544-4c81-97bc-d4bf6eb5df50/6807e107-373d-4481-9f32-7edb524902e0/71bec3e9-5421-4940-a47d-50807a99a2f3/system/devmsq";

        public static final String clientid  = "mqttId";*/

    private MqttClient client;
    private MqttConnectOptions options;

        /*private String userName = "jfiot";
        private String passWord = "jf@123";*/

    private ScheduledExecutorService scheduler;

    public void start(String Topic) throws Exception{
        System.out.println(mqttEntity);
        client = new MqttClient(mqttEntity.getServer_url(), mqttEntity.getClientid(), new MemoryPersistence());
        options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(mqttEntity.getUserName());
        options.setPassword(mqttEntity.getPassWord().toCharArray());
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(20);
        client.setCallback(new PushCallback());
        MqttTopic topic = client.getTopic(Topic);
        client.connect(options);
        int[] Qos = {1};
        String[] topic1 = {Topic};
        client.subscribe(topic1,Qos);
    }




    public  static void main (String[] args) throws Exception {
        MeMqttClient client = new MeMqttClient();
        client.start("mytopice");
    }
}
