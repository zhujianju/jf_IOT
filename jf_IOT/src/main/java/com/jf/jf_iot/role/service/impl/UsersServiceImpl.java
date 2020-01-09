package com.jf.jf_iot.role.service.impl;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.role.service.UsersService;
import com.jf.jf_iot.user.entity.User;

import java.util.List;

/**
 * ClassName: UsersServiceImpl
 * Description:
 * date: 2019/11/25 16:59
 *
 * @author 胡正
 * @since JDK 1.8
 */
public class UsersServiceImpl implements UsersService {
    @Override
    public PageResult findPage(int page, int rows, User users, User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize, User user) {
        return null;
    }

    @Override
    public User findOne(Integer id) {
        return null;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int updateByid(User user, User user2) {
        return 0;
    }

    @Override
    public int insert(User user, User user2) {
        return 0;
    }
}
