package com.jf.jf_iot.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.device.mapper.DeviceMapper;
import com.jf.jf_iot.device.service.DeviceService;
import com.jf.jf_iot.user.entity.User;
import com.jf.jf_iot.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Device> findAll() {
        return null;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize, Device device) {
        return null;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize, Device device, User user) {
        //分页
        PageHelper.startPage(pageNum,pageSize);
        Example example=new Example(Device.class);
        Example.Criteria criteria = example.createCriteria();
        //定义集合用于接收查询结果。
        List<Device> devices=null;
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
            setDeviceExample(device,criteria);
            devices = deviceMapper.selectByExample(example);
            //用于设置是否绑定用户
            devices=isBindUser(devices);
        }
        //3.当前登陆为用户/经销商。
        if(user.getAutho() == 1 || user.getAutho() == 2){
            if(device == null){//防止报空指针异常
                device=new Device(); }

             devices = deviceMapper.queryDeviceByUserId(user.getId(), device.getTypeid(), device.getDevicename());
        }
        //没有查询到设备
        if(CollectionUtils.isEmpty(devices)){
            throw new IOTException(ExceptionEnum.DEVICE_NOT_FOND);
        }

        //解析分页对象
        PageInfo<Device> info=new PageInfo<>(devices);
        return new PageResult(info.getTotal(),devices);
    }


    /**
     * 设置查询条件，名称和分类
     */
    private void setDeviceExample(Device device, Example.Criteria criteria){
        //判断查询条件是否为空
        if(device !=null){
            if(StringUtils.isNotBlank(device.getDevicename())){
                criteria.andLike("devicename","%"+device.getDevicename()+"%");
            }
            if(StringUtils.isNotBlank(device.getTypeid())){
                criteria.andEqualTo("typeid",device.getTypeid());
            }
        }
    }

    @Override
    public Device findOne(Integer id) {
        Device device=new Device();
        device.setId(id);
        Device de = deviceMapper.selectOne(device);
        return de;
    }

    @Override
    public int deleteById(Integer id) {

        return  deviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByid(Device device) {

        return deviceMapper.updateByPrimaryKey(device);
    }

    @Override
    public int insert(Device device) {
        device.setSaleactivate(0);
        return deviceMapper.insert(device);
    }

    @Override
    public List<User> findBindUser(Integer id) {
        List<User> users = deviceMapper.findBindUser(id);

        return users;
    }

    /**
     * 用于设置，设备是否绑定了用户
     * @param devices
     */
    private List<Device> isBindUser(List<Device> devices){
        for (Device device : devices) {
            List<User> users = findBindUser(device.getId());
            if(users !=null && users.size()>0){
                device.setIsBind(true);
            }else {
                device.setIsBind(false);
            }
        }
        return devices;
    }

}
