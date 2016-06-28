/**
 * Title: InterFaceSystemDao.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.dao;

import java.util.List;

import com.gigold.pay.ifsys.bo.InterFaceSysTem;

/**
 * Title: InterFaceSystemDao<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xb
 * @date 2015年10月12日上午9:41:32
 *
 */
public interface InterFaceSystemDao {
public List<InterFaceSysTem> getAllSysInfo();
public InterFaceSysTem getSysInfoById(InterFaceSysTem interFaceSystem);
public List<InterFaceSysTem> queryTest(InterFaceSysTem interFaceSystem);
}
