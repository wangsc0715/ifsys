/**
 * Title: InterFaceProServiceTest.java<br/>
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
import com.gigold.pay.ifsys.bo.InterFacePro;
import com.gigold.pay.ifsys.dao.InterFaceProDao;

/**
 * Title: InterFaceProServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月17日下午2:39:23
 *
 */

@RunWith(PowerMockRunner.class)
public class InterFaceProServiceTest {
	/** ====================== mock对象定义 ========================== **/
	@Mock
	InterFaceProDao interFaceProDao;

	/** ====================== 测试对象定义 ========================== **/
	private InterFaceProService interFaceProService = new InterFaceProService();

	/**
	 * mock对象初始化
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		// 测试对象中被mock对象赋值
		interFaceProService.setInterFaceProDao(interFaceProDao);
	}
	@Test
	public void getProInfoById(){
		InterFacePro pro=new InterFacePro();
		when(interFaceProDao.getProInfoById(any(InterFacePro.class))).thenReturn(null).thenReturn(pro);
		InterFacePro interFacePro=interFaceProService.getProInfoById(pro);
		//获取失败  包括抛异常的情况
		Assert.assertNull(interFacePro);
		interFacePro=interFaceProService.getProInfoById(pro);
		//成功
		Assert.assertNotNull(interFacePro);
	}
	@Test
	public void getProInfoBySysId(){
		InterFacePro pro=new InterFacePro();
		when(interFaceProDao.getProInfoBySysId(any(InterFacePro.class))).thenReturn(null).thenReturn(new ArrayList());
		List<InterFacePro> list=interFaceProService.getProInfoBySysId(pro);
		//获取失败  包括抛异常的情况
		Assert.assertNull(list);
		list=interFaceProService.getProInfoBySysId(pro);
		//成功
		Assert.assertNotNull(list);
	}
}
