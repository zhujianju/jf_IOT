package com.jf.jf_iot.user.controller;

import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.user.entity.User;
import com.jf.jf_iot.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("login")
    public String login(){
        return "/login/index";
    }

/*    //对密码进行 md5 加密
    String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());*/
    @Autowired
    private UserService userService;
    @PostMapping("userLogin")
    public String userLogin(User user, HttpSession session, HttpServletResponse response){
        if(StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword()) ){
            throw new IOTException(ExceptionEnum.USER_REQUST_ERROR);
        }

        User one = userService.findOne(user);

        if(one.getAutho() == null){
            throw new IOTException(ExceptionEnum.USER_NOT_AUTHO);
        }
        Cookie authoCookie=new Cookie("userAutho",one.getAutho()+"");
        response.addCookie(authoCookie);
        session.setAttribute("user",one);
        /*if(one.getAutho() == 1){
            return "redirect:https://www.baidu.com/?id=1";
        }
        if(one.getAutho() == 2){
            return "redirect:https://www.baidu.com/?id=2";
        }
        if(one.getAutho() == 3){
            return "redirect:https://www.baidu.com/?id=3";
        }*/
        return "redirect:/admin/index.html";
    }

}
