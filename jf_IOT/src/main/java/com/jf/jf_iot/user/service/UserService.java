package com.jf.jf_iot.user.service;

import com.jf.jf_iot.common.service.BaseService;
import com.jf.jf_iot.user.entity.User;

public interface UserService extends BaseService<User> {
    User findOne(User user);
}
