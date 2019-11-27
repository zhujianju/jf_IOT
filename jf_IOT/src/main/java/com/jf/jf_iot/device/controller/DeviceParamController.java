package com.jf.jf_iot.device.controller;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.entity.SelectEntityIdInteger;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.common.utill.SecurityUtil;
import com.jf.jf_iot.common.utill.SelectUtil;
import com.jf.jf_iot.device.entity.DeviceParam;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.service.DeviceParamService;
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
@RequestMapping("deviceParam")
public class DeviceParamController {
    @Autowired
    private DeviceParamService deviceParamService;

    @Autowired
    HttpSession session;


    /**
     * 分页查询设备类型
     */
    @PostMapping("page")
    public ResponseEntity<PageResult<DeviceParam>> findPage(@RequestBody(required = false) DeviceParam deviceParam, int page, int rows){
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        SecurityUtil.isRoot(session);//判断是否为管理员
        System.out.println(deviceParam);
        return ResponseEntity.ok(deviceParamService.findPage(page,rows,deviceParam));
    }

    /**
     * 新增的方法
     * @param deviceParam
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> saveDeviceType(@RequestBody DeviceParam deviceParam){
        SecurityUtil.isRoot(session);//判断是否为管理员
        User user = (User) session.getAttribute("user");
        int i = deviceParamService.insert(deviceParam,user);
      /*  if(i <1){
            throw new IOTException(ExceptionEnum.DEVICETYOE_CREATE_ERROR);
        }*/
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };
    /**
     * 修改的方法
     */
    @PutMapping
    public ResponseEntity<Void> updateDeviceType(@RequestBody DeviceParam deviceParam){
        SecurityUtil.isRoot(session);//判断是否为管理员
        User user = (User) session.getAttribute("user");
        int i = deviceParamService.updateByid(deviceParam,user);
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
        int i = deviceParamService.deleteById(id);
      /*  if(i <1){
            throw new IOTException(ExceptionEnum.DEVICETYOE_DELETE_ERROR);
        }*/
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查询单个设备分类的方法
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<DeviceParam> findOne(@PathVariable("id") Integer id){
        SecurityUtil.isRoot(session);//判断是否为管理员
        return ResponseEntity.ok(deviceParamService.findOne(id));
    }

    /**
     * 根据分类id查询分类下的所有参数
     * @return
     */
    @GetMapping("/params/{id}")
    public ResponseEntity<List<DeviceParam>> findDeviceParamsByid(@PathVariable("id") Integer id){
        SecurityUtil.isRoot(session);//判断是否为管理员
        return ResponseEntity.ok(deviceParamService.findDeviceParamsByid(id));
    }
}
