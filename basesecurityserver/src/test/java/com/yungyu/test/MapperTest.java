package com.yungyu.test;

import com.yungyu.oauthserver.SecurityApp;
import com.yungyu.oauthserver.entity.MyUser;
import com.yungyu.oauthserver.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityApp.class})
public class MapperTest {

    @Resource
    private UserMapper userMapper;

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

}