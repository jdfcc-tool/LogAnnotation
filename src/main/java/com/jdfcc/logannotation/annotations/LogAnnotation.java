package com.jdfcc.logannotation.annotations;

import java.lang.annotation.*;

/**
 * @author Jdfcc
 * @Description 日志注解
 * @DateTime 2023/6/26 10:53
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface LogAnnotation {

}
