package com.vanwei.tech.exception;


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
