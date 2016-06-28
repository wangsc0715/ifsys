/**
 * Title: ReturnCodeDao.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import java.util.List;

import com.gigold.pay.autotest.bo.ReturnCode;

/**
 * Title: ReturnCodeDao<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年12月5日上午9:30:58
 *
 */
public interface ReturnCodeDao {
	/**
	 * 
	 * Title: addRetrunCode<br/>
	 * Description: 新增返回码<br/>
	 * @author xiebin
	 * @date 2015年12月5日上午9:41:35
	 *
	 * @param returnCode
	 * @return
	 */
	public int addRetrunCode(ReturnCode returnCode);
	/**
	 * 
	 * Title: deleteReturnCodeByIfId<br/>
	 * Description: 根据接口ID删除返回码<br/>
	 * @author xiebin
	 * @date 2015年12月5日上午9:42:14
	 *
	 * @param ifId
	 * @return
	 */
	public int deleteReturnCodeByIfId(int ifId);
	/**
	 * 
	 * Title: deleteReturnCodeById<br/>
	 * Description: 根据ID删除返回码 <br/>
	 * @author xiebin
	 * @date 2015年12月5日上午9:42:35
	 *
	 * @param id
	 * @return
	 */
	public int deleteReturnCodeById(int id);
	/**
	 * 
	 * Title: updateReturnCodeById<br/>
	 * Description: 修改返回码<br/>
	 * @author xiebin
	 * @date 2015年12月5日上午9:42:57
	 *
	 * @param id
	 * @return
	 */
	public int updateReturnCodeById(ReturnCode returnCode);
	/**
	 * 
	 * Title: getReturnCodeByIfId<br/>
	 * Description: 根据接口ID获取所有返回码列表<br/>
	 * @author xiebin
	 * @date 2015年12月5日上午9:43:51
	 *
	 * @param returnCode
	 * @return
	 */
	public List<ReturnCode> getReturnCodeByIfId(int ifId);
	/**
	 * 
	 * Title: getReturnCodeById<br/>
	 * Description: 根据ID获取返回码信息<br/>
	 * @author xiebin
	 * @date 2015年12月7日下午1:33:42
	 *
	 * @param id
	 * @return
	 */
	public ReturnCode getReturnCodeById(int id);
	

}
