package com.yungyu.test;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yungyu.oauthserver.SecurityApp;
import com.yungyu.oauthserver.entity.MyUser;
import com.yungyu.oauthserver.entity.Student;
import com.yungyu.oauthserver.mapper.UserMapper;
import com.yungyu.oauthserver.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityApp.class})
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IStudentService studentService;

    @Test
    public void testOne () {
        System.out.println("test hello 1");
    }

    @Test
    public void testUserSelect () {
        userMapper.selectAll().stream().forEach(System.out::println);
        System.out.println(userMapper);
        MyUser user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void queryStudentByNo () {
        Student student = studentService.queryStudentByNo("JSD010201");
        System.out.println(student);
    }

    @Test
    public void updateStudent() {
        Student student = studentService.queryStudentByNo("JSD010202");
        System.out.println(student);
        student.setName("莫凌山");
        studentService.update(student);
        Student newStudent = studentService.queryStudentByNo(student.getNo());
        System.out.println(newStudent);
    }

}