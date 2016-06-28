/**
 * Title: InterFaceSysControllerTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.service.InterFaceSysService;

/**
 * Title: InterFaceSysControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月18日上午9:16:42
 *
 */

@RunWith(PowerMockRunner.class)
public class InterFaceSysControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceSysService interFaceSysService;
	@Mock
	InterFaceSysRequestDto rdto;
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceSysController interFaceSysController = new InterFaceSysController();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceSysController.setInterFaceSysService(interFaceSysService);
	}

	@Test
	public void getSysInfoById() {
		InterFaceSysResponseDto dto = new InterFaceSysResponseDto();
		when(interFaceSysService.getSysInfoById((any(InterFaceSysTem.class)))).thenReturn(null)
				.thenReturn(new InterFaceSysTem());
		dto = interFaceSysController.getSysInfoById(rdto);
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		Assert.assertNull(dto.getSysList());
		// 获取成功
		dto = interFaceSysController.getSysInfoById(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getSysList());
	}
	
	@Test
	public void getAllSysInfo() {
		InterFaceSysResponseDto dto = new InterFaceSysResponseDto();
		when(interFaceSysService.getAllSysInfo()).thenReturn(null)
				.thenReturn(new ArrayList());
		dto = interFaceSysController.getAllSysInfo();
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		Assert.assertNull(dto.getSysList());
		//  获取成功
		dto = interFaceSysController.getAllSysInfo();
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getSysList());
	}
}
