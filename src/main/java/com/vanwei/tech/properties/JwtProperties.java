package com.vanwei.tech.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
