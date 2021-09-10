package com.vanwei.tech.core;

import lombok.Data;

/**
 * Base Query Request DTO
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2021/7/19 17:22
 */
@Data
public class BaseQueryRequestDTO {

    /**
     * page
     */
    private int page = 1;

    /**
     * size
     */
    private int size = 10;
}
