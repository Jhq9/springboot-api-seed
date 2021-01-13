package com.vanwei.tech.core;

import com.vanwei.tech.exception.JwtSignatureVerifyException;
import com.vanwei.tech.exception.ServiceException;
import com.vanwei.tech.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.security.SignatureException;

import static org.springframework.http.HttpStatus.*;

/**
 * Global Exception Handler
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2019/12/16 8:42
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handleServiceException(ServiceException exception) {
        log.error("Global Exception Handler-业务异常 : {}", exception.getMessage());
        exception.printStackTrace();

        return Result.failed(exception.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        log.error("请求参数缺失异常-业务异常 : {}", exception.getMessage());
        exception.printStackTrace();

        return Result.failed(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Global Exception Handler-请求参数不满足条件错误 : {}", exception.getMessage());
        exception.printStackTrace();
        return Result.failed(exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("Global Exception Handler-请求参数不满足条件错误 : {}", exception.getMessage());
        exception.printStackTrace();
        return Result.failed(exception.getConstraintViolations().iterator().next().getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("Global Exception Handler-请求参数异常 : {}", exception.getMessage());
        exception.printStackTrace();
        return Result.failed(exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result handleAccessDeniedException(AccessDeniedException exception) {
        log.error("Global Exception Handler-权限不足或拒绝访问 : {}", exception.getMessage());
        exception.printStackTrace();
        Result result = new Result();

        return result.setCode(FORBIDDEN.value()).setMsg("没有足够的权限访问该接口");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public Result handleBadCredentialsException(BadCredentialsException exception) {
        log.error("Global Exception Handler-账号密码错误 : {}", exception.getMessage());
        exception.printStackTrace();
        Result result = new Result();

        return result.setCode(UNAUTHORIZED.value())
                .setMsg("账号或密码错误");
    }

    @ExceptionHandler(JwtSignatureVerifyException.class)
    @ResponseBody
    public Result handleJwtSignatureVerifyException(JwtSignatureVerifyException exception) {
        log.error("Global Exception Handler-JWT 签名错误 : {}", exception.getMessage());
        exception.printStackTrace();
        Result result = new Result();

        return result.setCode(UNAUTHORIZED.value()).setMsg(exception.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public Result handleSignatureException(SignatureException exception) {
        log.error("Global Exception Handler-JWT 签名错误 : {}", exception.getMessage());
        exception.printStackTrace();
        Result result = new Result();

        return result.setCode(UNAUTHORIZED.value()).setMsg("登录凭证签名错误");
    }

    /**
     * @Descript 统一处理文件过大问题.
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public Result handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        log.error("Global Exception Handler-上传文件过大 : {}", exception.getMessage());
        exception.printStackTrace();

        return Result.failed("上传文件过大,上传最大允许文件大小为10MB");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(HttpServletRequest request, Exception exception, Object handler) {
        log.error("Global Exception Handler-未知异常 : {}", exception.getMessage());
        exception.printStackTrace();

        String message;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                    request.getRequestURI(),
                    handlerMethod.getBean().getClass().getName(),
                    handlerMethod.getMethod().getName(),
                    exception.getMessage());
        } else {
            message = exception.getMessage();
        }

        log.error("Error Message : {}", message);

        Result result = new Result();

        return result.setCode(INTERNAL_SERVER_ERROR.value())
                .setMsg("接口 [" + request.getRequestURI() + "] 内部错误[" + exception.getMessage() + "]，请联系管理员");
    }
}
