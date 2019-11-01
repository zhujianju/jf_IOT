package com.jf.jf_iot.device.mapper;

import com.jf.jf_iot.device.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DeviceMapper extends Mapper<Device> {
    /**
     * 查询当前登陆用户下所有的设备。
            */
    List<Device> queryDeviceByUserId(Integer userId, @Param("typid")String typeid,@Param("deviceName")String deviceName);
}
