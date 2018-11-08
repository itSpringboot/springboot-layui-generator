package com.jcy.factory.generator.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
* @ClassName: ModelAttribute
* @Description: 可以设置写入视图的键别名
*/
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ModelAttribute {
	
	@AliasFor("key")
	String value() default "";
	
	@AliasFor("value")
	String key() default "";

}
