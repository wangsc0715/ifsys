package com.gigold.pay.ifsys.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.UserInfoService;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {
	@Autowired
	UserInfoService userInfoService;

	/**
	 * @return the userInfoService
	 */
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	/**
	 * @param userInfoService the userInfoService to set
	 */
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@RequestMapping(value = "/login.do")
	public @ResponseBody UserResDto login(@RequestBody UserIReqDto rdto, HttpSession sessin) {
		UserResDto dto = new UserResDto();
		// 验证请求参数合法性
		String code = rdto.validation();
		// 没有通过则返回对应的返回码
		if (!"00000".equals(code)) {
			dto.setRspCd(code);
			return dto;
		}
		UserInfo user = userInfoService.login(rdto.getUserInfo());
		if (user == null) {
			dto.setRspCd(CodeItem.IF_FAILURE);
		} else {
			sessin.setAttribute(SystemPropertyConfigure.getLoginKey(), user);
			dto.setUserInfo(user);
			dto.setRspCd(SysCode.SUCCESS);
		}

		return dto;
	}

}
