package com.gigold.pay.autotest.controller;

import java.util.List;

import com.gigold.pay.autotest.bo.InterFaceSysTem;
import com.gigold.pay.framework.web.ResponseDto;

public class InterFaceSysResponseDto extends ResponseDto {

    /** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private List<InterFaceSysTem> sysList;

    /**
     * @return the sysList
     */
    public List<InterFaceSysTem> getSysList() {
        return sysList;
    }

    /**
     * @param sysList
     *            the sysList to set
     */
    public void setSysList(List<InterFaceSysTem> sysList) {
        this.sysList = sysList;
    }

}
