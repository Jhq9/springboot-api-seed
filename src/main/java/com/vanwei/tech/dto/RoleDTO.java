package com.vanwei.tech.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 *  角色名称信息
 *
 *  @author     jin_huaquan
 *  @date      2020/9/27 16:19
 *  @version   1.0
 */
@Data
public class RoleDTO implements Serializable {

    /**
     * 角色的ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色的名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String remark;
}
