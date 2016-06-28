package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceInfo;

public class InterFaceResponseDto extends ResponseDto {

	private InterFaceInfo interFaceInfo;

	public InterFaceInfo getInterFaceInfo() {
		return interFaceInfo;
	}

	public void setInterFaceInfo(InterFaceInfo interFaceInfo) {
		this.interFaceInfo = interFaceInfo;
	}

}
