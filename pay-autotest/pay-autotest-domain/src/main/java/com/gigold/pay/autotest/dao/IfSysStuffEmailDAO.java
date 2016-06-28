/**
 * Title: IfSysMockDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import com.gigold.pay.autotest.bo.IfSysStuff;

import java.util.List;

/**
 * Title: IfSysMockDAO<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月26日下午4:07:08
 *
 */
public interface IfSysStuffEmailDAO {
	/**
	 *
	 * Title: getAllIfSys<br/>
	 * Description:根据email获取系统成员的信息 <br/>
	 *
	 * @author chenkuan
	 * @date 2015年12月11日
	 *
	 * @return
	 */
	public List<IfSysStuff> getStuffByEmail(String email);

}
