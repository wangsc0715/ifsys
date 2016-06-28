/**
 * Title: InterFaceProControllerTest.java<br/>
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
import com.gigold.pay.ifsys.bo.InterFacePro;
import com.gigold.pay.ifsys.service.InterFaceProService;

/**
 * Title: InterFaceProControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月18日上午9:25:54
 *
 */

@RunWith(PowerMockRunner.class)
public class InterFaceProControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceProService interFaceProService;
	@Mock
	InterFaceProRequestDto rdto;
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceProController interFaceProController = new InterFaceProController();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceProController.setInterFaceProService(interFaceProService);
	}

	@Test
	public void getProInfoBySysId() {
		InterFaceProResponseDto dto = new InterFaceProResponseDto();
		when(interFaceProService.getProInfoBySysId(any(InterFacePro.class))).thenReturn(null)
				.thenReturn(new ArrayList());
		dto = interFaceProController.getProInfoBySysId(rdto);
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		Assert.assertNull(dto.getProList());
		// 获取成功
		dto = interFaceProController.getProInfoBySysId(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getProList());
	}

}
