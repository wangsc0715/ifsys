/**
 * Title: IfSysMockReqaDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.util.common.StringUtil;
import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: IfSysMockReqaDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月30日上午11:39:51
 *
 */
public class IfSysMockAddReqDto extends RequestDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	//主键
	private int id;
	//接口ID
	private int ifId;
	//期望返回码
	private String rspCode;
	//期望请求报文
	private String requestJson;
	//期望响应报文
	private String responseJson;
	//测试结果
	private String testResult;
	//真实返回码
	private String realRspCode;
	//真实返回报文
	private String realResponseJson;
	
	private int rspCodeId;
	
	private String caseName;

	private String requestPath; // 用例请求地址


	private String checkJson; //用例数据自动生成规则描述 json格式 有格式要求
	private String rspRefJson;//描述依赖其他用例的字段取值情况，多个字段之间用英文逗号隔开

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	/**
	 * @return the checkJson
	 */
	public String getCheckJson() {
		return checkJson;
	}
	/**
	 * @param checkJson the checkJson to set
	 */
	public void setCheckJson(String checkJson) {
		this.checkJson = checkJson;
	}
	/**
	 * @return the rspRefJson
	 */
	public String getRspRefJson() {
		return rspRefJson;
	}
	/**
	 * @param rspRefJson the rspRefJson to set
	 */
	public void setRspRefJson(String rspRefJson) {
		this.rspRefJson = rspRefJson;
	}
	/**
	 * @return the caseName
	 */
	public String getCaseName() {
		return caseName;
	}
	/**
	 * @param caseName the caseName to set
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	/**
	 * @return the rspCodeId
	 */
	public int getRspCodeId() {
		return rspCodeId;
	}
	/**
	 * @param rspCodeId the rspCodeId to set
	 */
	public void setRspCodeId(int rspCodeId) {
		this.rspCodeId = rspCodeId;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the realRspCode
	 */
	public String getRealRspCode() {
		return realRspCode;
	}
	/**
	 * @param realRspCode the realRspCode to set
	 */
	public void setRealRspCode(String realRspCode) {
		this.realRspCode = realRspCode;
	}
	/**
	 * @return the realResponseJson
	 */
	public String getRealResponseJson() {
		return realResponseJson;
	}
	/**
	 * @param realResponseJson the realResponseJson to set
	 */
	public void setRealResponseJson(String realResponseJson) {
		this.realResponseJson = realResponseJson;
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
	 * @return the rspCode
	 */
	public String getRspCode() {
		return rspCode;
	}
	/**
	 * @param rspCode the rspCode to set
	 */
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	/**
	 * @return the requestJson
	 */
	public String getRequestJson() {
		return requestJson;
	}
	/**
	 * @param requestJson the requestJson to set
	 */
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	/**
	 * @return the responseJson
	 */
	public String getResponseJson() {
		return responseJson;
	}
	/**
	 * @param responseJson the responseJson to set
	 */
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	
	public String validation(){
//		if(StringUtil.isBlank(this.rspCode)){
//			return CodeItem.RETURN_CODE_IS_NULL;
//		}
//		if(StringUtil.isBlank(this.requestJson)){
//			return CodeItem.REQ_JSON_IS_NULL;
//		}
		return SysCode.SUCCESS;
	}
   
	
	
}
