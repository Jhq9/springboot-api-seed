package com.vanwei.tech.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.vanwei.tech.dto.PayloadDTO;
import com.vanwei.tech.exception.JwtSignatureVerifyException;
import com.vanwei.tech.properties.JwtProperties;
import com.vanwei.tech.util.JwtUtil;
import com.vanwei.tech.util.Result;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Objects;

import static cn.hutool.core.util.CharsetUtil.UTF_8;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;

/**
 * 对需要进行权限或角色认证的每个请求进行解析与校验
 *
 * @author jinhuaquan
 * @date 2017/7/31
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private final CustomUserDetailsService userDetailsService;

    private final JwtProperties jwtProperties;

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //获取拼接过的token
        String authHeader = request.getHeader(jwtProperties.getHeader());
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("AUTH : {}", authHeader);
        //校验是否拼接过
        if (Objects.nonNull(authHeader) && authHeader.startsWith(jwtProperties.getTokenType())) {
            // The part after "Bearer "
            final String authToken = authHeader.substring(jwtProperties.getTokenType().length() + 1);

            try {
                String payload = JwtUtil.verifySignature(authToken, jwtProperties.getSecret());
                PayloadDTO payloadDTO = objectMapper.readValue(payload, PayloadDTO.class);
                log.info("checking authentication {}", payloadDTO.getUsername());

                if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(payloadDTO.getUsername());

                    //校验TOKEN是否有效
                    if (payloadDTO.isExpired()) {
                        setUnauthorizedResponse(response, "登录已过期，请重新登录");
                        return;
                    }
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (UsernameNotFoundException e) {
                setUnauthorizedResponse(response, "非法的登录凭证");
                return;
            } catch (ParseException e) {
                setUnauthorizedResponse(response, "非法的登录凭证");
                return;
            } catch (JOSEException e) {
                setUnauthorizedResponse(response, "非法的登录凭证");
                return;
            } catch (JwtSignatureVerifyException e) {
                setUnauthorizedResponse(response, "非法的登录凭证");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * 设置认证失败的响应
     *
     * @param response 响应
     * @param message  描述信息
     * @throws IOException
     */
    private void setUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        SecurityContextHolder.clearContext();
        response.setCharacterEncoding(UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        PrintWriter out = response.getWriter();

        out.write(objectMapper.writeValueAsString(Result
                .failed(HttpStatus.UNAUTHORIZED.value(), message)));
    }
}
