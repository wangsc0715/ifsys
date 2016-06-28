/**
 * Title: InterFaceServiceTest.java<br/>
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

import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.dao.InterFaceDao;

@RunWith(PowerMockRunner.class)
public class InterFaceServiceTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceDao interFaceDao;
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceService interFaceService = new InterFaceService();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceService.setInterFaceDao(interFaceDao);
	}
	@Test
	public void getInterFaceById(){
		InterFaceInfo interFace=new InterFaceInfo();
		when(interFaceDao.getInterFaceById(any(InterFaceInfo.class))).thenReturn(null).thenReturn(interFace);
		InterFaceInfo interFaceInfo=interFaceService.getInterFaceById(interFace);
		//获取失败  包括抛异常的情况
		Assert.assertNull(interFaceInfo);
		interFaceInfo=interFaceService.getInterFaceById(interFace);
		//成功
		Assert.assertNotNull(interFace);
	}
	@Test
	public void queryInterFaceByPage(){
		InterFaceInfo interFace=new InterFaceInfo();
		when(interFaceDao.queryInterFaceByPage(any(InterFaceInfo.class))).thenReturn(null).thenReturn(new ArrayList());
		List<InterFaceInfo> list=interFaceService.queryInterFaceByPage(interFace);
		//获取失败  包括抛异常的情况
		Assert.assertNull(list);
		list=interFaceService.queryInterFaceByPage(interFace);
		//成功
		Assert.assertNotNull(list);
	}
	@Test
	public void addInterFace(){
		InterFaceInfo interFace=new InterFaceInfo();
		when(interFaceDao.addInterFace(any(InterFaceInfo.class))).thenReturn(-1).thenReturn(1);
		boolean flag=interFaceService.addInterFace(interFace);
		//新增失败  包括抛异常的情况
		Assert.assertFalse(flag);
		flag=interFaceService.addInterFace(interFace);
		//成功
		Assert.assertTrue(flag);
	}
	@Test
	public void deleteInterFaceById(){
		InterFaceInfo interFace=new InterFaceInfo();
		when(interFaceDao.deleteInterFaceById(any(InterFaceInfo.class))).thenReturn(-1).thenReturn(1);
		boolean flag=interFaceService.deleteInterFaceById(interFace);
		//删除失败  包括抛异常的情况
		Assert.assertFalse(flag);
		flag=interFaceService.deleteInterFaceById(interFace);
		//成功
		Assert.assertTrue(flag);
	}
	
	@Test
	public void updateInterFace(){
		InterFaceInfo interFace=new InterFaceInfo();
		when(interFaceDao.updateInterFace(any(InterFaceInfo.class))).thenReturn(-1).thenReturn(1);
		boolean flag=interFaceService.updateInterFace(interFace);
		//删除失败  包括抛异常的情况
		Assert.assertFalse(flag);
		flag=interFaceService.updateInterFace(interFace);
		//成功
		Assert.assertTrue(flag);
	}

}
