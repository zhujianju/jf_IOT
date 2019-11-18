package com.jf.jf_iot.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.mapper.DeviceMapper;
import com.jf.jf_iot.device.mapper.DeviceTypeMapper;
import com.jf.jf_iot.device.service.DeviceTypeService;
import com.jf.jf_iot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    /**
     * 查询当前登陆用户的所有设备的设备分类。
     * @return
     */
    @Override
    public List<DeviceType> findAll(User user) {
        //用户权限判断
        //1.当前用户没有权限
        if (user.getAutho() == null || user.getAutho()==0){
            throw new IOTException(ExceptionEnum.USER_NOT_AUTHO);
        }
        //定义参数，用于接收查询到的分类集合
        List<DeviceType> deviceTypes=null;
        //2.如果当前为管理员，则查询所有的设备分类
        if (user.getAutho() == 3){
             deviceTypes = findAll();
        }
        //3.当前登陆为用户/经销商。
        if(user.getAutho() == 1 || user.getAutho() == 2){
            List<Device> devices = deviceMapper.queryDeviceByUserId(user.getId(), null, null);
            if(CollectionUtils.isEmpty(devices)){
                throw new IOTException(ExceptionEnum.DEVICE_NOT_FOND);
            }
            deviceTypes=new ArrayList<>();
            for (Device device : devices) {
                DeviceType deviceType = selectOneByKey(device.getTypeid());
                deviceTypes.add(deviceType);
            }

        }
        return deviceTypes;
    }

    @Override
    public DeviceType selectOneByKey(String key) {
        DeviceType deviceType=new DeviceType();
        deviceType.setTypekey(key);
        return deviceTypeMapper.selectOne(deviceType);    }

    /**
     * 查询设备，查询所有设备分类，一般用于管理员操作
     * @return
     */
    @Override
    public List<DeviceType> findAll() {
        List<DeviceType> deviceTypes = deviceTypeMapper.selectAll();
        return deviceTypes;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize, DeviceType deviceType) {
        return null;
    }
    @Override
    public PageResult findPage(int pageNum, int pageSize, DeviceType deviceType,User user) {
        //分页
        PageHelper.startPage(pageNum,pageSize);
        Example example=new Example(Device.class);
        Example.Criteria criteria = example.createCriteria();
        //定义集合用于接收查询结果。
        List<DeviceType> deviceTypes=null;
        //用户权限判断
        if(user== null){
            throw new IOTException(ExceptionEnum.USER_NOT_LOGIN);
        }
        //1.当前用户没有权限
        if (user.getAutho() == null || user.getAutho()==0){
            throw new IOTException(ExceptionEnum.USER_NOT_AUTHO);
        }

        //2.当前为管理员。可查询所有的设备
        if(user.getAutho() == 3){
            //设置查询条件
            deviceTypes = deviceTypeMapper.selectByExample(example);
        }else {
            throw new IOTException(ExceptionEnum.USER_NOT_AUTHO);
        }
        //解析分页对象
        PageInfo<DeviceType> info=new PageInfo<>(deviceTypes);
        return new PageResult(info.getTotal(),deviceTypes);
    }

    @Override
    public DeviceType findOne(Integer id) {
        return null;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int updateByid(DeviceType deviceType) {
        return 0;
    }

    @Override
    public int insert(DeviceType deviceType) {
        return 0;
    }
}
