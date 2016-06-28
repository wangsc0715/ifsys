/**
 * Title: InterFaceSysServiceTest.java<br/>
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

import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.dao.InterFaceSystemDao;

/**
 * Title: InterFaceSysServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月17日下午1:39:52
 *
 */
@RunWith(PowerMockRunner.class)
public class InterFaceSysServiceTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceSystemDao interFaceSystemDao;
	/** ====================== 测试对象定义 ========================== **/
	private InterFaceSysService interFaceSysService = new InterFaceSysService();
	
	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceSysService.setInterFaceSystemDao(interFaceSystemDao);
	}
	
	@Test
	public void getAllSysInfo(){
		when(interFaceSystemDao.getAllSysInfo()).thenReturn(null).thenReturn(new ArrayList());
		List list=interFaceSysService.getAllSysInfo();
		//获取失败 
		Assert.assertNull(list);
		list=interFaceSysService.getAllSysInfo();
		//获取失败 
		Assert.assertNotNull(list);
	}
	
	@Test
	public void getSysInfoById(){
		InterFaceSysTem interFaceSysTem=new InterFaceSysTem();
		
		when(interFaceSystemDao.getSysInfoById(any(InterFaceSysTem.class))).thenReturn(null).thenReturn(interFaceSysTem);
		InterFaceSysTem sys=interFaceSysService.getSysInfoById(interFaceSysTem);
		//获取失败 
		Assert.assertNull(sys);
		sys=interFaceSysService.getSysInfoById(interFaceSysTem);
		//获取失败 
		Assert.assertNotNull(sys);
	}
	
}
