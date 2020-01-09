package com.jf.jf_iot.device.service;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.service.BaseService;
import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.user.entity.User;

import java.util.List;

public interface DeviceService extends BaseService<Device> {
    public PageResult findPage(int pageNum, int pageSize, Device device, User user);

    public List<User> findBindUser(String id);

    /**
     * 设备id(String 32位 )
     * @param id
     * @return
     */
    public Device findOne(String id);

    public int deleteById(String id);

}
