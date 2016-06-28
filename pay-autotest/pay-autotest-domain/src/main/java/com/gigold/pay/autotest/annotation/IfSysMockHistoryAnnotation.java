package com.gigold.pay.autotest.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Title:IfSysMockHistoryAnnotation <br/>
 * > Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年10月21日下午2:27:52
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IfSysMockHistoryAnnotation {
	String description() default "";

	String value();
}
