package com.gigold.pay.autotest.controller;

import java.util.List;

import com.gigold.pay.autotest.bo.InterFacePro;
import com.gigold.pay.framework.web.ResponseDto;

public class InterFaceProResponseDto extends ResponseDto {

    private List<InterFacePro> proList;

    /**
     * @return the proList
     */
    public List<InterFacePro> getProList() {
        return proList;
    }

    /**
     * @param proList
     *            the proList to set
     */
    public void setProList(List<InterFacePro> proList) {
        this.proList = proList;
    }

}
