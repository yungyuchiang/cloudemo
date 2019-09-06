package com.yungyu.shiroserver.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    public final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/doLogin")
    public String doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            logger.info("登录成功");
            return "登录成功";
        } catch (AuthenticationException e) {
            logger.info("登录失败");
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/hello")
    public String hello () {
        return "hello";
    }

    @GetMapping("/login")
    public String login () {
        return "please login !";
    }

}
