package com.jf.jf_iot.role.service;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.service.BaseService;
import com.jf.jf_iot.user.entity.User;

/**
 * ClassName: UsersService
 * Description:
 * date: 2019/11/25 16:53
 *
 * @author 胡正
 * @since JDK 1.8
 */
public interface UsersService extends BaseService<User> {

    PageResult findPage(int page, int rows, User users, User user);
}

