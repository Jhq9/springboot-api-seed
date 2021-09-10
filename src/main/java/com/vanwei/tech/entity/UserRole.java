package com.vanwei.tech.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * User Role
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/3 19:05
 */
@Data
@TableName("sys_user_role")
public class UserRole implements Serializable {

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;
}
