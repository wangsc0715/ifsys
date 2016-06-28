/**
 * Title: InterFaceInvokerControllerTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpSession;

import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.ifsys.bo.InterFaceInvoker;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.InterFaceInvokerService;

/**
 * Title: InterFaceInvokerControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月18日上午9:34:22
 *
 */
@RunWith(PowerMockRunner.class)
public class InterFaceInvokerControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceInvokerService interFaceInvokerService;
	@Mock
	InterFaceInvokerReqDto rdto;
	private HttpSession session = new MockHttpSession();
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceInvokerController interFaceInvokerController = new InterFaceInvokerController();
	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceInvokerController.setInterFaceInvokerService(interFaceInvokerService);
	}
	@Test
	public void addInterFaceInvoker(){
		InterFaceInvokerAddResDto dto = new InterFaceInvokerAddResDto();
		when(rdto.vaildate()).thenReturn(CodeItem.REMARK_FAILURE).thenReturn(SysCode.SUCCESS);
		dto = interFaceInvokerController.addInterFaceInvoker(rdto, session);
		//关注者调用信息不能为空
		Assert.assertEquals(CodeItem.REMARK_FAILURE, dto.getRspCd());
		
		
		dto = interFaceInvokerController.addInterFaceInvoker(rdto, session);
		// 用户未登录
		Assert.assertEquals(SysCode.SYS_FAIL, dto.getRspCd());
		session.setAttribute(SystemPropertyConfigure.getLoginKey(), new UserInfo());
		
		when(interFaceInvokerService.addInterFaceInvoker((any(InterFaceInvoker.class)))).thenReturn(null).thenReturn(new InterFaceInvoker());
		//关注失败
		dto = interFaceInvokerController.addInterFaceInvoker(rdto, session);
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		dto = interFaceInvokerController.addInterFaceInvoker(rdto, session);
		//关注成功
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getInvoker());
	}

	
	@Test
	@PrepareForTest({ SpringContextHolder.class })
	public void getInvokerListByFollowId() {
		PowerMockito.mockStatic(SpringContextHolder.class);
		InterFaceInvokerResDto dto=new InterFaceInvokerResDto();
		when(interFaceInvokerService.getInvokerList(any(InterFaceInvoker.class))).thenReturn(null)
				.thenReturn(new ArrayList());
		when(SpringContextHolder.getBean(InterFaceInvoker.class)).thenReturn(new InterFaceInvoker());
		dto = interFaceInvokerController.getInvokerListByFollowId(rdto);
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		Assert.assertNull(dto.getList());
		// 获取成功
		dto = interFaceInvokerController.getInvokerListByFollowId(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getList());
	}
}
