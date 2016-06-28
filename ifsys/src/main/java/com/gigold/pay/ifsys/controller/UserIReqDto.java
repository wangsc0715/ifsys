package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.util.common.StringUtil;
import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.ifsys.bo.UserInfo;

public class UserIReqDto extends RequestDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String validation() {
		if (userInfo == null) {
			return CodeItem.USER_PASS_ERROR;
		}
		if (StringUtil.isBlank(userInfo.getLoginName())) {
			return CodeItem.USER_PASS_ERROR;
		}
		if (StringUtil.isBlank(userInfo.getLoginPassword())) {
			return CodeItem.USER_PASS_ERROR;
		}
		return SysCode.SUCCESS;
	}
}
