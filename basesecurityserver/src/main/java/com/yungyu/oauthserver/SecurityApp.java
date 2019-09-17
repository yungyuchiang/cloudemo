package com.yungyu.oauthserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// @MapperScan("com.yungyu.oauthserver.mapper")
@EnableCaching
public class SecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);
        // System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }

}