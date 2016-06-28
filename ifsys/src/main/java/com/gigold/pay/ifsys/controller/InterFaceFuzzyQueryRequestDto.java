/**
 * Title: QueryDemoRequestDto.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import com.gigold.pay.framework.web.RequestDto;
import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.bo.MyPageInfo;

public class InterFaceFuzzyQueryRequestDto extends RequestDto {
    private MyPageInfo pageInfo;
    private InterFaceInfo interFaceInfo;
    /**
     * @return the interFaceInfo
     */
    public InterFaceInfo getInterFaceInfo() {
        return interFaceInfo;
    }

    /**
     * @param interFaceInfo the interFaceInfo to set
     */
    public void setInterFaceInfo(InterFaceInfo interFaceInfo) {
        this.interFaceInfo = interFaceInfo;
    }

    /**
     * @return the pageInfo
     */
    public MyPageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     * @param pageInfo the pageInfo to set
     */
    public void setPageInfo(MyPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
