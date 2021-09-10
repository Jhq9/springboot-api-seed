package com.vanwei.tech.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT Properties
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2021/9/10 11:41
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Header param name
     */
    private String header;

    /**
     * Encode secret
     */
    private String secret;

    /**
     * Token expiration seconds
     */
    private Long expiresIn;

    /**
     * Token prefix
     */
    private String tokenType;
}
