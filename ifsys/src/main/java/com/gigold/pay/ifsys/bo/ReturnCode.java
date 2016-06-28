/**
 * Title: ReturnCode.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.bo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.util.common.StringUtil;

/**
 * Title: ReturnCode<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日上午9:23:40
 *
 */
@Component
@Scope("prototype")
public class ReturnCode extends Domain {

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
	 * @param id
	 *            the id to set
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
	 * @param ifId
	 *            the ifId to set
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
	 * @param rspCode
	 *            the rspCode to set
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
	 * @param rspCodeDesc
	 *            the rspCodeDesc to set
	 */
	public void setRspCodeDesc(String rspCodeDesc) {
		this.rspCodeDesc = rspCodeDesc;
	}

	
}
