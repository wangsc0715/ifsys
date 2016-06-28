package com.gigold.pay.autotest.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.IfSysMockHistory;
import com.gigold.pay.autotest.jrn.JrnGeneratorService;
import com.gigold.pay.autotest.service.IfSysMockHistoryService;
import com.gigold.pay.framework.base.SpringContextHolder;

@Aspect
@Component
/**
 * 
 * Title: OperatorLogAspect<br/>
 * 记录操作日志: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年10月25日上午9:59:08
 *
 */
public class IfSysMockHistoryAspect extends BaseAspect {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Autowired
	IfSysMockHistoryService ifSysMockHistoryService;
	@Autowired
	JrnGeneratorService jrnGrneratorService;
	private String jrn;

	// Service层切点
	@Pointcut("@annotation(com.gigold.pay.autotest.annotation.IfSysMockHistoryAnnotation)")
	public void serviceAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(com.gigold.pay.autotest.annotation.IfSysMockHistoryAnnotation)")
	public void controllerAspect() {
	}

	@Before("serviceAspect()")
	public void doBefore(JoinPoint joinPoint) {
		debug("dobefore log");
		String targetName = joinPoint.getTarget().getClass().getName();
		if ("com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool".equalsIgnoreCase(targetName)) {
			try {
				jrn = jrnGrneratorService.generateJrn();
			} catch (Exception e) {
				debug("ifSysMockHistoryServiceAspect doBefore 生成批次号有异常");
				e.printStackTrace();
			}
		} else {
			Date d = new Date();
			IfSysMockHistory ifSysMockHistory = (IfSysMockHistory) SpringContextHolder.getBean(IfSysMockHistory.class);
			// 设置请求日期
			Object argsObj = joinPoint.getArgs()[0];
			IfSysMock ifSysMock = null;
			if (argsObj instanceof IfSysMock) {
				ifSysMock = (IfSysMock) argsObj;
			}
			if (ifSysMock == null) {
				debug("ifSysMockHistoryServiceAspect doBefore ifSysMock为null");
				return;
			}

			ifSysMockHistory.setIfId(ifSysMock.getIfId());
			ifSysMockHistory.setMockId(ifSysMock.getId());
			// 设置测试结果
			ifSysMockHistory.setTestResult(ifSysMock.getTestResult());
			// 设置流水号
			ifSysMockHistory.setJrn(jrn);
			// 设置测试日期
			ifSysMockHistory.setTestDt(ymd.format(d));
			// 设置测试时间
			ifSysMockHistory.setTestTm(hms.format(d));
			boolean flag = ifSysMockHistoryService.addIfSysMockHistory(ifSysMockHistory);
			if (!flag) {
				debug("ifSysMockHistoryService.addIfSysMockHistory 方法出现异常");
			}
		}
	}

}
