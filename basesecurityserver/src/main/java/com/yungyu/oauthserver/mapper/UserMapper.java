package com.yungyu.oauthserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yungyu.oauthserver.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<MyUser> {

    List<MyUser> selectAll();

    MyUser selectUserByName(@Param(value = "name") String name);

}
