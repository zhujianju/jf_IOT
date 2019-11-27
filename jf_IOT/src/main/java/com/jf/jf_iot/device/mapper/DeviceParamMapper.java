package com.jf.jf_iot.device.mapper;

import com.jf.jf_iot.device.entity.DeviceParam;
import com.jf.jf_iot.device.entity.DeviceType;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface DeviceParamMapper extends Mapper<DeviceParam>, IdListMapper<DeviceType,String> {
}
