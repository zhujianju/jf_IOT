package com.jf.jf_iot.device.controller;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.device.service.DeviceService;
import com.jf.jf_iot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Device>> querDeviceByPage(@RequestParam(required = false) Device device, HttpSession session,int page,int rows){
        User user = (User) session.getAttribute("user");

        PageResult page1 = deviceService.findPage(page, rows, device, user);
        return ResponseEntity.ok(page1);
    }

}
