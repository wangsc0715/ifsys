package com.gigold.pay.autotest.controller;

import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.framework.web.ResponseDto;


/**
 * Created by chenkuan
 * on 16/3/8.
 */
public class IfSysFieldReferReqDto extends RequestDto {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String mockid;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMockid() {
        return mockid;
    }

    public void setMockid(String mockid) {
        this.mockid = mockid;
    }

    public String validation(){
        if(this.mockid.isEmpty() && this.id.isEmpty()){
            return CodeItem.REQ_JSON_IS_NULL;
        }else{
            return SysCode.SUCCESS;
        }
    }
}
