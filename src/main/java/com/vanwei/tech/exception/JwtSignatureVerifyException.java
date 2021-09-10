package com.vanwei.tech.exception;

/**
 * JWT Signature verify exception
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2021/9/10 11:43
 */
public class JwtSignatureVerifyException extends Exception {

    /**
     * Creates a new JwtSignatureVerifyException with the specified message.
     *
     * @param message The exception message.
     */
    public JwtSignatureVerifyException(String message) {
        super(message);
    }

    /**
     * Creates a new JwtSignatureVerifyException with the specified message and cause.
     *
     * @param message The exception message.
     * @param cause   The exception cause.
     */
    public JwtSignatureVerifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
