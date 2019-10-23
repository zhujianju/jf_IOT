package com.jf.jf_iot.user.service.impl;

import com.jf.jf_iot.common.entity.PageResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import com.jf.jf_iot.user.entity.User;
import com.jf.jf_iot.user.mapper.UserMapper;
import com.jf.jf_iot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
    public int updateByid(User user) {
        return 0;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public User findOne(User user) {
        User us = userMapper.selectOne(user);
        if(us == null){
            throw new IOTException(ExceptionEnum.USER_NOT_FOND);
        }
        return us;
    }
}
