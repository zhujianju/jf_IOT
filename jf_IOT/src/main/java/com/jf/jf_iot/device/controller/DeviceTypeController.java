package com.jf.jf_iot.device.controller;

import com.jf.jf_iot.common.entity.SelectEntityIdString;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.common.utill.SelectUtil;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.service.DeviceTypeService;
import com.jf.jf_iot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("deviceType")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private SelectUtil selectUtil;
    @Autowired
    HttpSession session;
    /**
     * 为下拉框提供设备分类的方法
     * @return
     */
    @GetMapping("findSelect")
    public ResponseEntity<List<SelectEntityIdString>>findDeviceTypeToSelect(){
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        List<DeviceType> all = deviceTypeService.findAll(user);
        //定义集合用于接收转换后的结果集
        List<SelectEntityIdString> list = new ArrayList<>();
        for (DeviceType deviceType : all) {
            SelectEntityIdString selectEntity = selectUtil.exchangToSelect(deviceType.getTypekey(), deviceType.getTypename());
            list.add(selectEntity);
        }
        return ResponseEntity.ok(list) ;
    }

    /**
     * 分页查询设备类型
     */
    @PostMapping
    public ResponseEntity<PageResult<DeviceType>> findPage(@RequestBody(required = false) DeviceType deviceType, int page, int rows){
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        SecurityUtil.isRoot(session);//判断是否为管理员
        return ResponseEntity.ok(deviceTypeService.findPage(page,rows,deviceType));
    }
    @PostMapping("page")
    public ResponseEntity<PageResult<DeviceType>> querDeviceByPage(@RequestBody(required = false) DeviceType deviceType, int page, int rows) {
        User user = (User) session.getAttribute("user");

        PageResult page1 = deviceTypeService.findPage(page, rows, deviceType, user);
        return ResponseEntity.ok(page1);
    }

}
