/**
 * Title: IfSysMockControllerTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.InterFaceFieldService;
import com.gigold.pay.autotest.service.InterFaceService;
import com.gigold.pay.autotest.service.RetrunCodeService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.ResponseDto;
import com.github.pagehelper.PageHelper;

import junit.framework.Assert;

/**
 * Title: IfSysMockControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日下午1:41:09
 *
 */
@RunWith(PowerMockRunner.class)
public class IfSysMockControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	IfSysMockService ifSysMockService;
	@Mock
	InterFaceFieldService interFaceFieldService;
	@Mock
	InterFaceService interFaceService;
	@Mock
	RetrunCodeService retrunCodeService;
	@Mock
	IfSysMockAddReqDto dto;
	@Mock
	IfSysMockDelReqDto deldto;
	/** ====================== 测试对象定义 ========================== **/
	private IfSysMockController ifSysMockController = new IfSysMockController();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		ifSysMockController.setIfSysMockService(ifSysMockService);
		ifSysMockController.setInterFaceFieldService(interFaceFieldService);
		ifSysMockController.setInterFaceService(interFaceService);
		ifSysMockController.setRetrunCodeService(retrunCodeService);
	}

	@Test
	public void testAddIfSysMock() {
		when(dto.validation()).thenReturn(CodeItem.RETURN_CODE_IS_NULL).thenReturn(CodeItem.REQ_JSON_IS_NULL);
		// 验证失败 返回码不能为空
		ResponseDto rdto = ifSysMockController.addIfSysMock(dto);
		Assert.assertEquals(CodeItem.RETURN_CODE_IS_NULL, rdto.getRspCd());
		// 验证失败 请求报文不能为空
		rdto = ifSysMockController.addIfSysMock(dto);
		Assert.assertEquals(CodeItem.REQ_JSON_IS_NULL, rdto.getRspCd());

		when(dto.validation()).thenReturn(SysCode.SUCCESS);
		when(ifSysMockService.addIfSysMock(any(IfSysMock.class))).thenReturn(false).thenReturn(true);
		// 添加失败
		rdto = ifSysMockController.addIfSysMock(dto);
		Assert.assertEquals(CodeItem.FAILURE, rdto.getRspCd());
		// 添加成功
		rdto = ifSysMockController.addIfSysMock(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}

	@Test
	public void testDeleteIfSysMockById() {
		// IfSysMockDelReqDto dto=new IfSysMockDelReqDto();
		when(ifSysMockService.deleteIfSysMockById(any(int.class))).thenReturn(false).thenReturn(true);
		// 删除失败
		ResponseDto rdto = ifSysMockController.deleteIfSysMockById(deldto);
		Assert.assertEquals(CodeItem.FAILURE, rdto.getRspCd());
		rdto = ifSysMockController.deleteIfSysMockById(deldto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());

	}

	@Test
	public void testDeleteIfSysMockByIfId() {
		when(ifSysMockService.deleteIfSysMockByIfId(any(int.class))).thenReturn(false);
		// 删除失败
		ResponseDto rdto = ifSysMockController.deleteIfSysMockByIfId(deldto);
		Assert.assertEquals(CodeItem.FAILURE, rdto.getRspCd());
		// 删除成功
		when(ifSysMockService.deleteIfSysMockByIfId(any(int.class))).thenReturn(true);
		ResponseDto rdto1 = ifSysMockController.deleteIfSysMockByIfId(deldto);
		Assert.assertEquals(SysCode.SUCCESS, rdto1.getRspCd());
	}

	@Test
	public void testUpdateIfSysMock() {
		when(dto.validation()).thenReturn(CodeItem.RETURN_CODE_IS_NULL).thenReturn(CodeItem.REQ_JSON_IS_NULL);
		// 验证失败 返回码不能为空
		ResponseDto rdto = ifSysMockController.updateIfSysMock(dto);
		Assert.assertEquals(CodeItem.RETURN_CODE_IS_NULL, rdto.getRspCd());
		// 验证失败 请求报文不能为空
		rdto = ifSysMockController.updateIfSysMock(dto);
		Assert.assertEquals(CodeItem.REQ_JSON_IS_NULL, rdto.getRspCd());

		when(dto.validation()).thenReturn(SysCode.SUCCESS);
		when(ifSysMockService.updateIfSysMock(any(IfSysMock.class))).thenReturn(false).thenReturn(true);
		// 添加失败
		rdto = ifSysMockController.updateIfSysMock(dto);
		Assert.assertEquals(CodeItem.FAILURE, rdto.getRspCd());
		// 添加成功
		rdto = ifSysMockController.updateIfSysMock(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}

	@Test

	@PrepareForTest({ PageHelper.class ,SystemPropertyConfigure.class})
	public void testGetAllIfSys() {
		PowerMockito.mockStatic(RandomStringUtils.class);
		PowerMockito.mockStatic(SystemPropertyConfigure.class);
		
		IfSysMockPageDto dto = new IfSysMockPageDto();
		when(SystemPropertyConfigure.getProperty("sys.pageSize")).thenReturn("20");
		when(interFaceService.getAllIfSys(any(InterFaceInfo.class))).thenReturn(null).thenReturn(new ArrayList());
		// /查询失败
		ResponseDto rdto = ifSysMockController.getAllIfSys(dto);
		Assert.assertEquals(CodeItem.FAILURE, rdto.getRspCd());
		// 查询成功
		rdto = ifSysMockController.getAllIfSys(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}

	@Test
	@PrepareForTest({SpringContextHolder.class})
	public void testGetIfSysMockByIfId() {
		PowerMockito.mockStatic(SpringContextHolder.class);
		when(SpringContextHolder.getBean(InterFaceField.class)).thenReturn(new InterFaceField()).thenReturn(new IfSysMock());
		when(SpringContextHolder.getBean(IfSysMock.class)).thenReturn(new IfSysMock());
		
		IfSysMockAddReqDto dto = new IfSysMockAddReqDto();
		when(interFaceService.getInterFaceById(any(int.class))).thenReturn(null).thenReturn(new InterFaceInfo());
		when(retrunCodeService.getReturnCodeByIfId(1)).thenReturn(null);
		// /查询失败
		ResponseDto rdto = ifSysMockController.getIfSysMockByIfId(dto);
		Assert.assertEquals(CodeItem.FAILURE, rdto.getRspCd());
		// 查询成功
		rdto = ifSysMockController.getIfSysMockByIfId(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());

	}

}
