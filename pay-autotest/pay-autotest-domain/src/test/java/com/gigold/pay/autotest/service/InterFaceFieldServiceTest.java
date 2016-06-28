/**
 * Title: InterFaceFieldServiceTest.java<br/>
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
import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.dao.InterFaceFieldDao;

/**
 * Title: InterFaceFieldServiceTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月5日上午11:43:09
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
	public void testGetFirstReqFieldByIfId() {
		InterFaceField interFaceField=new InterFaceField();
		when(interFaceFieldDao.getFirstReqFieldByIfId(any(InterFaceField.class))).thenReturn(null).thenReturn(new ArrayList());
		// 获取接口的字段列表信息失败
		List<InterFaceField> list = interFaceFieldService.getFirstReqFieldByIfId(interFaceField);
		Assert.assertNull(list);
		// 获取接口的字段列表信息成功
		list = interFaceFieldService.getFirstReqFieldByIfId(interFaceField);
		Assert.assertNotNull(list);

	}
	
}
