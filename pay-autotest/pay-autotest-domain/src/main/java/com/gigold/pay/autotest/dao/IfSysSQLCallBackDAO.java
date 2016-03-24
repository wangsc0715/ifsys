package com.gigold.pay.autotest.dao;

import com.gigold.pay.autotest.bo.IfSysSQLCallBack;

import java.util.List;

/**
 * Created by chenkuan
 * on 16/3/24.
 */
public interface IfSysSQLCallBackDAO {
    /**
     * 根据mockid查询回调sql
     * @return
     */
    public IfSysSQLCallBack getIfSysSQLCallBackByMockId(int mockid);

    /**
     * 根据id查询回调sql
     * @return
     */
    public IfSysSQLCallBack getIfSysSQLCallBackById(int id);

    /**
     * 更新回调sql
     * @return
     */
    public void updateIfSysSQLCallBack(IfSysSQLCallBack sqlCallBack);
}
