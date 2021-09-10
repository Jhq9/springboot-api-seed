package com.vanwei.tech.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户 DTO
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/4 14:51
 */
@Data
public class UserRequestDTO implements Serializable {

    /**
     * 账号
     */
    @NotEmpty(message = "账号不能为空")
    private String username;

    /**
     * 账号密码
     */
    @NotEmpty(message = "账号密码不能为空")
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    @Email(message = "邮箱地址不合法")
    private String email;

    /**
     * 关联的角色集合
     */
    @NotEmpty(message = "角色ID不能为空")
    private Long[] roleIds;

    /**
     * 账号状态:0-锁定;1-有效
     */
    private Integer status;
}
