package com.gigold.pay.ifsys.dao;

import java.util.List;
import java.util.Map;

import com.gigold.pay.ifsys.bo.InterFaceInfo;

public interface InterFaceDao {
	/**
	 * 根据Id获得接口信息
	 * 
	 * @param id
	 * @return
	 */
	public InterFaceInfo getInterFaceById(InterFaceInfo interFaceInfo);

	/**
	 * 根据产品Id获得接口信息
	 * 
	 * @param id
	 * @return
	 */
	public List<InterFaceInfo> getInterFaceByProId(int id);


	/**
	 * 获得所有接口信息－－分页
	 * 
	 * @return
	 */
	public List<InterFaceInfo> getAllInterFaceByPage();
	/**
	 * 
	 * Title: queryInterFaceByPage<br/>
	 * 模糊查询 分页: <br/>
	 * @author xb
	 * @date 2015年10月15日下午12:56:37
	 *
	 * @return
	 */
	public List<InterFaceInfo> queryInterFaceByPage( InterFaceInfo interFaceInfo);
	


	/**
	 * 修改接口信息
	 * 
	 * @param interFaceInfo
	 * @return
	 */
	public int updateInterFace(InterFaceInfo interFaceInfo);

	/**
	 * 新增接口信息
	 * 
	 * @param interFaceInfo
	 * @return
	 */
	public int addInterFace(InterFaceInfo interFaceInfo);

	/**
	 * 根据Id删除接口信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteInterFaceById(InterFaceInfo interFaceInfo);
}
