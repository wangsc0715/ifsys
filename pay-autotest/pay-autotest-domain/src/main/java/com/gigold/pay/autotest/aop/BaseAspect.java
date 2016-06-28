package com.gigold.pay.autotest.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;

import com.gigold.pay.framework.core.Domain;

public class BaseAspect extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	 public final static SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
	 public final static SimpleDateFormat hms = new SimpleDateFormat("HHmmss");
	/**
	 * 
	 * Title: getServiceMethodDescription<br/>
	 * 获取方法注解的描述信息: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年10月21日下午2:16:28
	 *
	 * @param joinPoint
	 * @return
	 */
	public String getServiceMethodDescription(JoinPoint joinPoint) {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = null;
		try {
			targetClass = Class.forName(targetName);
		} catch (ClassNotFoundException e) {
			debug("找不到存在的类名");
		}
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					//description = method.getAnnotation(OperatorServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}
}
