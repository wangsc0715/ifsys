/**
 * Title: InterFaceFieldServiceTest.java<br/>
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

import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.dao.InterFaceFieldDao;

/**
 * Title: InterFaceFieldServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月17日下午3:11:22
 *
 */
@RunWith(PowerMockRunner.class)
public class InterFaceFieldServiceTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceFieldDao interFaceFieldDao;
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceFieldService interFaceFieldService = new InterFaceFieldService();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceFieldService.setInterFaceFieldDao(interFaceFieldDao);
	}

	@Test
	public void getFirstReqFieldByIfId() {
		InterFaceField interFaceField = new InterFaceField();
		when(interFaceFieldDao.getFirstReqFieldByIfId(any(InterFaceField.class))).thenReturn(null)
				.thenReturn(new ArrayList());
		List<InterFaceField> list = interFaceFieldService.getFirstReqFieldByIfId(interFaceField);
		// 获取失败 包括抛异常的情况
		Assert.assertNull(list);
		list = interFaceFieldService.getFieldByIfId(interFaceField);
		// 成功
		Assert.assertNotNull(list);
	}

	@Test
	public void getFieldByparentId() {
		InterFaceField interFaceField = new InterFaceField();
		when(interFaceFieldDao.getFieldByparentId(any(InterFaceField.class))).thenReturn(null)
				.thenReturn(new ArrayList());
		List<InterFaceField> list = interFaceFieldService.getFieldByparentId(interFaceField);
		// 获取失败 包括抛异常的情况
		Assert.assertNull(list);
		list = interFaceFieldService.getFieldByparentId(interFaceField);
		// 成功
		Assert.assertNotNull(list);
	}

	@Test
	public void getFieldByIfId() {
		InterFaceField interFaceField = new InterFaceField();
		when(interFaceFieldDao.getFieldByIfId(any(InterFaceField.class))).thenReturn(null).thenReturn(new ArrayList());
		List<InterFaceField> list = interFaceFieldService.getFieldByIfId(interFaceField);
		// 获取失败 包括抛异常的情况
		Assert.assertNull(list);
		list = interFaceFieldService.getFieldByIfId(interFaceField);
		// 成功
		Assert.assertNotNull(list);
	}

	@Test
	public void addInterFaceField() {
		InterFaceField interFaceField = new InterFaceField();
		when(interFaceFieldDao.addInterFaceField(any(InterFaceField.class))).thenReturn(-1).thenReturn(1);
		// 添加失败
		boolean flag = interFaceFieldService.addInterFaceField(interFaceField);
		Assert.assertFalse(flag);
		// 添加成功
		flag = interFaceFieldService.addInterFaceField(interFaceField);
		Assert.assertTrue(flag);
	}
	
	@Test
	public void deleteFieldByLevel() {
		InterFaceField interFaceField = new InterFaceField();
		when(interFaceFieldDao.deleteFieldByLevel(any(InterFaceField.class))).thenReturn(-1).thenReturn(1);
		// 添加失败
		boolean flag = interFaceFieldService.deleteFieldByLevel(interFaceField);
		Assert.assertFalse(flag);
		// 添加成功
		flag = interFaceFieldService.deleteFieldByLevel(interFaceField);
		Assert.assertTrue(flag);
	}
	@Test
	public void updateInterFaceField() {
		InterFaceField interFaceField = new InterFaceField();
		when(interFaceFieldDao.updateInterFaceField(any(InterFaceField.class))).thenReturn(-1).thenReturn(1);
		// 添加失败
		boolean flag = interFaceFieldService.updateInterFaceField(interFaceField);
		Assert.assertFalse(flag);
		// 添加成功
		flag = interFaceFieldService.updateInterFaceField(interFaceField);
		Assert.assertTrue(flag);
	}

}
