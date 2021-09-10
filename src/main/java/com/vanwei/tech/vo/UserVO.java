package com.vanwei.tech.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.vanwei.tech.dto.RoleDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  用户信息 VO
 *
 *  @author     jin_huaquan
 *  @date      2020/8/4 13:51
 *  @version   1.0
 */
@Data
public class UserVO {

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 账号状态
     */
    private Integer status;

    /**
     * 上次登陆时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 角色列表
     */
    private List<RoleDTO> roleList;
}

