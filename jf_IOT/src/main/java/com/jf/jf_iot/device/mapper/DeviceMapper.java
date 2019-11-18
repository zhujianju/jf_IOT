package com.jf.jf_iot.device.mapper;

import com.jf.jf_iot.device.entity.Device;
import com.jf.jf_iot.user.entity.User;
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

    /**
     * 查询当前设备属于哪些拥有者
     * 根据设备id，在用户设备关联表中查询出对应的关联用户。多对多关系，所以用户可以存在多个
     * @param id
     * @return
     */
    @Select("select sys_user.* from sys_user where id in (select userId from con_device_user where deviceId = #{id})")
    List<User> findBindUser(Integer id);





}
