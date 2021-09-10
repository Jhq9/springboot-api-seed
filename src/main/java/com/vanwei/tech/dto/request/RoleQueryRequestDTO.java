package com.vanwei.tech.dto.request;

import com.vanwei.tech.core.BaseQueryRequestDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Role Query Request DTO
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2021/9/10 14:46
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleQueryRequestDTO extends BaseQueryRequestDTO {

    /**
     * 角色名称
     */
    private String name;
}
