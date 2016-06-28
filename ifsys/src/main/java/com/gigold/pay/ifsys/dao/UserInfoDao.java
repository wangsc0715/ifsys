package com.gigold.pay.ifsys.dao;

import java.util.List;

import com.gigold.pay.ifsys.bo.UserInfo;

public interface UserInfoDao {

	public UserInfo getUser(int id);

	public List<UserInfo> getList();

	public int addUser(UserInfo user);
	
	public UserInfo login(UserInfo user);

}
