/**
 * Title: IfSysMockReqaDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;

/**
 * Title: IfSysMockReqaDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月30日上午11:39:51
 *
 */
public class IfSysInterFaceReqDto extends RequestDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	//接口ID
	private int ifId;

	/**
	 * @return the ifId
	 */
	public int getIfId() {
		return ifId;
	}
	/**
	 * @param ifId the ifId to set
	 */
	public void setIfId(int ifId) {
		this.ifId = ifId;
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
