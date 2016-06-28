package com.gigold.pay.autotest.controller;

import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.framework.web.ResponseDto;

import java.util.List;

/**
 * Created by chenkuan
 * on 16/3/8.
 */
public class IfSysFieldReferListRspDto extends ResponseDto {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private List<IfSysFeildRefer> list;
    /**
     * @return the list
     */
    public List<IfSysFeildRefer> getList() {
        return list;
    }
    /**
     * @param list the list to set
     */
    public void setList(List<IfSysFeildRefer> list) {
        this.list = list;
    }
}
