/**
 * Title: InterFaceControllerTest.java<br/>
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
import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.bo.InterFacePro;
import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.bo.MyPageInfo;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.InterFaceFieldService;
import com.gigold.pay.ifsys.service.InterFaceProService;
import com.gigold.pay.ifsys.service.InterFaceService;
import com.gigold.pay.ifsys.service.InterFaceSysService;
import com.gigold.pay.ifsys.service.UserInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Title: InterFaceControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月18日上午10:33:01
 *
 */

@RunWith(PowerMockRunner.class)
public class InterFaceControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceService interFaceService;
	@Mock
	UserInfoService userInfoService;
	@Mock
	InterFaceSysService interFaceSysService;
	@Mock
	InterFaceProService interFaceProService;
	@Mock
	InterFaceFieldService interFaceFieldService;
	@Mock
	InterFaceRequestDto rdto;
	private HttpSession session = new MockHttpSession();
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceController interFaceController = new InterFaceController();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceController.setInterFaceFieldService(interFaceFieldService);
		interFaceController.setInterFaceProService(interFaceProService);
		interFaceController.setInterFaceService(interFaceService);
		interFaceController.setInterFaceSysService(interFaceSysService);
		interFaceController.setUserInfoService(userInfoService);
	}

	@Test
	@PrepareForTest(SpringContextHolder.class)
	public void getInterFaceById() {
		PowerMockito.mockStatic(SpringContextHolder.class);
		InterFaceByIdResponseDto dto = new InterFaceByIdResponseDto();
		InterFaceSysTem sys = new InterFaceSysTem();
		when(SpringContextHolder.getBean(InterFaceSysTem.class)).thenReturn(sys);
		when(SpringContextHolder.getBean(InterFacePro.class)).thenReturn(new InterFacePro());
		when(SpringContextHolder.getBean(UserInfo.class)).thenReturn(new UserInfo());
		when(SpringContextHolder.getBean(InterFaceField.class)).thenReturn(new InterFaceField());
		when(interFaceService.getInterFaceById((any(InterFaceInfo.class)))).thenReturn(null).thenReturn(new InterFaceInfo());
		when(interFaceSysService.getSysInfoById((any(InterFaceSysTem.class)))).thenReturn(null).thenReturn(sys);
		when(interFaceProService.getProInfoById((any(InterFacePro.class)))).thenReturn(null)
				.thenReturn(new InterFacePro());
		when(userInfoService.getUser((any(Integer.class)))).thenReturn(null).thenReturn(new UserInfo());
		when(interFaceFieldService.getFieldByIfId((any(InterFaceField.class)))).thenReturn(null)
				.thenReturn(new ArrayList());

		dto = interFaceController.getInterFaceById(rdto);
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		dto = interFaceController.getInterFaceById(rdto);
		// 获取成功
		dto = interFaceController.getInterFaceById(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
	}

	@Test
	@PrepareForTest({ PageHelper.class, SystemPropertyConfigure.class })
	public void queryInterFaceByPage() {
		InterFaceFuzzyQueryRequestDto qdto = new InterFaceFuzzyQueryRequestDto();
		MyPageInfo pageInfo = new MyPageInfo();
		pageInfo.setPageNum(1);
		pageInfo.setPageSize(20);
		qdto.setPageInfo(pageInfo);
		qdto.setInterFaceInfo(new InterFaceInfo());
		PowerMockito.mockStatic(PageHelper.class);
		PowerMockito.mockStatic(SystemPropertyConfigure.class);
		InterFacePageResponseDto dto = new InterFacePageResponseDto();
		when(SystemPropertyConfigure.getProperty(any(String.class), any(String.class))).thenReturn("20");
		when(PageHelper.startPage(any(Integer.class), any(Integer.class))).thenReturn(new Page());
		when(interFaceService.queryInterFaceByPage((any(InterFaceInfo.class)))).thenReturn(null)
				.thenReturn(new ArrayList());
		dto = interFaceController.queryInterFaceByPage(qdto);
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		// 获取成功
		dto = interFaceController.queryInterFaceByPage(qdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getPageInfo());
	}
	
	
	@Test
	public void addInterface() {
		InterFaceRequestDto qdto = new InterFaceRequestDto();
		qdto.setInterFaceInfo(new InterFaceInfo());
		//PowerMockito.mockStatic(SystemPropertyConfigure.class);
		InterFaceResponseDto dto = new InterFaceResponseDto();
		when(interFaceService.addInterFace((any(InterFaceInfo.class)))).thenReturn(false)
				.thenReturn(true);
		session.setAttribute(SystemPropertyConfigure.getLoginKey(),new UserInfo());
		dto = interFaceController.addInterface(qdto,session);
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		// 获取成功
		dto = interFaceController.addInterface(qdto,session);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getInterFaceInfo());
	}
	
	@Test
	public void deleteInterFace() {
		InterFaceRequestDto qdto = new InterFaceRequestDto();
		qdto.setInterFaceInfo(new InterFaceInfo());
		InterFaceResponseDto dto = new InterFaceResponseDto();
		when(interFaceService.deleteInterFaceById((any(InterFaceInfo.class)))).thenReturn(false)
				.thenReturn(true);
		dto = interFaceController.deleteInterFace(qdto);
		// 删除失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		// 删除成功
		dto = interFaceController.deleteInterFace(qdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
	}
	@Test
	public void updateInterFace() {
		InterFaceRequestDto qdto = new InterFaceRequestDto();
		qdto.setInterFaceInfo(new InterFaceInfo());
		InterFaceResponseDto dto = new InterFaceResponseDto();
		when(interFaceService.updateInterFace((any(InterFaceInfo.class)))).thenReturn(false)
				.thenReturn(true);
		dto = interFaceController.updateInterFace(qdto);
		// 修改失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		// 修改成功
		dto = interFaceController.updateInterFace(qdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
	}
	
	
}
