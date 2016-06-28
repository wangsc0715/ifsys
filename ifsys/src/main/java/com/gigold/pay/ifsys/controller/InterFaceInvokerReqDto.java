/**
 * Title: InterFaceInvokerAddDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import org.springframework.util.StringUtils;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: InterFaceInvokerAddDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月23日下午3:29:39
 *
 */
public class InterFaceInvokerReqDto extends RequestDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private long id;
	private long ifFollowId;
	private int uId;
	private long ifFollowedId;
	private String remark;

	
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
	 * @return the uId
	 */
	public int getuId() {
		return uId;
	}

	/**
	 * @param uId the uId to set
	 */
	public void setuId(int uId) {
		this.uId = uId;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the ifFollowId
	 */
	public long getIfFollowId() {
		return ifFollowId;
	}

	/**
	 * @param ifFollowId
	 *            the ifFollowId to set
	 */
	public void setIfFollowId(long ifFollowId) {
		this.ifFollowId = ifFollowId;
	}

	/**
	 * @return the ifFollowedId
	 */
	public long getIfFollowedId() {
		return ifFollowedId;
	}

	/**
	 * @param ifFollowedId the ifFollowedId to set
	 */
	public void setIfFollowedId(long ifFollowedId) {
		this.ifFollowedId = ifFollowedId;
	}

	public String vaildate() {
		if(StringUtils.isEmpty(this.remark)){
			return CodeItem.REMARK_FAILURE;
		}
		if(ifFollowedId==0){
			return CodeItem.FLLOW_IF_ID_FAILURE;
		}
		return SysCode.SUCCESS;
	}

}
