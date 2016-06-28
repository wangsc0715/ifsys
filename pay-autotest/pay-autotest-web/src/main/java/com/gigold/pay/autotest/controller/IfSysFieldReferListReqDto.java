package com.gigold.pay.autotest.controller;

import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.framework.web.ResponseDto;

import java.util.List;

/**
 * Created by chenkuan
 * on 16/3/8.
 */
public class IfSysFieldReferListReqDto extends RequestDto {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private List<IfSysFeildRefer>  referList;

    public String validation(){
        return SysCode.SUCCESS;
    }

    public List<IfSysFeildRefer> getReferList() {
        return referList;
    }

    public void setReferList(List<IfSysFeildRefer> referList) {
        this.referList = referList;
    }
}
