/**
 * Title: IfSysRefer.java<br/>
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
 * Title: IfSysRefer<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月9日下午5:07:23
 *
 */
@Component
@Scope("prototype")
public class IfSysRefer extends Domain{
  /** serialVersionUID */
	private static final long serialVersionUID = 1L;
private int id;
  //测试用例ID
  private int mockId;
  //被依赖测试用例ID
  private int refMockId;
  //依赖序号
  private int ordNum;
  //返回码
  private String rspCode;
  //返回码描述
  private String rspCodeDesc;
  //接口名
  private String ifName;
  //用例名称
  private String caseName;
  
  
  
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
 * @return the rspCodeDesc
 */
public String getRspCodeDesc() {
	return rspCodeDesc;
}
/**
 * @param rspCodeDesc the rspCodeDesc to set
 */
public void setRspCodeDesc(String rspCodeDesc) {
	this.rspCodeDesc = rspCodeDesc;
}
/**
 * @return the ifName
 */
public String getIfName() {
	return ifName;
}
/**
 * @param ifName the ifName to set
 */
public void setIfName(String ifName) {
	this.ifName = ifName;
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
 * @return the refMockId
 */
public int getRefMockId() {
	return refMockId;
}
/**
 * @param refMockId the refMockId to set
 */
public void setRefMockId(int refMockId) {
	this.refMockId = refMockId;
}
/**
 * @return the ordNum
 */
public int getOrdNum() {
	return ordNum;
}
/**
 * @param ordNum the ordNum to set
 */
public void setOrdNum(int ordNum) {
	this.ordNum = ordNum;
}
  
}
