package com.Lnn.annotation;


import java.lang.annotation.*;

/**
 * 角色的权限校验
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RoleAuthorize {

    String[] value() default {};

}
