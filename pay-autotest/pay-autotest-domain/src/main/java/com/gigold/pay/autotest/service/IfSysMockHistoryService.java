/**
 * Title: IfSysMockHistoryService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.IfSysMockHistory;
import com.gigold.pay.autotest.dao.IfSysMockHistoryDAO;
import com.gigold.pay.framework.core.Domain;
import java.util.List;

/**
 * Title: IfSysMockHistoryService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月16日下午1:31:42
 *
 */
@Service
public class IfSysMockHistoryService extends Domain {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	IfSysMockHistoryDAO ifSysMockHistoryDAO;

	/**
	 * 
	 * Title: addIfSysMockHistory<br/>
	 * Description: 添加测试历史数据记录<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月16日下午1:35:55
	 *
	 * @param ifSysMockHistory
	 * @return
	 */
	public boolean addIfSysMockHistory(IfSysMockHistory ifSysMockHistory) {
		boolean flag = false;
		try {
			List<IfSysMockHistory> list = ifSysMockHistoryDAO.getmockhistoryByJrnAndMockId(ifSysMockHistory);
			//如果当前批次还没有相应的用例测试记录 就新增一条
			if (list!= null &&list.size() == 0) {
				int count = ifSysMockHistoryDAO.addIfSysMockHistory(ifSysMockHistory);
				if (count > 0) {
					flag = true;
				}
			}
		} catch (Exception e) {
			debug("调用 addIfSysMockHistory 数据库异常");
			flag = false;
		}
		return flag;
	}

	public List<IfSysMockHistory> getNewestReslutOf(int limit) {
		List<IfSysMockHistory> list = null;
		try {
			list = ifSysMockHistoryDAO.getNewestReslutOf(limit);
		} catch (Exception e) {
			System.out.println(e);
			list = null;
		}
		return list;
	}

}
