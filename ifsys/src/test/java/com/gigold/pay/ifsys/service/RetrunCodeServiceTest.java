/**
 * Title: UserInfoServiceTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gigold.pay.ifsys.bo.ReturnCode;
import com.gigold.pay.ifsys.dao.ReturnCodeDao;

/**
 * Title: UserInfoServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月17日上午11:18:53
 *
 */
@RunWith(PowerMockRunner.class)
public class RetrunCodeServiceTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	ReturnCodeDao returnCodeDao;
	/** ====================== 测试对象定义 ========================== **/
	private RetrunCodeService retrunCodeService = new RetrunCodeService();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		retrunCodeService.setReturnCodeDao(returnCodeDao);
	}

	@Test
	public void addRetrunCode() {
		ReturnCode rspCode = new ReturnCode();
		when(returnCodeDao.getReturnCodeById(any(Integer.class))).thenReturn(null);
		when(returnCodeDao.addRetrunCode(any(ReturnCode.class))).thenReturn(-1).thenReturn(1);
		ReturnCode rc = retrunCodeService.addRetrunCode(rspCode);
		// 返回码不存在则新增 新增失败
		Assert.assertNull(rc);
		rc = retrunCodeService.addRetrunCode(rspCode);
		// 返回码不存在则新增 新增成功
		Assert.assertNotNull(rc);
        
		when(returnCodeDao.getReturnCodeById(any(Integer.class))).thenReturn(rspCode);
		when(returnCodeDao.updateReturnCodeById(any(ReturnCode.class))).thenReturn(-1).thenReturn(1);
		// 返回码存在则更新 更新失败
		rc = retrunCodeService.addRetrunCode(rspCode);
		Assert.assertNull(rc);
		// 返回码存在则更新 更新成功
		rc = retrunCodeService.addRetrunCode(rspCode);
		Assert.assertNotNull(rc);
	}
	@Test
	public void deleteReturnCodeByIfId() {
		when(returnCodeDao.deleteReturnCodeByIfId(any(Integer.class))).thenReturn(-1).thenReturn(1);
		boolean flag=retrunCodeService.deleteReturnCodeByIfId(1);
		//删除  包括抛异常的情况
		Assert.assertFalse(flag);
		flag=retrunCodeService.deleteReturnCodeByIfId(1);
		//成功
		Assert.assertTrue(flag);
	}
	
	@Test
	public void deleteReturnCodeById() {
		when(returnCodeDao.deleteReturnCodeById(any(Integer.class))).thenReturn(-1).thenReturn(1);
		boolean flag=retrunCodeService.deleteReturnCodeById(1);
		//删除  包括抛异常的情况
		Assert.assertFalse(flag);
		flag=retrunCodeService.deleteReturnCodeById(1);
		//成功
		Assert.assertTrue(flag);
	}
	@Test
	public void updateReturnCodeById() {
		ReturnCode rspCode = new ReturnCode();
		when(returnCodeDao.updateReturnCodeById(any(ReturnCode.class))).thenReturn(-1).thenReturn(1);
		boolean flag=retrunCodeService.updateReturnCodeById(rspCode);
		//修改返回码 失败  包括抛异常的情况
		Assert.assertFalse(flag);
		flag=retrunCodeService.updateReturnCodeById(rspCode);
		//成功
		Assert.assertTrue(flag);
	}
	
	@Test
	public void getReturnCodeByIfId(){
		when(returnCodeDao.getReturnCodeByIfId(any(Integer.class))).thenReturn(null).thenReturn(new ArrayList());
		List list=retrunCodeService.getReturnCodeByIfId(1);
		//查询失败
		Assert.assertNull(list);
		list=retrunCodeService.getReturnCodeByIfId(1);
		//查询成功返回
		Assert.assertNotNull(list);
	}
	
	@Test
	public void getReturnCodeById(){
		ReturnCode rspCode = new ReturnCode();
		when(returnCodeDao.getReturnCodeById(any(Integer.class))).thenReturn(null).thenReturn(rspCode);
		ReturnCode rc =retrunCodeService.getReturnCodeById(1);
		//查询失败
		Assert.assertNull(rc);
		rc=retrunCodeService.getReturnCodeById(1);
		//查询成功返回
		Assert.assertNotNull(rc);
	}
	
}
