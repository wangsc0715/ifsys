/**
 * Title: IfSysMockService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.IfSysStuff;
import com.gigold.pay.autotest.dao.IfSysMockDAO;
import com.gigold.pay.autotest.dao.IfSysStuffEmailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: IfSysMockService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:22:09
 *
 */
@Service
public class IfSysStuffService {

	@Autowired
	IfSysStuffEmailDAO ifSysStuffEmailDAO;

	/**
	 *
	 * Title: filterMocksByStatus<br/>
	 * Description: 根据id查询接口的关注列表<br/>
	 *
	 * @author chenkuan
	 * @date 2015年12月11日上午11:56:31
	 *
	 * @return list
	 */
	public List<IfSysStuff> getStuffByEmail(String email) {
		// 筛选数据
		List<IfSysStuff> list = null;
		try {
			list = ifSysStuffEmailDAO.getStuffByEmail(email);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}
}
