package com.jf.jf_iot.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.jf.jf_iot.common.entity.propertiEntity.WxEntity;
import com.jf.jf_iot.common.utill.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * 微信小程序登陆
 */
@Controller
public class WxLoginController {
   /* *//**
     * 小程序appid
     *//*
    @Value("${wx.appid}")
    private String appid;
    *//**
     * 小程序小程序密钥
     *//*
    @Value("${wx.secret}")
    private String appSecret;*/

    @Autowired
    private WxEntity wxEntity;

    /**
     * 微信小程序用户登陆
     * @param code  用户登陆后的code
     * @param encryptedData 获取用户的加密信息
     * @return
     */
    @GetMapping("wxLogin")
    public ResponseEntity<String> wxLogin(String code,String encryptedData,String iv) {
        System.out.println(wxEntity);
        // 根据小程序穿过来的code想这个url发送请求
        WeChatUtil weChatUtil =new WeChatUtil();
        String str =weChatUtil.getOpenIdAndSession_key(wxEntity.getAppid(), wxEntity.getAppSecret(),code);
        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str);
        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = jsonObject.get("openid").toString();
        //获得登陆会话sessionkey
        String seesionKey = jsonObject.get("session_key").toString();
        JSONObject unionId = weChatUtil.getUnionId(seesionKey, iv, encryptedData);
        System.out.println(unionId);
        return null;
    }
}
