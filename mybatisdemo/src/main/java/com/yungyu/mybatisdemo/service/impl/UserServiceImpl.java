package com.yungyu.mybatisdemo.service.impl;

import com.yungyu.mybatisdemo.entity.User;
import com.yungyu.mybatisdemo.mapper.UserMapper;
import com.yungyu.mybatisdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.findUserInfoById(id);
    }

}
