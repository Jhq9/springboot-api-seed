package com.vanwei.tech.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.vanwei.tech.vo.UserVO;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息 DTO
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/9/27 16:45
 */
@Data
public class UserDTO implements Serializable {

    /**
     * 用户信息
     */
    @JsonUnwrapped
    private UserVO userVO;
}
