/**
 * Title: InterFaceInvokerServiceTest.java<br/>
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

import com.gigold.pay.ifsys.bo.InterFaceInvoker;
import com.gigold.pay.ifsys.dao.InterFaceInvokerDao;

/**
 * Title: InterFaceInvokerServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月17日下午2:53:27
 *
 */
@RunWith(PowerMockRunner.class)
public class InterFaceInvokerServiceTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceInvokerDao interFaceInvokerDao;
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceInvokerService interFaceInvokerService = new InterFaceInvokerService();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceInvokerService.setInterFaceInvokerDao(interFaceInvokerDao);
	}

	@Test
	public void addInterFaceInvoker() {
		InterFaceInvoker interFaceInvoker = new InterFaceInvoker();
		when(interFaceInvokerDao.addInterFaceInvoker(any(InterFaceInvoker.class))).thenReturn(-1).thenReturn(1);
		InterFaceInvoker invoker = interFaceInvokerService.addInterFaceInvoker(interFaceInvoker);
		// 新增失败 包括抛异常的情况
		Assert.assertNull(invoker);
		invoker = interFaceInvokerService.addInterFaceInvoker(interFaceInvoker);
		// 新增成功
		Assert.assertNotNull(invoker);
	}
	@Test
	public void getInvokerList(){
		InterFaceInvoker interFace=new InterFaceInvoker();
		when(interFaceInvokerDao.getInvokerList(any(InterFaceInvoker.class))).thenReturn(null).thenReturn(new ArrayList());
		List<InterFaceInvoker> list=interFaceInvokerService.getInvokerList(interFace);
		//获取失败  包括抛异常的情况
		Assert.assertNull(list);
		list=interFaceInvokerService.getInvokerList(interFace);
		//成功
		Assert.assertNotNull(list);
	}
}
