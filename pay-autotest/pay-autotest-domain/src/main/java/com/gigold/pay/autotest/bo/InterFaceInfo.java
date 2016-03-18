/**
 * Title: InterFaceInfo.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Title: InterFaceInfo<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月3日下午4:49:42
 *
 */
public class InterFaceInfo {
	
	private  int id;
	private String ifName;
	private String sysName;
	private String proName;
	private String ifUrl;
	private String ifDesc;
	private String addressUrl;
	private String method;
	private String methodVersion;
	private String reqJsonStr;
	private String rspJsonStr;
	private String dsname;
	private List<ReturnCode> returnCodeList;
	private List<IfSysMock> mockList=new ArrayList<IfSysMock>();
	private List<Map> mockidList=new ArrayList<>();

	private int ifSysId;
	private int ifProId;


	public List<Map> getMockidList() {
		return mockidList;
	}

	public void setMockidList(List<Map> mockidList) {
		this.mockidList = mockidList;
	}

	public List<ReturnCode> getReturnCodeList() {
		return returnCodeList;
	}

	public void setReturnCodeList(List<ReturnCode> returnCodeList) {
		this.returnCodeList = returnCodeList;
	}

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	/**
	 * @return the ifDesc
	 */
	public String getIfDesc() {
		return ifDesc;
	}

	/**
	 * @param ifDesc the ifDesc to set
	 */
	public void setIfDesc(String ifDesc) {
		this.ifDesc = ifDesc;
	}

	/**
	 * @return the ifSysId
	 */
	public int getIfSysId() {
		return ifSysId;
	}

	/**
	 * @param ifSysId the ifSysId to set
	 */
	public void setIfSysId(int ifSysId) {
		this.ifSysId = ifSysId;
	}

	/**
	 * @return the ifProId
	 */
	public int getIfProId() {
		return ifProId;
	}

	/**
	 * @param ifProId the ifProId to set
	 */
	public void setIfProId(int ifProId) {
		this.ifProId = ifProId;
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
	 * @return the sysName
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName the sysName to set
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return the proName
	 */
	public String getProName() {
		return proName;
	}

	/**
	 * @param proName the proName to set
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}

	/**
	 * @return the ifUrl
	 */
	public String getIfUrl() {
		return ifUrl;
	}

	/**
	 * @param ifUrl the ifUrl to set
	 */
	public void setIfUrl(String ifUrl) {
		this.ifUrl = ifUrl;
	}

	/**
	 * @return the addressUrl
	 */
	public String getAddressUrl() {
		return addressUrl;
	}

	/**
	 * @param addressUrl the addressUrl to set
	 */
	public void setAddressUrl(String addressUrl) {
		this.addressUrl = addressUrl;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the methodVersion
	 */
	public String getMethodVersion() {
		return methodVersion;
	}

	/**
	 * @param methodVersion the methodVersion to set
	 */
	public void setMethodVersion(String methodVersion) {
		this.methodVersion = methodVersion;
	}

	/**
	 * @return the reqJsonStr
	 */
	public String getReqJsonStr() {
		return reqJsonStr;
	}

	/**
	 * @param reqJsonStr the reqJsonStr to set
	 */
	public void setReqJsonStr(String reqJsonStr) {
		this.reqJsonStr = reqJsonStr;
	}

	/**
	 * @return the rspJsonStr
	 */
	public String getRspJsonStr() {
		return rspJsonStr;
	}

	/**
	 * @param rspJsonStr the rspJsonStr to set
	 */
	public void setRspJsonStr(String rspJsonStr) {
		this.rspJsonStr = rspJsonStr;
	}


	/**
	 * @return the mockList
	 */
	public List<IfSysMock> getMockList() {
		return mockList;
	}

	/**
	 * @param mockList
	 *            the mockList to set
	 */
	public void setMockList(List<IfSysMock> mockList) {
		this.mockList = mockList;
	}

}
