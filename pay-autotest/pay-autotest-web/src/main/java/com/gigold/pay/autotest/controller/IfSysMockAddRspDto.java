/**
 * Title: IfSysMockPageDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: IfSysMockPageDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月2日下午1:40:56
 *
 */
public class IfSysMockAddRspDto extends ResponseDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private IfSysMock ifSysMock;
	/**
	 * @return the ifSysMock
	 */
	public IfSysMock getIfSysMock() {
		return ifSysMock;
	}
	/**
	 * @param ifSysMock the ifSysMock to set
	 */
	public void setIfSysMock(IfSysMock ifSysMock) {
		this.ifSysMock = ifSysMock;
	}

	
	
	

}
