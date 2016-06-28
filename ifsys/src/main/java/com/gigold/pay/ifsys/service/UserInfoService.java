package com.gigold.pay.ifsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.dao.UserInfoDao;

@Service
public class UserInfoService {
	@Autowired
	UserInfoDao userInfoDao;

	/**
	 * @return the userInfoDao
	 */
	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	/**
	 * @param userInfoDao
	 *            the userInfoDao to set
	 */
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	/**
	 * 
	 * Title: getUser<br/>
	 * Description: 根据ID查询用户信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月17日上午11:51:02
	 *
	 * @param id
	 * @return
	 */
	public UserInfo getUser(int id) {
		UserInfo userInfo = null;
		try {
			userInfo = userInfoDao.getUser(id);
		} catch (Exception e) {
			userInfo = null;
		}
		return userInfo;
	}

	/**
	 * 
	 * Title: login<br/>
	 * Description: 登录<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月17日上午11:51:22
	 *
	 * @param user
	 * @return
	 */
	public UserInfo login(UserInfo user) {
		UserInfo userInfo = null;
		try {
			userInfo = userInfoDao.login(user);
		} catch (Exception e) {
			userInfo = null;
		}
		return userInfo;
	}

	/**
	 * 
	 * Title: addUser<br/>
	 * Description: 添加用户信息<br/>
	 * @author xiebin
	 * @date 2015年12月17日上午11:51:38
	 *
	 * @param userInfo
	 * @return
	 */
	public boolean addUser(UserInfo userInfo) {
		boolean flag = false;
		try {
			int count = userInfoDao.addUser(userInfo);
			if (count > 0) {
				flag = true;
			}

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
