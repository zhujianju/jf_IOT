package com.jf.jf_iot.device.service;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.service.BaseService;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.user.entity.User;

import java.util.List;

public interface DeviceTypeService extends BaseService<DeviceType> {

    public List<DeviceType> findAll(User user);
    public PageResult findPage(int pageNum, int pageSize, DeviceType deviceType, User user);
    public DeviceType selectOneByKey(String key);
}
