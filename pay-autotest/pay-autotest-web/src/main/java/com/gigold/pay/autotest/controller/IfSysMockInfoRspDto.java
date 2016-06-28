/**
 * Title: IfSysMockRspDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.framework.web.ResponseDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Title: IfSysMockRspDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月2日下午1:36:56
 *
 */
public class IfSysMockInfoRspDto extends ResponseDto {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private IfSysMock mock;
	private List mockFieldReferList;
	private List mockReferList;

	public List getMockFieldReferList() {
		return mockFieldReferList;
	}

	public void setMockFieldReferList(List mockFieldReferList) {
		this.mockFieldReferList = mockFieldReferList;
	}

	public List getMockReferList() {
		return mockReferList;
	}

	public void setMockReferList(List mockReferList) {
		this.mockReferList = mockReferList;
	}

	public IfSysMock getMock() {
		return mock;
	}

	public void setMock(IfSysMock mock) {
		this.mock = mock;
	}
}
