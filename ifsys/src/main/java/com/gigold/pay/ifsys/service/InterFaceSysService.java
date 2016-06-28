package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.dao.InterFaceSystemDao;

@Service
public class InterFaceSysService extends Domain {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	InterFaceSystemDao interFaceSystemDao;

	/**
	 * @return the interFaceSystemDao
	 */
	public InterFaceSystemDao getInterFaceSystemDao() {
		return interFaceSystemDao;
	}

	/**
	 * @param interFaceSystemDao
	 *            the interFaceSystemDao to set
	 */
	public void setInterFaceSystemDao(InterFaceSystemDao interFaceSystemDao) {
		this.interFaceSystemDao = interFaceSystemDao;
	}

	/**
	 * 
	 * Title: getAllSysInfo<br/>
	 * Description: 获取所有的系统信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月17日下午1:41:32
	 *
	 * @return
	 */
	public List<InterFaceSysTem> getAllSysInfo() {
		List<InterFaceSysTem> list = null;
		try {
			list = interFaceSystemDao.getAllSysInfo();
		} catch (Exception e) {
			debug("调用 interFaceSystemDao.getAllSysInfo 发生异常");
		}
		return list;
	}

	/**
	 * 
	 * Title: getSysInfoById<br/>
	 * Description: 根据系统Id获取系统的信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月17日下午1:53:28
	 *
	 * @param interFaceSystem
	 * @return
	 */
	public InterFaceSysTem getSysInfoById(InterFaceSysTem interFaceSystem) {
		InterFaceSysTem interFaceSysTem = null;
		try {
			interFaceSysTem = interFaceSystemDao.getSysInfoById(interFaceSystem);
		} catch (Exception e) {
			debug("调用 interFaceSystemDao.getSysInfoById 发生异常");
		}
		return interFaceSysTem;
	}

}
