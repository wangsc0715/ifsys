/**
 * Title: RetrunCodeRspDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import java.util.List;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.ReturnCode;

/**
 * Title: RetrunCodeRspDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日上午10:17:17
 *
 */
public class RetrunCodeRspDto extends ResponseDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private List<ReturnCode> list;

	/**
	 * @return the list
	 */
	public List<ReturnCode> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<ReturnCode> list) {
		this.list = list;
	}

}
