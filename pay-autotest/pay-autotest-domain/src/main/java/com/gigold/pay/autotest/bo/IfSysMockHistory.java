/**
 * Title: IfSysMockHistory.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gigold.pay.framework.core.Domain;

/**
 * Title: IfSysMockHistory<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月16日下午1:16:12
 *
 */
@Component
@Scope("prototype")
public class IfSysMockHistory extends Domain {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	// 主键
	private long id;
	// 测试数据ID
	private int mockId;
	// 接口ID
	private int ifId;
	// 测试结果
	private String testResult;
	// 测试批次号
	private String jrn;
	// 测试日期
	private String testDt;
	// 测试时间
	private String testTm;
	// 数据状态
	private String status;
	// 接口名
	private String ifName;
	private int ifSysId;

	public int getIfSysId() {
		return ifSysId;
	}

	public void setIfSysId(int ifSysId) {
		this.ifSysId = ifSysId;
	}

	// 所属模块

	public String getIfName() {
		return ifName;
	}

	public void setIfName(String ifName) {
		this.ifName = ifName;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the mockId
	 */
	public int getMockId() {
		return mockId;
	}
	/**
	 * @param mockId the mockId to set
	 */
	public void setMockId(int mockId) {
		this.mockId = mockId;
	}
	/**
	 * @return the ifId
	 */
	public int getIfId() {
		return ifId;
	}
	/**
	 * @param ifId the ifId to set
	 */
	public void setIfId(int ifId) {
		this.ifId = ifId;
	}
	/**
	 * @return the testResult
	 */
	public String getTestResult() {
		return testResult;
	}
	/**
	 * @param testResult the testResult to set
	 */
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	/**
	 * @return the jrn
	 */
	public String getJrn() {
		return jrn;
	}
	/**
	 * @param jrn the jrn to set
	 */
	public void setJrn(String jrn) {
		this.jrn = jrn;
	}
	/**
	 * @return the testDt
	 */
	public String getTestDt() {
		return testDt;
	}
	/**
	 * @param testDt the testDt to set
	 */
	public void setTestDt(String testDt) {
		this.testDt = testDt;
	}
	/**
	 * @return the testTm
	 */
	public String getTestTm() {
		return testTm;
	}
	/**
	 * @param testTm the testTm to set
	 */
	public void setTestTm(String testTm) {
		this.testTm = testTm;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
