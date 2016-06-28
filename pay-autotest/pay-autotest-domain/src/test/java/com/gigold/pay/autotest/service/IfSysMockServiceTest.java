/**
 * Title: IfSysMockService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

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

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.dao.IfSysMockDAO;

/**
 * Title: IfSysMockService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:22:09
 *
 */

@RunWith(PowerMockRunner.class)
public class IfSysMockServiceTest {

	/** ====================== mock对象定义 ========================== **/
	@Mock
	IfSysMockDAO fSysMockDAO;

	/** ====================== 测试对象定义 ========================== **/
	private IfSysMockService fSysMockService = new IfSysMockService();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		fSysMockService.setIfSysMockDao(fSysMockDAO);
	}

	@Test
	public void testAddIfSysMock() {
		when(fSysMockDAO.addIfSysMock(any(IfSysMock.class))).thenReturn(0).thenReturn(1);
		IfSysMock ifSysMock = new IfSysMock();
		// 新增失败
		boolean flag = fSysMockService.addIfSysMock(ifSysMock);
		Assert.assertFalse(flag);
		// 新增成功
		boolean flag1 = fSysMockService.addIfSysMock(ifSysMock);
		Assert.assertTrue(flag1);

	}

	@Test
	public void testGetMockInfoByIfId() {
		when(fSysMockDAO.getMockInfoByIfId(1)).thenReturn(null).thenReturn(new ArrayList());
		// 根据接口ID获取所有测试信息失败
		List<IfSysMock> list = fSysMockService.getMockInfoByIfId(1);
		Assert.assertNull(list);
		// 根据接口ID获取所有测试信息成功
		list = fSysMockService.getMockInfoByIfId(1);
		Assert.assertNotNull(list);

	}

	@Test
	public void testUpdateIfSysMock() {

		IfSysMock ifSysMock = new IfSysMock();
		ifSysMock.setId(0);
		when(fSysMockDAO.addIfSysMock(any(IfSysMock.class))).thenReturn(0).thenReturn(1);
		// 修改失败
		boolean flag = fSysMockService.updateIfSysMock(ifSysMock);
		Assert.assertFalse(flag);
		// 修改成功
		boolean flag1 = fSysMockService.updateIfSysMock(ifSysMock);
		Assert.assertTrue(flag1);
		// 修改失败
		ifSysMock.setId(1);
		when(fSysMockDAO.getMockInfoById(any(IfSysMock.class))).thenReturn(ifSysMock);
		when(fSysMockDAO.updateIfSysMock(any(IfSysMock.class))).thenReturn(0).thenReturn(1);
		boolean flag2 = fSysMockService.updateIfSysMock(ifSysMock);
		Assert.assertFalse(flag2);
		// 修改成功
		boolean flag3 = fSysMockService.updateIfSysMock(ifSysMock);
		Assert.assertTrue(flag3);
		
		when(fSysMockDAO.getMockInfoById(any(IfSysMock.class))).thenReturn(null);
		when(fSysMockDAO.addIfSysMock(any(IfSysMock.class))).thenReturn(0).thenReturn(1);
		boolean flag4 = fSysMockService.updateIfSysMock(ifSysMock);
		Assert.assertFalse(flag4);
		// 修改成功
		boolean flag5 = fSysMockService.updateIfSysMock(ifSysMock);
		Assert.assertTrue(flag5);
		
	}

	@Test
	public void testDeleteIfSysMockById() {

		when(fSysMockDAO.deleteIfSysMockById(1)).thenReturn(0).thenReturn(1);
		// 删除失败
		boolean flag = fSysMockService.deleteIfSysMockById(1);
		Assert.assertFalse(flag);
		// 删除成功
		boolean flag1 = fSysMockService.deleteIfSysMockById(1);
		Assert.assertTrue(flag1);
	}
	@Test
	public void testDeleteIfSysMockByIfId() {

		when(fSysMockDAO.deleteIfSysMockByIfId(1)).thenReturn(0).thenReturn(1);
		// 删除失败
		boolean flag = fSysMockService.deleteIfSysMockByIfId(1);
		Assert.assertFalse(flag);
		// 删除成功
		boolean flag1 = fSysMockService.deleteIfSysMockByIfId(1);
		Assert.assertTrue(flag1);
	}

	/* @陈宽 2015-12-08*/
	@Test
	public void testFilterMocksByStatus(){
		// 待补充
		Assert.assertTrue(true);
	}
	
}
