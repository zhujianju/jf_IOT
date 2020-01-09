package com.jf.jf_iot.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.common.mqtt.MeMqttServer;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.mapper.DeviceMapper;
import com.jf.jf_iot.device.service.DeviceService;
import com.jf.jf_iot.device.service.DeviceTypeService;
import com.jf.jf_iot.user.entity.User;
import com.jf.jf_iot.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeviceTypeService deviceTypeService;

    /*定义头主题,公共主题*/
    public static final String PUBLICTOPIC="public";

    /*订阅地址结尾指定值*/
    public static final String SUBSCRIBER="/user/devmsq";
    /*发布地址结尾指定值*/
    public static final String ISSUE="/user/cloudmsg";
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
        example.setOrderByClause("createtime DESC");
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
                device=new Device();
            }
             devices = deviceMapper.queryDeviceByUserId(user.getId(), device.getTypeid(), device.getName());
            //清除devices中的不可以展示数据项
            clearDeviceKey(devices);
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
            if(StringUtils.isNotBlank(device.getName())){
                criteria.andLike("name","%"+device.getName()+"%");
            }
            if(device.getTypeid() != null){
                criteria.andEqualTo("typeid",device.getTypeid());
            }
        }
    }

    @Override
    public Device findOne(Integer id) {
        return null;
    }

    @Override
    public Device findOne(String id) {
        Device device=new Device();
        device.setId(id);
        Device de = deviceMapper.selectOne(device);
        return de;
    }

    @Override
    public int deleteById(String id) {

        return  deviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int updateByid(Device device,User user) {
        if(user != null){
            device.setLastUpdateId(user.getId());
        }
        device.setUpdatetime(new Date());
        Example example = new Example(Device.class);
        example.createCriteria().andEqualTo("devicename", device.getDevicename());
        return deviceMapper.updateByExample(device,example);
    }

    @Override
    @Transactional
    public int insert(Device device,User user) {
        device.setSaleactivate(0);//设置状态未激活
        String UUid=UUID.randomUUID().toString().replace("-","");
        String productKey=UUid.substring(0,8);
        String deviceName = UUid.substring(9,16);
        String deviceSecret = UUid.substring(17,24);
        device.setProductkey(productKey);
        device.setDevicename(deviceName);
        device.setDevicesecret(deviceSecret);
        device.setIsenable(0);//设置默认未开启
        device.setLifecyclewarn(0);//设置为已入库状态
        /*查找typeKey*/
        DeviceType deivceType = deviceTypeService.findOne(device.getTypeid());
        String typekey = deivceType.getTypekey();
        /*定制前段，相同值*/
        String address="/"+typekey+"/"+productKey+"/"+deviceName;
        /*生成subscriber订阅地址*/
        String subscriber=address;
        /*生成issue发布地址*/
        String issue=address;
        device.setSubscriber(subscriber);
        device.setIssue(issue);
        if(user != null){
            device.setCreatorID(user.getId());
        }
        device.setCreatetime(new Date());
        return deviceMapper.insert(device);
    }


    @Override
    public List<User> findBindUser(String id) {
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

    /**
     * 清空设备中的不可展示的key项。用于清空非管理员的查询项
     * @return
     */
    private List<Device> clearDeviceKey(List<Device> devices){
        for (Device device : devices) {
            device.setProductkey(null);
            device.setDevicename(null);
            device.setDevicesecret(null);
            device.setSubscriber(null);
            device.setIssue(null);
        }
        return devices;
    }

}
