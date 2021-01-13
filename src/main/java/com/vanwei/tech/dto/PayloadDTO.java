package com.vanwei.tech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static com.vanwei.tech.constant.CommonConstants.ZONE_ID_ASIA_SHANGHAI;

/**
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/12/18 14:27
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadDTO implements Serializable {


    /**
     * JWT身份ID
     */
    private String jti;

    /**
     * 签发人
     */
    private String iss;

    /**
     * 过期时长(ms)
     */
    private Long exp;

    /**
     * 主题
     */
    private String sub;

    /**
     * 受众
     */
    private String aud;

    /**
     * 生效时间(1970年1月1日到现在的偏移量)
     */
    private Long nbf;

    /**
     * 签发时间(1970年1月1日到现在的偏移量)
     */
    private Long iat;

    /**
     * 用户的账户名
     */
    private String username;

    /**
     * 判断是否已经过期
     *
     * @return 是否过期
     */
    public boolean isExpired() {
        long currentTimestamp = Instant.now(Clock.system(ZoneId.of(ZONE_ID_ASIA_SHANGHAI))).toEpochMilli();

        return currentTimestamp - nbf >= exp;
    }
}
