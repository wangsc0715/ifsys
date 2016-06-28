/**
 * Title: InterFaceProDao.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import java.util.List;

import com.gigold.pay.autotest.bo.InterFacePro;

/**
 * Title: InterFaceProDao<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xb
 * @date 2015年10月12日上午9:41:46
 *
 */
public interface InterFaceProDao {

    public List<InterFacePro> getAllProInfo();

    public InterFacePro getProInfoById(InterFacePro interFacePro);

    public List<InterFacePro> getProInfoBySysId(InterFacePro interFacePro);
}
