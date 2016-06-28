package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.github.pagehelper.PageInfo;

public class InterFacePageResponseDto extends ResponseDto {

	private PageInfo<InterFaceInfo> pageInfo;

	public PageInfo<InterFaceInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<InterFaceInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}

	

}
