package com.gigold.pay.autotest.dao;

import java.util.List;

import com.gigold.pay.autotest.bo.InterFaceInfo;

public interface InterFaceDao {
	/**
	 * 获得所有接口数
	 * 
	 * @param
	 * @return
	 */
	public int getAllIfSysCount();

	/**
	 * 根据Id获得模块的接口数
	 *
	 * @param
	 * @return
	 */
	public int getAllIfSysCountByMod(int modId);

	/**
	 * 根据Id获得接口信息
	 *
	 * @param id
	 * @return
	 */
	public InterFaceInfo getInterFaceById(int id);

	/**
	 * 
	 * Title: getAllIfSys<br/>
	 * Description:获取所有的接口信息 <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月1日下午2:59:07
	 *
	 * @return
	 */
	public List<InterFaceInfo> getAllIfSys(InterFaceInfo interFaceInfo);
	/**
	 * 
	 * Title: getAllIfSys<br/>
	 * Description:获取所有的接口信息 <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月1日下午2:59:07
	 *
	 * @return
	 */
	public List<InterFaceInfo> getAllIfSysForTest();

}
