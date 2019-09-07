package com.yungyu.oauthserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "Hello Spring Security";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String adminAuth () {
        return "您拥有admin权限，可以查看";
    }

}
