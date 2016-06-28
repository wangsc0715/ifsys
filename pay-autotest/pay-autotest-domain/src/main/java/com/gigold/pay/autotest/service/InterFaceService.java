package com.gigold.pay.autotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.dao.InterFaceDao;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InterFaceService {

	@Autowired
	InterFaceDao interFaceDao;


	/**
	 * @return the interFaceDao
	 */
	public InterFaceDao getInterFaceDao() {
		return interFaceDao;
	}

	/**
	 * @param interFaceDao
	 *            the interFaceDao to set
	 */
	public void setInterFaceDao(InterFaceDao interFaceDao) {
		this.interFaceDao = interFaceDao;
	}

	/**
	 * 
	 * Title: getInterFaceById<br/>
	 * Description:根据ID查询接口信息<br/>
	 * 
	 * @author xb
	 * @date 2015年10月8日上午11:12:48
	 *
	 * @param id
	 * @return
	 */
	public InterFaceInfo getInterFaceById(int id) {
		InterFaceInfo inteFaceInfo = null;
		try {
			inteFaceInfo = interFaceDao.getInterFaceById(id);
		} catch (Exception e) {
			inteFaceInfo = null;
		}
		return inteFaceInfo;
	}

	/**
	 * 
	 * Title: getAllIfSys<br/>
	 * Description: 获取所有的接口信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月1日下午3:02:29
	 *
	 * @return
	 */
	public List<InterFaceInfo> getAllIfSys(InterFaceInfo interFaceInfo) {
		List<InterFaceInfo> list = null;
		try {
			list = interFaceDao.getAllIfSys(interFaceInfo);
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	/**
	 *
	 * Title: getAllIfSys<br/>
	 * Description: 获取所有的接口信息<br/>
	 *
	 * @author xiebin
	 * @date 2015年12月1日下午3:02:29
	 *
	 * @return
	 */
	public int getAllIfSysCount() {
		int count = 0;
		try {
			count = interFaceDao.getAllIfSysCount();
		} catch (Exception e) {
			e.printStackTrace();
			count = -1;
		}
		return count;
	}
	
	/**
	 * 
	 * Title: getAllIfSys<br/>
	 * Description: 获取所有的接口信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月1日下午3:02:29
	 *
	 * @return
	 */
	public PageInfo<InterFaceInfo> getAllIfSys(int curPageNum) {
		List<InterFaceInfo> list = null;
		PageInfo<InterFaceInfo> pageInfo = null;
		PageHelper.startPage(curPageNum, Integer.parseInt(SystemPropertyConfigure.getProperty("sys.pageSize")));
		try {
			list = interFaceDao.getAllIfSysForTest();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		if(list!=null){
			pageInfo=new PageInfo<InterFaceInfo>(list);
		}
		return pageInfo;
	}
	
}
