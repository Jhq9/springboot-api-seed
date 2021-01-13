package com.vanwei.tech.util;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.vanwei.tech.constant.CommonConstants.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

/**
 * Result Generator
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/7/22 9:25
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> Result<T> ok() {
        return restResult(null, OK.value(), SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, OK.value(), SUCCESS);
    }

    public static <T> Result<T> ok(T data, String message) {
        return restResult(data, OK.value(), message);
    }

    public static <T> Result<T> failed() {
        return restResult(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, BAD_REQUEST.value(), msg);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result<T> failed(int code, String msg, T data) {
        return restResult(data, code, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);

        return apiResult;
    }
}
