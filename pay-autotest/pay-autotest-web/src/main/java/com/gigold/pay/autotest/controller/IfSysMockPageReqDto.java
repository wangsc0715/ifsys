/**
 * Title: IfSysMockReqaDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: IfSysMockReqaDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月30日上午11:39:51
 *
 */
public class IfSysMockPageReqDto extends RequestDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int pageNum;
	private int pageSize;
	private int ifSysId;
	private int ifProId;
	private String  ifName;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}


	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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
