/**
 * Title: UserControllerTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpSession;

import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.UserInfoService;

/**
 * Title: UserControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月17日下午4:56:41
 *
 */

@RunWith(PowerMockRunner.class)
public class UserControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	UserInfoService userInfoService;
	@Mock
	UserIReqDto rdto;
	private HttpSession session = new MockHttpSession();
	/** ====================== 测试对象定义 ========================== **/
	private UserController userController = new UserController();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		userController.setUserInfoService(userInfoService);
	}

	@Test
	public void login() {
		UserResDto dto = new UserResDto();
		when(rdto.validation()).thenReturn(CodeItem.USER_PASS_ERROR).thenReturn(SysCode.SUCCESS);
		dto = userController.login(rdto, session);
		// 用户名或密码不能为空
		Assert.assertEquals(CodeItem.USER_PASS_ERROR, dto.getRspCd());
		
		when(userInfoService.login((any(UserInfo.class)))).thenReturn(null).thenReturn(new UserInfo());
		dto = userController.login(rdto, session);
		// 登录失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		
		// 登录成功
		dto = userController.login(rdto, session);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(session.getAttribute(SystemPropertyConfigure.getLoginKey()));
	}
}
