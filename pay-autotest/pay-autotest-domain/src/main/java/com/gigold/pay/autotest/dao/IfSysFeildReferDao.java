/**
 * Title: IfSysMockDAO.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.dao;

import com.gigold.pay.autotest.bo.IfSysFeildRefer;

import java.util.List;

/**
 * 字段依赖
 *
 */
public interface IfSysFeildReferDao {
    /**
     * 查询所有依赖的字段
     * @param mockid 主mock
     * @return 所依赖的所有字段
     */
    public List<IfSysFeildRefer> queryReferFields(int mockid);
}
