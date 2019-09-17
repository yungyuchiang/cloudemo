package com.yungyu.oauthserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yungyu.oauthserver.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> selectAll();

    Student selectUserByName(@Param(value = "name") String name);

}
