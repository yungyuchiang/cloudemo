package com.yungyu.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan(value = "com.yungyu.mybatisdemo.mapper")
@SpringBootApplication
@EnableEurekaClient
public class MyBatisDemoApp {

    public static void main(String[] args){
        SpringApplication.run(MyBatisDemoApp.class, args);
    }

}
