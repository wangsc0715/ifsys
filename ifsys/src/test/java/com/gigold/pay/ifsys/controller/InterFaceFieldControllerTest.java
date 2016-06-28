/**
 * Title: InterFaceFieldControllerTest.java<br/>
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
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpSession;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.service.InterFaceFieldService;
import com.gigold.pay.ifsys.service.RetrunCodeService;

/**
 * Title: InterFaceFieldControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月18日上午10:11:57
 *
 */
@RunWith(PowerMockRunner.class)
public class InterFaceFieldControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceFieldService interFaceFeildService;
	@Mock
	RetrunCodeService retrunCodeService;
	@Mock
	InterFaceFieldReqDto rdto;
	private HttpSession session = new MockHttpSession();
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceFieldController interFaceFieldController = new InterFaceFieldController();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceFieldController.setInterFaceFeildService(interFaceFeildService);
		interFaceFieldController.setRetrunCodeService(retrunCodeService);
	}

	@Test
	public void getReqestInfoByIfId() {
		InterFaceFieldResJsonDto dto = new InterFaceFieldResJsonDto();
		when(interFaceFeildService.getFirstReqFieldByIfId((any(InterFaceField.class)))).thenReturn(null)
				.thenReturn(new ArrayList());
		when(rdto.getInterFaceField()).thenReturn(new InterFaceField());
		dto = interFaceFieldController.getReqestInfoByIfId(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
	}

	@Test
	public void addInterFaceField() {
		InterFaceFieldResAddDto dto = new InterFaceFieldResAddDto();
		when(interFaceFeildService.addInterFaceField((any(InterFaceField.class)))).thenReturn(false).thenReturn(true);
		when(rdto.getInterFaceField()).thenReturn(new InterFaceField());
		dto = interFaceFieldController.addInterFaceField(rdto);
		// 添加失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		Assert.assertNull(dto.getInterFaceField());
		// 添加成功
		dto = interFaceFieldController.addInterFaceField(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getInterFaceField());
	}
	
	@Test
	public void getFieldByIfId() {
		InterFaceFieldResListDto dto = new InterFaceFieldResListDto();
		when(interFaceFeildService.getFieldByIfId((any(InterFaceField.class)))).thenReturn(null).thenReturn(new ArrayList());
		when(rdto.getInterFaceField()).thenReturn(new InterFaceField());
		dto = interFaceFieldController.getFieldByIfId(rdto);
		// 获取失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		Assert.assertNull(dto.getList());
		// 获取成功
		dto = interFaceFieldController.getFieldByIfId(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
		Assert.assertNotNull(dto.getList());
	}
	
	@Test
	public void deleteFieldByLevel() {
		ResponseDto dto = new ResponseDto();
		when(interFaceFeildService.deleteFieldByLevel((any(InterFaceField.class)))).thenReturn(false).thenReturn(true);
		when(rdto.getInterFaceField()).thenReturn(new InterFaceField());
		dto = interFaceFieldController.deleteFieldByLevel(rdto);
		// 删除失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		// 删除成功
		dto = interFaceFieldController.deleteFieldByLevel(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
	}
	
	@Test
	public void updateInterFaceField() {
		ResponseDto dto = new ResponseDto();
		when(interFaceFeildService.updateInterFaceField((any(InterFaceField.class)))).thenReturn(false).thenReturn(true);
		when(rdto.getInterFaceField()).thenReturn(new InterFaceField());
		dto = interFaceFieldController.updateInterFaceField(rdto);
		// 修改失败
		Assert.assertEquals(CodeItem.IF_FAILURE, dto.getRspCd());
		// 修改成功
		dto = interFaceFieldController.updateInterFaceField(rdto);
		Assert.assertEquals(SysCode.SUCCESS, dto.getRspCd());
	}
	
}
