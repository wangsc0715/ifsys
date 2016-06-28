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

public class InterFaceRequestDto extends RequestDto {
    private InterFaceInfo interFaceInfo;
	public InterFaceInfo getInterFaceInfo() {
        return interFaceInfo;
    }

    public void setInterFaceInfo(InterFaceInfo interFaceInfo) {
        this.interFaceInfo = interFaceInfo;
    }


}
