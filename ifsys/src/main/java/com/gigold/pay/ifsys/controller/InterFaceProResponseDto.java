package com.gigold.pay.ifsys.controller;

import java.util.List;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFacePro;

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
