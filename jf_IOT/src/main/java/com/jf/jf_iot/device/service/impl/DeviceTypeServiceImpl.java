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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
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
                DeviceType deviceType = findOne(device.getTypeid());
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
        PageHelper.startPage(pageNum,pageSize);
        Example example=new Example(DeviceType.class);
        Example.Criteria criteria = example.createCriteria();
        //如果传入的对象不为null
        if(deviceType != null){
            if(StringUtils.isNotBlank(deviceType.getTypename())){
                criteria.andLike("typename","%"+deviceType.getTypename()+"%");
            }
        }
        List<DeviceType> deviceTypes = deviceTypeMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(deviceTypes)){
            throw new IOTException(ExceptionEnum.DEVICETYOE_NOT_FOND);
        }
        //解析分页对象
        PageInfo<DeviceType> info=new PageInfo<>(deviceTypes);

        return new PageResult(info.getTotal(),deviceTypes);
    }
    @Override
    public DeviceType findOne(Integer id) {
        DeviceType deviceType=new DeviceType();
        deviceType.setId(id);
        return deviceTypeMapper.selectOne(deviceType);
    }

    @Override
    public int deleteById(Integer id) {
        //用于删除前，判断是否已经绑定好了设备
        DeviceType dety = findOne(id);
        if(theTypeIsHaveDevice(dety.getId())){
            throw new IOTException(ExceptionEnum.DEVICETYOE_KEY_ISBIND);
        }
        Example example = new Example(DeviceType.class);
        example.createCriteria().andEqualTo("id",id);
        return  deviceTypeMapper.deleteByExample(example);
    }

    @Override
    public int updateByid(DeviceType deviceType,User user) {
   /*     if(selectOneByKey(deviceType.getTypekey()) !=null ){
            throw new IOTException(ExceptionEnum.DEVICETYOE_KEY_UNION);
        }*/
        deviceType.setUpdatetime(new Date());
        return deviceTypeMapper.updateByPrimaryKey(deviceType);
    }

    @Override
    public int insert(DeviceType deviceType,User user) {
        /**
         * 新增前，判断用户是否重复输入key
         */
        if(selectOneByKey(deviceType.getTypekey()) !=null ){
            throw new IOTException(ExceptionEnum.DEVICETYOE_KEY_UNION);
        }
        deviceType.setCreatetime(new Date());
        return deviceTypeMapper.insert(deviceType);
    }

    /**
     * 查看当前设备类型是否绑定上了设备（暂时用于删除和修改前进行查询操作）
     * @return
     */
    private boolean theTypeIsHaveDevice(Integer deviceType){
        Device de=new Device();
        de.setTypeid(deviceType);
        List<Device> device = deviceMapper.select(de);
        if(!CollectionUtils.isEmpty(device)){
            return true;
        }
        return false;
    }


}
