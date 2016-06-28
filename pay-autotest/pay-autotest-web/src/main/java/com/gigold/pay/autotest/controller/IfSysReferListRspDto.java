/**
 * Title: IfSysMockRspDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import java.util.List;

import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: IfSysMockRspDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月2日下午1:36:56
 *
 */
public class IfSysReferListRspDto extends ResponseDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private List<IfSysRefer>list;
	/**
	 * @return the list
	 */
	public List<IfSysRefer> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<IfSysRefer> list) {
		this.list = list;
	}
	

}
