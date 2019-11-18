package com.jf.jf_iot.device.controller;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.entity.SelectEntityIdString;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.common.utill.SelectUtil;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.service.DeviceTypeService;
import com.jf.jf_iot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("deviceType")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    HttpSession session;
    @Autowired
    private SelectUtil selectUtil;

    /**
     * 为下拉框提供设备分类的方法
     *
     * @return
     */
    @GetMapping("findSelect")
    public ResponseEntity<List<SelectEntityIdString>> findDeviceTypeToSelect(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        List<DeviceType> all = deviceTypeService.findAll(user);
        //定义集合用于接收转换后的结果集
        List<SelectEntityIdString> list = new ArrayList<>();
        for (DeviceType deviceType : all) {
            SelectEntityIdString selectEntity = selectUtil.exchangToSelect(deviceType.getTypekey(), deviceType.getTypename());
            list.add(selectEntity);
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping("page")
    public ResponseEntity<PageResult<DeviceType>> querDeviceByPage(@RequestBody(required = false) DeviceType deviceType, int page, int rows) {
        User user = (User) session.getAttribute("user");

        PageResult page1 = deviceTypeService.findPage(page, rows, deviceType, user);
        return ResponseEntity.ok(page1);
    }

}
