package com.vanwei.tech.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录请求
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/12/18 15:01
 */
@Data
public class UserLoginRequestDTO implements Serializable {

    /**
     * 用户账号
     */
    @NotBlank(message = "用户登录账号不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户登录密码不能为空")
    private String password;
}
