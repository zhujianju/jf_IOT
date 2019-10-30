package com.jf.jf_iot.role.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: RoleController
 * Description: 权限管理
 * date: 2019/10/26 15:55
 * @author 胡正
 * @since JDK 1.8
 */
@Controller
public class RoleController {
    @RequestMapping("/index")
    public String Hello(){
        System.out.println("RoleController.Hello()");
        return "/login/index";
    }
    @RequestMapping("/thymeleaf")
    public String testThymeleaf(Model model){
        //把数据存入model
        model.addAttribute("name","nb");
        return "test";
    }
}
