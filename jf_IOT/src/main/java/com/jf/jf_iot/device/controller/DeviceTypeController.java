package com.jf.jf_iot.device.controller;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.entity.SelectEntityIdInteger;
import com.jf.jf_iot.common.entity.SelectEntityIdString;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.common.utill.SecurityUtil;
import com.jf.jf_iot.common.utill.SelectUtil;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.service.DeviceTypeService;
import com.jf.jf_iot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private SelectUtil selectUtil;
    @Autowired
    HttpSession session;
    /**
     * 为下拉框提供设备分类的方法
     * @return
     */
    @GetMapping("findSelect")
    public ResponseEntity<List<SelectEntityIdInteger>>findDeviceTypeToSelect(){
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        List<DeviceType> all = deviceTypeService.findAll(user);
        //定义集合用于接收转换后的结果集
        List<SelectEntityIdInteger> list = new ArrayList<>();
        for (DeviceType deviceType : all) {
            SelectEntityIdInteger selectEntity = selectUtil.exchangToSelect(deviceType.getId().longValue(), deviceType.getTypename());
            list.add(selectEntity);
        }
        return ResponseEntity.ok(list) ;
    }

    /**
     * 分页查询设备类型
     */
    @PostMapping("page")
    public ResponseEntity<PageResult<DeviceType>> findPage(@RequestBody(required = false) DeviceType deviceType, int page, int rows){
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        SecurityUtil.isRoot(session);//判断是否为管理员
        return ResponseEntity.ok(deviceTypeService.findPage(page,rows,deviceType));
    }

    /**
     * 新增的方法
     * @param deviceType
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> saveDeviceType(@RequestBody DeviceType deviceType){
        SecurityUtil.isRoot(session);//判断是否为管理员
        User user = (User) session.getAttribute("user");
        int i = deviceTypeService.insert(deviceType,user);
      /*  if(i <1){
            throw new IOTException(ExceptionEnum.DEVICETYOE_CREATE_ERROR);
        }*/
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };
    /**
     * 修改的方法
     */
    @PutMapping
    public ResponseEntity<Void> updateDeviceType(@RequestBody DeviceType deviceType){
        SecurityUtil.isRoot(session);//判断是否为管理员
        User user = (User) session.getAttribute("user");
        int i = deviceTypeService.updateByid(deviceType,user);
        /*if(i <1){
            throw new IOTException(ExceptionEnum.DEVICETYOE_CREATE_ERROR);
        }*/
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 删除的方法
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Integer id) {
        SecurityUtil.isRoot(session);//判断是否为管理员
        int i = deviceTypeService.deleteById(id);
        if(i <1){
            throw new IOTException(ExceptionEnum.DEVICETYOE_DELETE_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查询单个设备分类的方法
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<DeviceType> findOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(deviceTypeService.findOne(id));
    }
}
