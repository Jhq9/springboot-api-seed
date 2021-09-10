package com.vanwei.tech.dto.request;

import com.vanwei.tech.core.BaseQueryRequestDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User Query Request DTO
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2021/9/10 14:40
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserQueryRequestDTO extends BaseQueryRequestDTO {

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String mobile;
}
