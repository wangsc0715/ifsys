package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceInvoker;

public class InterFaceInvokerAddResDto extends ResponseDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private InterFaceInvoker invoker;
	/**
	 * @return the invoker
	 */
	public InterFaceInvoker getInvoker() {
		return invoker;
	}
	/**
	 * @param invoker the invoker to set
	 */
	public void setInvoker(InterFaceInvoker invoker) {
		this.invoker = invoker;
	}

	

}
