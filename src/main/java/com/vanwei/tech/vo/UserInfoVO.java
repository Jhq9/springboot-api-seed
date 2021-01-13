package com.vanwei.tech.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息 VO
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/12/18 15:07
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserInfoVO extends UserVO {

    /**
     * access token
     */
    private String accessToken;

    /**
     * 过期时长(ms)
     */
    private Long expiresIn;

    /**
     * token类别
     */
    private String tokenType;
}
