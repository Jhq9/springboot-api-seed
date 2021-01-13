package com.vanwei.tech.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanwei.tech.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import static cn.hutool.core.util.CharsetUtil.UTF_8;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;

/**
 * 处理未通过Security认证的请求
 *
 * @author May
 * @version 1.0
 * @date 2018/5/6 14:43
 */
@Component
@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final String AUTHENTICATION_FAIL_MESSAGE = "请先登录";

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        response.setCharacterEncoding(UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        PrintWriter out = response.getWriter();

        out.write(objectMapper.writeValueAsString(Result.failed(HttpStatus.UNAUTHORIZED.value(), AUTHENTICATION_FAIL_MESSAGE)));
    }
}
