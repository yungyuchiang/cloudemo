package com.yungyu.mybatisdemo.mapper;

import com.yungyu.mybatisdemo.entity.User;

public interface UserMapper {

    User findUserInfoById(int id);

}
