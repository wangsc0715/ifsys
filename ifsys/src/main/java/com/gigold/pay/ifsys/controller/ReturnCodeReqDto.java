/**
 * Title: ReturnCodeAddDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.util.common.StringUtil;
import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: ReturnCodeAddDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月5日上午9:55:44
 *
 */
public class ReturnCodeReqDto extends RequestDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int id;
	private int ifId;
	private String rspCode;
	private String rspCodeDesc;
	
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
	public String validation() {
		if (this.ifId == 0) {
			return CodeItem.IF_ID_FAILURE;
		}
		if (StringUtil.isBlank(this.rspCode)) {
			return CodeItem.RSP_CODE_FAILURE;
		}
		if (StringUtil.isBlank(this.rspCodeDesc)) {
			return CodeItem.RSP_CODE_DESC_FAILURE;
		}
		return SysCode.SUCCESS;
	}
	
}
