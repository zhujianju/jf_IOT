package com.jf.jf_iot.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.device.entity.DeviceParam;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.mapper.DeviceMapper;
import com.jf.jf_iot.device.mapper.DeviceParamMapper;
import com.jf.jf_iot.device.mapper.DeviceTypeMapper;
import com.jf.jf_iot.device.service.DeviceParamService;
import com.jf.jf_iot.device.service.DeviceTypeService;
import com.jf.jf_iot.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceParamServiceImpl implements DeviceParamService {
    /*@Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Autowired
    private DeviceMapper deviceMapper;*/
    @Autowired
    private DeviceParamMapper deviceParamMapper;




    @Override
    public PageResult findPage(int pageNum, int pageSize, DeviceParam deviceParam) {
        PageHelper.startPage(pageNum,pageSize);
        Example example=new Example(DeviceParam.class);
        Example.Criteria criteria = example.createCriteria();
        //如果传入的对象不为null
        System.out.println(deviceParam);
        if(deviceParam != null){
            if(deviceParam.getClassificationId() !=null){
                    criteria.andEqualTo("classificationId",deviceParam.getClassificationId());
            }
        }
        example.setOrderByClause("createtime DESC");
        List<DeviceParam> deviceParams = deviceParamMapper.selectByExample(example);
        //没有查询到设备
        if(CollectionUtils.isEmpty(deviceParams)){
            throw new IOTException(ExceptionEnum.DEVICEPARAM_NOT_FOND);
        }
        //解析分页对象
        PageInfo<DeviceParam> info=new PageInfo<>(deviceParams);
        return new PageResult(info.getTotal(),deviceParams);
    }

    @Override
    public DeviceParam findOne(Integer id) {
        DeviceParam deviceParam=new DeviceParam();
        deviceParam.setId(id);
        return deviceParamMapper.selectOne(deviceParam);
    }

    @Override
    public int deleteById(Integer id) {
        //用于删除前，判断是否已经绑定好了分类
        Example example = new Example(DeviceParam.class);
        example.createCriteria().andEqualTo("id",id);
        return  deviceParamMapper.deleteByExample(example);
    }

    @Override
    public int updateByid(DeviceParam deviceParam,User user) {
   /*     if(selectOneByKey(deviceType.getTypekey()) !=null ){
            throw new IOTException(ExceptionEnum.DEVICETYOE_KEY_UNION);
        }*/
        deviceParam.setUpdatetime(new Date());
        deviceParam.setLastUpdateId(user.getId());
        return deviceParamMapper.updateByPrimaryKey(deviceParam);
    }

    @Override
    public int insert(DeviceParam deviceParam,User user) {
        deviceParam.setCreatetime(new Date());
        deviceParam.setCreatorID(user.getId());
        return deviceParamMapper.insert(deviceParam);
    }
    @Override
    public List<DeviceParam> findAll() {
        return null;
    }

    @Override
    public List<DeviceParam> findDeviceParamsByid(Integer id) {
        DeviceParam deviceParam=new DeviceParam();
        deviceParam.setClassificationId(id);
        List<DeviceParam> params = deviceParamMapper.select(deviceParam);
        if(CollectionUtils.isEmpty(params)){
            throw new IOTException(ExceptionEnum.DEVICEPARAM_NOT_FOND);
        }
        return params;
    }
}
