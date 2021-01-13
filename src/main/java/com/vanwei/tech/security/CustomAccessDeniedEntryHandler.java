package com.vanwei.tech.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanwei.tech.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import static cn.hutool.core.util.CharsetUtil.UTF_8;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;

/**
 * AccessDeniedException Entry point
 *
 * @author May
 * @version 1.0
 * @date 2020/1/2 12:57
 */
@Component
@AllArgsConstructor
public class CustomAccessDeniedEntryHandler implements AccessDeniedHandler, Serializable {

    private final ObjectMapper objectMapper;

    private static final String ACCESS_DENIED_MESSAGE = "权限不足";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        response.setCharacterEncoding(UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        PrintWriter out = response.getWriter();

        out.write(objectMapper.writeValueAsString(Result.failed(HttpStatus.UNAUTHORIZED.value(), ACCESS_DENIED_MESSAGE)));
    }
}
