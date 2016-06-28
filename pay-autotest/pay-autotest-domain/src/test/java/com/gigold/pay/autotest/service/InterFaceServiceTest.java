/**
 * Title: InterFaceServiceTest.java<br/>
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

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.dao.InterFaceDao;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Title: InterFaceServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月5日上午11:47:52
 *
 */

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
	public void testGetInterFaceById() {
		when(interFaceDao.getInterFaceById(1)).thenReturn(null).thenReturn(new InterFaceInfo());
		// 新增失败
		InterFaceInfo info = interFaceService.getInterFaceById(1);
		Assert.assertNull(info);
		// 新增成功
		info = interFaceService.getInterFaceById(1);
		Assert.assertNotNull(info);

	}
	
	@Test
	@PrepareForTest({ PageHelper.class ,SystemPropertyConfigure.class})
	public void testGetAllIfSys() {
		PowerMockito.mockStatic(RandomStringUtils.class);
		PowerMockito.mockStatic(SystemPropertyConfigure.class);
		InterFaceInfo interFaceInfo=new InterFaceInfo();
		when(SystemPropertyConfigure.getProperty("sys.pageSize")).thenReturn("20");
		when(interFaceDao.getAllIfSys(any(InterFaceInfo.class))).thenReturn(null).thenReturn(new ArrayList());
		// 获取所有的接口信息失败
		 List<InterFaceInfo>list = interFaceService.getAllIfSys(interFaceInfo);
		Assert.assertNull(list);
		// 新增成功
		list = interFaceService.getAllIfSys(interFaceInfo);
		Assert.assertNotNull(list);

	}
	@Test
	@PrepareForTest({ PageHelper.class ,SystemPropertyConfigure.class})
	public void testGetAllIfSys1() {
		PowerMockito.mockStatic(RandomStringUtils.class);
		PowerMockito.mockStatic(SystemPropertyConfigure.class);
		
		when(SystemPropertyConfigure.getProperty("sys.pageSize")).thenReturn("20");
		when(interFaceDao.getAllIfSysForTest()).thenReturn(null).thenReturn(new ArrayList());
		// 获取所有的接口信息失败
		 PageInfo<InterFaceInfo>pageInfo = interFaceService.getAllIfSys(1);
		Assert.assertNull(pageInfo);
		// 新增成功
		pageInfo = interFaceService.getAllIfSys(1);
		Assert.assertNotNull(pageInfo);

	}

}
