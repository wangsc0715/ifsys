/**
 * Title: IfSysMockPageDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: IfSysMockPageDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月2日下午1:40:56
 *
 */
public class IfSysMockPageDto extends RequestDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int pageNum;
	private String ifName;
	private int ifSysId;
	private int ifProId;

	
	
	
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
	

}
