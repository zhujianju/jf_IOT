package com.jf.jf_iot.common.mqtt;
import com.jf.jf_iot.common.entity.propertiEntity.MqttEntity;
import com.jf.jf_iot.common.mqtt.PushCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 * Title:Server
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @author admin
 * 2017年2月10日下午17:41:10
 */
@Component
public class MeMqttServer {
    @Autowired
    private MqttEntity mqttEntity;
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    //定义一个主题
    //定义MQTT的ID，可以在MQTT服务配置中指定

    private MqttClient client;
    private MqttTopic topic11;

    private String Tpoice;

    private MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public MeMqttServer() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存

    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(mqttEntity.getUserName());
        options.setPassword(mqttEntity.getPassWord().toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);

            topic11 = client.getTopic(this.Tpoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    private void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException {


      MeMqttServer server = new MeMqttServer();
        server.start("mytopic","测试");
        System.out.println("程序走到末尾");
    }

    public Integer start(String topic,String message) throws MqttException {
       MeMqttServer server = new MeMqttServer();
        this.Tpoice=topic;
        client = new MqttClient(mqttEntity.getServer_url(), mqttEntity.getClientid(), new MemoryPersistence());
        connect();
        this.message= new MqttMessage();
        //设置消息的级别。最多一次的传输.  至少一次的传输  只有一次的传输只有一次的传输
        this.message.setQos(1);
        //设置是否保留最后一次发送的消息。  设置为true后，断开主题，重新链接会再次读取到最后一次发送的消息
        this.message.setRetained(false);
        this.message.setPayload(message.getBytes());
        this.publish(this.topic11 , this.message);
        System.out.println(this.message.isRetained() + "------ratained状态");
        return 1;
    }

}
