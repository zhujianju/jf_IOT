package com.jf.jf_iot.device.service;

import com.jf.jf_iot.common.service.BaseService;
import com.jf.jf_iot.device.entity.DeviceParam;
import com.jf.jf_iot.device.entity.DeviceType;
import com.jf.jf_iot.user.entity.User;

import java.util.List;

public interface DeviceParamService extends BaseService<DeviceParam> {
    /**
     * 根据分类id查询分类下的所有参数
     * @return
     */
    List<DeviceParam> findDeviceParamsByid(Integer id);
}
