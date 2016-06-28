/**
 * Title: ReturnCodeControllerTest.java<br/>
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
import com.gigold.pay.ifsys.bo.ReturnCode;
import com.gigold.pay.ifsys.service.RetrunCodeService;

/**
 * Title: ReturnCodeControllerTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月17日下午5:17:08
 *
 */

@RunWith(PowerMockRunner.class)
public class ReturnCodeControllerTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	private RetrunCodeService retrunCodeService;
	@Mock
	ReturnCodeReqDto dto;
	private HttpSession session = new MockHttpSession();;
	/** ====================== 测试对象定义 ========================== **/
	private ReturnCodeController returnCodeController = new ReturnCodeController();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		returnCodeController.setRetrunCodeService(retrunCodeService);
	}

	@Test
	public void addRetrunCode() {
		RetrunCodeAddRspDto rdto = new RetrunCodeAddRspDto();
		when(dto.validation()).thenReturn(CodeItem.IF_ID_FAILURE).thenReturn(CodeItem.RSP_CODE_FAILURE)
				.thenReturn(CodeItem.RSP_CODE_DESC_FAILURE).thenReturn(SysCode.SUCCESS);
		rdto = returnCodeController.addRetrunCode(dto);
		// 所属接口ID不能为空
		Assert.assertEquals(CodeItem.IF_ID_FAILURE, rdto.getRspCd());
		//返回码不能为空
		rdto = returnCodeController.addRetrunCode(dto);
		Assert.assertEquals(CodeItem.RSP_CODE_FAILURE, rdto.getRspCd());
		//返回码描述不能为空
		rdto = returnCodeController.addRetrunCode(dto);
		Assert.assertEquals(CodeItem.RSP_CODE_DESC_FAILURE, rdto.getRspCd());
		
		when(retrunCodeService.addRetrunCode((any(ReturnCode.class)))).thenReturn(null).thenReturn(new ReturnCode());
		rdto = returnCodeController.addRetrunCode(dto);
		//新增失败
		Assert.assertEquals(CodeItem.IF_FAILURE, rdto.getRspCd());
		//成功
		rdto = returnCodeController.addRetrunCode(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}
	
	
	@Test
	public void deleteReturnCodeByIfId(){
		ResponseDto rdto = new ResponseDto();
		when(dto.getIfId()).thenReturn(0).thenReturn(33);
		rdto = returnCodeController.deleteReturnCodeByIfId(dto);
		//所属接口ID不能为空
		Assert.assertEquals(CodeItem.IF_ID_FAILURE, rdto.getRspCd());
		
		when(retrunCodeService.deleteReturnCodeByIfId((any(Integer.class)))).thenReturn(false).thenReturn(true);
		
		rdto = returnCodeController.deleteReturnCodeByIfId(dto);
		//失败
		Assert.assertEquals(CodeItem.IF_FAILURE, rdto.getRspCd());
		rdto = returnCodeController.deleteReturnCodeByIfId(dto);
		//成功
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}
	@Test
	public void deleteReturnCodeById(){
		ResponseDto rdto = new ResponseDto();
		when(dto.getId()).thenReturn(0);
		//ID不能为空
		rdto = returnCodeController.deleteReturnCodeById(dto);
		Assert.assertEquals(CodeItem.ID_FAILURE, rdto.getRspCd());
		when(dto.getId()).thenReturn(33);
		when(retrunCodeService.deleteReturnCodeById((any(Integer.class)))).thenReturn(false).thenReturn(true);
		
		//失败
		rdto = returnCodeController.deleteReturnCodeById(dto);
		Assert.assertEquals(CodeItem.IF_FAILURE, rdto.getRspCd());
		
		//成功
		rdto = returnCodeController.deleteReturnCodeById(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}
	
	
	@Test
	public void updateReturnCodeById() {
		ResponseDto rdto = new ResponseDto();
		when(dto.validation()).thenReturn(CodeItem.IF_ID_FAILURE).thenReturn(CodeItem.RSP_CODE_FAILURE)
				.thenReturn(CodeItem.RSP_CODE_DESC_FAILURE).thenReturn(SysCode.SUCCESS);
		rdto = returnCodeController.updateReturnCodeById(dto);
		// 所属接口ID不能为空
		Assert.assertEquals(CodeItem.IF_ID_FAILURE, rdto.getRspCd());
		//返回码不能为空
		rdto = returnCodeController.updateReturnCodeById(dto);
		Assert.assertEquals(CodeItem.RSP_CODE_FAILURE, rdto.getRspCd());
		//返回码描述不能为空
		rdto = returnCodeController.updateReturnCodeById(dto);
		Assert.assertEquals(CodeItem.RSP_CODE_DESC_FAILURE, rdto.getRspCd());
		
		when(retrunCodeService.updateReturnCodeById((any(ReturnCode.class)))).thenReturn(false).thenReturn(true);
		rdto = returnCodeController.updateReturnCodeById(dto);
		//新增失败
		Assert.assertEquals(CodeItem.IF_FAILURE, rdto.getRspCd());
		//成功
		rdto = returnCodeController.updateReturnCodeById(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}
	
	@Test
	public void getReturnCodeByIfId(){
		RetrunCodeRspDto rdto = new RetrunCodeRspDto();
		when(dto.getIfId()).thenReturn(0);
		//ID不能为空
		rdto = returnCodeController.getReturnCodeByIfId(dto);
		Assert.assertEquals(CodeItem.IF_ID_FAILURE, rdto.getRspCd());
		when(dto.getIfId()).thenReturn(33);
		when(retrunCodeService.getReturnCodeByIfId((any(Integer.class)))).thenReturn(null).thenReturn(new ArrayList());
		
		//失败
		rdto = returnCodeController.getReturnCodeByIfId(dto);
		Assert.assertEquals(CodeItem.IF_FAILURE, rdto.getRspCd());
		
		//成功
		rdto = returnCodeController.getReturnCodeByIfId(dto);
		Assert.assertEquals(SysCode.SUCCESS, rdto.getRspCd());
	}
	
}
