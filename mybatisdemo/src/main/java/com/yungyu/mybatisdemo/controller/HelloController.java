package com.yungyu.mybatisdemo.controller;

import com.yungyu.mybatisdemo.entity.Result;
import com.yungyu.mybatisdemo.entity.User;
import com.yungyu.mybatisdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mybatis")
public class HelloController {

    @Autowired
    private IUserService userService;

    @Value("${mybatis.mapper-locations}")
    private String mybatisxmllocations;

    @GetMapping("/getinfo/{id}")
    public Result<User> findUserInfoById(@PathVariable int id){
        System.out.println(mybatisxmllocations);
        Result<User> result = new Result<>();
        try{
            User user = userService.getUserById(id);
            if(null != user){
                result.setCode(Result.RESULT_SUCCESS);
                result.setMsg("查询成功");
                result.setData(user);
            } else {
              result.setCode(Result.RESULT_ERROR);
              result.setMsg("查询失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(Result.RESULT_ERROR);
            result.setMsg("查询失败");
        }
        return result;
    }

}
