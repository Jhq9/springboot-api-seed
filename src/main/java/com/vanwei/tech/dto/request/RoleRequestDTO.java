package com.vanwei.tech.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  Role DTO
 *
 *  @author     jin_huaquan
 *  @date      2020/8/4 14:46
 *  @version   1.0
 */
@Data
public class RoleRequestDTO implements Serializable {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2, max = 10, message = "角色名称字符长度范围为2-10")
    private String name;

    /**
     * 角色描述信息
     */
    @NotBlank(message = "角色的备注信息不能为空")
    @Length(max = 140, message = "角色备注信息不能超过140个字符")
    private String remark;
}
