package com.yungyu.oauthserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class MyUser {

    @TableId(value = "id")
    private Integer id;

    private String name;

    private String password;

    private Date birthday;

    @TableField(exist = false)
    private boolean accountNonExpired = true;

    @TableField(exist = false)
    private boolean accountNonLocked= true;

    @TableField(exist = false)
    private boolean credentialsNonExpired= true;

    @TableField(exist = false)
    private boolean enabled= true;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
