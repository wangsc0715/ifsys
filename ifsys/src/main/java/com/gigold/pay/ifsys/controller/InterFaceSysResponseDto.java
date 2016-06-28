package com.gigold.pay.ifsys.controller;

import java.util.List;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceSysTem;

public class InterFaceSysResponseDto extends ResponseDto {

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
