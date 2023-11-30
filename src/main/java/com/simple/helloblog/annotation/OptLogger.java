package com.simple.helloblog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 *
 * @author 魑魅魍魉
 * @date 2023/11/28 21:26:47
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLogger {

    /**
     * 操作描述
     *
     * @return {@link String}
     */
    String value() default "";

}
