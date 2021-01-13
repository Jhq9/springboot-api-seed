package com.vanwei.tech.core;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanwei.tech.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志请求Api Logger Aspect
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2018/11/21 14:51
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private ObjectMapper objectMapper;

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.vanwei.tech.annotation.Log)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }

        Object result;
        startTime.set(System.currentTimeMillis());


        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD : {}", request.getMethod());
        log.info("BROWSER : {}", HttpClientUtils.parseBrowser(request));
        log.info("IP : {}", HttpClientUtils.getIpAddress(request));
        log.info("CLASS_METHOD : {}", methodName);
        log.info("ARGS : {}", params + " }");

        result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime.get();
        log.info("SPEND TIME : {}", elapsedTime);
        log.info("RESPONSE : {}", objectMapper.writeValueAsString(result));

        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return result;
    }
}
