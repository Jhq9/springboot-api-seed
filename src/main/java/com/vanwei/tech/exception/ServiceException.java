package com.vanwei.tech.exception;

/**
 * Service Exception
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2018/11/21 10:06
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
