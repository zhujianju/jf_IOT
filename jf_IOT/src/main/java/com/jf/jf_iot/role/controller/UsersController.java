package com.jf.jf_iot.role.controller;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.user.entity.User;
import com.jf.jf_iot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * ClassName: UsersContrller
 * Description:
 * date: 2019/11/25 16:38
 *
 * @author 胡正
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @RequestMapping("/page")
    public ResponseEntity<PageResult<User>> querDeviceByPage(@RequestBody(required = false) User users, int page, int rows){
        User user = (User) session.getAttribute("user");
        PageResult page1 = userService.findPage(page,rows,user);
        return ResponseEntity.ok(page1);
    }
}
