/**
 * Title: IfSysMockReqaDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.bo.InterFacePro;
import com.gigold.pay.autotest.bo.InterFaceSysTem;
import com.gigold.pay.autotest.bo.ReturnCode;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.framework.web.ResponseDto;

import java.util.List;

/**
 * Title: IfSysMockReqaDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月30日上午11:39:51
 *
 */
public class IfSysInterFaceInfoRspDto extends ResponseDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	//接口ID
	private InterFaceInfo interFaceInfo;
	private InterFaceSysTem System;
	private InterFacePro Pro;
	private List<ReturnCode> returnCodeList;

	public List<ReturnCode> getReturnCodeList() {
		return returnCodeList;
	}

	public void setReturnCodeList(List<ReturnCode> returnCodeList) {
		this.returnCodeList = returnCodeList;
	}

	public InterFacePro getPro() {
		return Pro;
	}

	public void setPro(InterFacePro pro) {
		Pro = pro;
	}

	public InterFaceSysTem getSystem() {
		return System;
	}

	public void setSystem(InterFaceSysTem system) {
		System = system;
	}

	public InterFaceInfo getInterFaceInfo() {
		return interFaceInfo;
	}

	public void setInterFaceInfo(InterFaceInfo interFaceInfo) {
		this.interFaceInfo = interFaceInfo;
	}

	public String validation(){
//		if(StringUtil.isBlank(this.rspCode)){
//			return CodeItem.RETURN_CODE_IS_NULL;
//		}
//		if(StringUtil.isBlank(this.requestJson)){
//			return CodeItem.REQ_JSON_IS_NULL;
//		}
		return SysCode.SUCCESS;
	}
   
	
	
}
