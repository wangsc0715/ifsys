/**
 * Title: RetrunCodeRspDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: RetrunCodeRspDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日上午10:17:17
 *
 */
public class RetrunCodeAddRspDto extends ResponseDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int id;
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

}
