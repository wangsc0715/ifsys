/**
 * Title: IfStsMockRspListDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.framework.web.ResponseDto;

/**
 * Title: IfStsMockRspListDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月2日下午3:30:57
 *
 */
public class IfStsMockRspListDto extends ResponseDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private InterFaceInfo interFaceInfo;
	/**
	 * @return the interFaceInfo
	 */
	public InterFaceInfo getInterFaceInfo() {
		return interFaceInfo;
	}
	/**
	 * @param interFaceInfo the interFaceInfo to set
	 */
	public void setInterFaceInfo(InterFaceInfo interFaceInfo) {
		this.interFaceInfo = interFaceInfo;
	}



}
