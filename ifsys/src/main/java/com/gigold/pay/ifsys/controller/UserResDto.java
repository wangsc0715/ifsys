package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.UserInfo;

public class UserResDto extends ResponseDto{
 
	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
