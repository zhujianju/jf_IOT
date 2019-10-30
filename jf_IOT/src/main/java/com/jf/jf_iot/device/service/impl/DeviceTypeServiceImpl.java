package com.jf.jf_iot.device.service.impl;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.device.mapper.DeviceTypeMapper;
import com.jf.jf_iot.device.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Override
    public List<DeviceType> findAll() {

        return null;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize, DeviceType deviceType) {
        return null;
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
