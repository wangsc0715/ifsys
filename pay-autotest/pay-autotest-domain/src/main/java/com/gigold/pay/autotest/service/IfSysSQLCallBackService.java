/**
 * Title: IfSysMockService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import com.gigold.pay.autotest.bo.IfSysSQLCallBack;
import com.gigold.pay.autotest.dao.IfSysSQLCallBackDAO;
import com.gigold.pay.framework.core.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
public class IfSysSQLCallBackService extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	IfSysSQLCallBackDAO ifSysSQLCallBackDAO ;

	/**
	 * 根据mockid查询回调sql
	 * @return
	 */
	public IfSysSQLCallBack getIfSysSQLCallBackByMockId(int mockid){

		IfSysSQLCallBack ifSysSQLCallBack = null;
		try {
			ifSysSQLCallBack = ifSysSQLCallBackDAO.getIfSysSQLCallBackByMockId(mockid);
		}catch (Exception e){
			e.printStackTrace();
		}
		return ifSysSQLCallBack;
	}

	/**
	 * 根据id查询回调sql
	 * @return
	 */
	public IfSysSQLCallBack getIfSysSQLCallBackById(int id){
		IfSysSQLCallBack ifSysSQLCallBack = null;
		try {
			ifSysSQLCallBack = ifSysSQLCallBackDAO.getIfSysSQLCallBackById(id);
		}catch (Exception e){
			e.printStackTrace();
		}
		return ifSysSQLCallBack;
	}

	/**
	 * 更新回调sql
	 * @return
	 */
	public IfSysSQLCallBack updateIfSysSQLCallBack(IfSysSQLCallBack sqlCallBack){
		IfSysSQLCallBack ifSysSQLCallBack = null;
		try {
			ifSysSQLCallBackDAO.updateIfSysSQLCallBack(sqlCallBack);
			ifSysSQLCallBack = ifSysSQLCallBackDAO.getIfSysSQLCallBackById(sqlCallBack.getId());
		}catch (Exception e){
			e.printStackTrace();
		}
		return ifSysSQLCallBack;
	}
}
