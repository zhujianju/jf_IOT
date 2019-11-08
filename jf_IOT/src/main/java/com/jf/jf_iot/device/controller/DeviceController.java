package com.jf.jf_iot.device.controller;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.common.utill.SecurityUtil;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.device.service.DeviceService;
import com.jf.jf_iot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    HttpSession session;
    @PostMapping("page")
    public ResponseEntity<PageResult<Device>> querDeviceByPage(@RequestBody(required = false) Device device, int page, int rows){
        User user = (User) session.getAttribute("user");

        PageResult page1 = deviceService.findPage(page, rows, device, user);
        return ResponseEntity.ok(page1);
    }
    @GetMapping("findOne/{id}")
    public ResponseEntity<Device> findOne(@PathVariable("id") Integer id){
      return ResponseEntity.ok(deviceService.findOne(id));
    }

    /**
     * 新增的方法
     */
    @PostMapping()
    public ResponseEntity<Void> saveDevice(@RequestBody Device device){
        //检验权限,不为管理员则抛异常
        SecurityUtil.isRoot(session);
        deviceService.insert(device);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 修改的方法
     */
    @PutMapping()
    public ResponseEntity<Void> updateDevice(@RequestBody Device device){
        //检验权限,不为管理员则抛异常
        SecurityUtil.isRoot(session);
        deviceService.updateByid(device);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    /**
     * 删除的方法
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Integer id){
        //检验权限,不为管理员则抛异常
        SecurityUtil.isRoot(session);
        deviceService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据设备id，查询当前设备下的所有用户
     */
    @GetMapping("findUser/{id}")
    public ResponseEntity<List<User>> findBindUser(@PathVariable("id") Integer id, HttpSession session){
        //检验权限,不为管理员则抛异常
        SecurityUtil.isRoot(session);

        return  ResponseEntity.ok(deviceService.findBindUser(id));
    }
}
