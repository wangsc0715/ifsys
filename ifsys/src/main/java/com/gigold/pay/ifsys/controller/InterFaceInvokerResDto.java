package com.gigold.pay.ifsys.controller;

import java.util.List;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceInvoker;

public class InterFaceInvokerResDto extends ResponseDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private List<InterFaceInvoker> list;

	/**
	 * @return the list
	 */
	public List<InterFaceInvoker> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<InterFaceInvoker> list) {
		this.list = list;
	}

}
