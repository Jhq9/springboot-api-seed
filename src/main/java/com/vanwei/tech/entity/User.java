package com.vanwei.tech.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.vanwei.tech.core.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/3 18:45
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class User extends BaseEntity {

    /**
     * 用户账号
     */
    @TableField("username")
    private String username;

    /**
     * 账号密码
     */
    @TableField("password")
    private String password;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 账号状态:0-锁定;1-有效;9删除
     */
    @TableField("status")
    private Integer status;

    /**
     * 上次登陆时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
}
