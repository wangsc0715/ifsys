/**
 * Title: IfSysMockReqaDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.bo;

import com.gigold.pay.autotest.bo.ReturnCode;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.framework.web.ResponseDto;

import java.util.List;
import java.util.Map;

/**
 * Title: IfSysMockReqaDto<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月30日上午11:39:51
 *
 */
public class IfSysInterFaceRsp{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private int ifId,ifProId,ifSysId;
    private String ifDesc,ifUrl,ifName,ifType,proName,sysName,dsname;
    private List<ReturnCode> returnCodeList;
    private List<Map> mockidList;

    public String getDsname() {
        return dsname;
    }

    public void setDsname(String dsname) {
        this.dsname = dsname;
    }

    public int getIfId() {
        return ifId;
    }

    public void setIfId(int ifId) {
        this.ifId = ifId;
    }

    public int getIfProId() {
        return ifProId;
    }

    public void setIfProId(int ifProId) {
        this.ifProId = ifProId;
    }

    public int getIfSysId() {
        return ifSysId;
    }

    public void setIfSysId(int ifSysId) {
        this.ifSysId = ifSysId;
    }

    public String getIfDesc() {
        return ifDesc;
    }

    public void setIfDesc(String ifDesc) {
        this.ifDesc = ifDesc;
    }

    public String getIfUrl() {
        return ifUrl;
    }

    public void setIfUrl(String ifUrl) {
        this.ifUrl = ifUrl;
    }

    public String getIfName() {
        return ifName;
    }

    public void setIfName(String ifName) {
        this.ifName = ifName;
    }

    public String getIfType() {
        return ifType;
    }

    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public List<ReturnCode> getReturnCodeList() {
        return returnCodeList;
    }

    public void setReturnCodeList(List<ReturnCode> returnCodeList) {
        this.returnCodeList = returnCodeList;
    }

    public List<Map> getMockidList() {
        return mockidList;
    }

    public void setMockidList(List<Map> mockidList) {
        this.mockidList = mockidList;
    }

}
