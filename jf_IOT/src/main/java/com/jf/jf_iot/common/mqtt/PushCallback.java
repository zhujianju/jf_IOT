package com.jf.jf_iot.common.mqtt;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengcheng.du on 2018/10/12.
 */
public class PushCallback implements MqttCallback {
    @Override
    //处理链接断开
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println(throwable);
        System.out.println("连接断开，可以做重连");
    }

    @Override
    //处理消息送达
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("接收消息主题 : " + s);
        System.out.println("接收消息Qos : " + mqttMessage.getQos());
        String str=new String(mqttMessage.getPayload());
        System.out.println("接收消息内容 : " + str);
        System.out.println("-----------------------------------");
        Map<String,String> map=new HashMap();
        map=JSONObject.parseObject(str , HashMap.class);
        System.out.println("转换成Map集合"+map);
       Object cmd= map.get("cmd");
        System.out.println("cmd"+cmd);

    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}