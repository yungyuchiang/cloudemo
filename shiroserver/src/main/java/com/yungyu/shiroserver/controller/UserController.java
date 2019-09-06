package com.yungyu.shiroserver.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/userList")
    @RequiresPermissions("admin:write")
    public List<String> getUserNameStrs () {
        return Arrays.asList("123", "456", "789");
    }

}
