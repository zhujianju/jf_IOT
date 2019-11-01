package com.jf.jf_iot.device.controller;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.device.service.DeviceService;
import com.jf.jf_iot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("page")
    public ResponseEntity<PageResult<Device>> querDeviceByPage(@RequestBody(required = false) Device device, HttpSession session, int page, int rows){
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        PageResult page1 = deviceService.findPage(page, rows, device, user);
        return ResponseEntity.ok(page1);
    }
    @GetMapping("findOne/{id}")
    public ResponseEntity<Device> findOne(@PathVariable("id") Integer id){
      return ResponseEntity.ok(deviceService.findOne(id));
    }
}
