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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.dao.UserInfoDao;

/**
 * Title: UserInfoServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月17日上午11:18:53
 *
 */
@RunWith(PowerMockRunner.class)
public class UserInfoServiceTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	UserInfoDao userInfoDao;
	/** ====================== 测试对象定义 ========================== **/
	private UserInfoService userInfoService = new UserInfoService();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		userInfoService.setUserInfoDao(userInfoDao);
	}
	
	@Test
	public void getUser(){
		when(userInfoDao.getUser(1)).thenReturn(null).thenReturn(new UserInfo());
		UserInfo userInfo=userInfoService.getUser(1);
		//查询失败  包括抛异常的情况
		Assert.assertNull(userInfo);
		userInfo=userInfoService.getUser(1);
		//成功
		Assert.assertNotNull(userInfo);
	}
	@Test
	public void login(){
		UserInfo u=new UserInfo();
		when(userInfoDao.login(any(UserInfo.class))).thenReturn(null).thenReturn(u);
		UserInfo  userInfo=userInfoService.login(u);
		//登录失败  包括抛异常的情况
		Assert.assertNull(userInfo);
		userInfo=userInfoService.login(u);
		//成功
		Assert.assertNotNull(userInfo);
	}
	@Test
	public void addUser(){
		UserInfo u=new UserInfo();
		when(userInfoDao.addUser(any(UserInfo.class))).thenReturn(0).thenReturn(1);
		boolean flag=userInfoService.addUser(u);
		//添加失败  包括抛异常的情况
		Assert.assertFalse(flag);
		flag=userInfoService.addUser(u);
		//成功
		Assert.assertTrue(flag);
	}
}
