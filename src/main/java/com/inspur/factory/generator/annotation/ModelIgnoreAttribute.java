package com.inspur.factory.generator.annotation;

import java.lang.annotation.*;

/**
* @ClassName: ModelIgnoreAttribute
* @Description: 标记了该注解的成员变量不写入视图
*/
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ModelIgnoreAttribute {
}
