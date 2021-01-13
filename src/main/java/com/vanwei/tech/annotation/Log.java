package com.vanwei.tech.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Log 注解
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2019/12/18 23:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * @return 接口描述
     */
    String value() default "";

    /**
     * @return 是否记录下该次操作
     */
    boolean record() default false;
}
