/**
 * Title: RetrunCodeService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.ifsys.bo.ReturnCode;
import com.gigold.pay.ifsys.dao.ReturnCodeDao;

/**
 * Title: RetrunCodeService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日上午9:46:14
 *
 */
@Service
public class RetrunCodeService extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ReturnCodeDao returnCodeDao;

	/**
	 * @return the returnCodeDao
	 */
	public ReturnCodeDao getReturnCodeDao() {
		return returnCodeDao;
	}

	/**
	 * @param returnCodeDao the returnCodeDao to set
	 */
	public void setReturnCodeDao(ReturnCodeDao returnCodeDao) {
		this.returnCodeDao = returnCodeDao;
	}

	/**
	 * 
	 * Title: addRetrunCode<br/>
	 * Description: 新增返回码<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月5日上午9:41:35
	 *
	 * @param returnCode
	 * @return
	 */
	public ReturnCode addRetrunCode(ReturnCode returnCode) {
		int count = -1;
		ReturnCode rspCode=null;
		try {
			 rspCode=returnCodeDao.getReturnCodeById(returnCode.getId());
			if(rspCode==null){
				count = returnCodeDao.addRetrunCode(returnCode);
			}else{
				count = returnCodeDao.updateReturnCodeById(returnCode);
			}
			
		} catch (Exception e) {
			returnCode =null;
		}
		if (count > 0) {
			return returnCode;
		}else{
			return null;
		}
	}

	/**
	 * 
	 * Title: deleteReturnCodeByIfId<br/>
	 * Description: 根据接口ID删除返回码<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月5日上午9:42:14
	 *
	 * @param ifId
	 * @return
	 */
	public boolean deleteReturnCodeByIfId(int ifId) {
		int count = -1;
		try {
			count = returnCodeDao.deleteReturnCodeByIfId(ifId);
		} catch (Exception e) {
			count = 0;
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Title: deleteReturnCodeById<br/>
	 * Description: 根据ID删除返回码 <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月5日上午9:42:35
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteReturnCodeById(int id) {
		int count = -1;
		try {
			count = returnCodeDao.deleteReturnCodeById(id);
		} catch (Exception e) {
			count = 0;
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Title: updateReturnCodeById<br/>
	 * Description: 修改返回码<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月5日上午9:42:57
	 *
	 * @param id
	 * @return
	 */
	public boolean updateReturnCodeById(ReturnCode returnCode) {
		int count = -1;
		try {
			count = returnCodeDao.updateReturnCodeById(returnCode);
		} catch (Exception e) {
			count = 0;
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Title: getReturnCodeByIfId<br/>
	 * Description: 根据接口ID获取所有返回码列表<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月5日上午9:43:51
	 *
	 * @param returnCode
	 * @return
	 */
	public List<ReturnCode> getReturnCodeByIfId(int ifId) {
		List<ReturnCode> list = null;
		try {
			list = returnCodeDao.getReturnCodeByIfId(ifId);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	/**
	 * 
	 * Title: getReturnCodeById<br/>
	 * Description: 根据ID获取返回码信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月7日下午1:35:52
	 *
	 * @param id
	 * @return
	 */
	public ReturnCode getReturnCodeById(int id) {
		ReturnCode returnCode = null;
		try {
			returnCode = returnCodeDao.getReturnCodeById(id);
		} catch (Exception e) {
			returnCode = null;
		}
		return returnCode;
	}
}
