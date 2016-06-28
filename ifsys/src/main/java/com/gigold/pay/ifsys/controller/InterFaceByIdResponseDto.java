package com.gigold.pay.ifsys.controller;

import java.util.List;

import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.bo.InterFacePro;
import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.bo.UserInfo;

public class InterFaceByIdResponseDto extends ResponseDto {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private InterFaceInfo interFaceInfo;
	private UserInfo userInfo;
	private InterFaceSysTem system;
	private InterFacePro pro;
	private List<InterFaceField> fieldList;

	/**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the system
     */
    public InterFaceSysTem getSystem() {
        return system;
    }

    /**
     * @param system the system to set
     */
    public void setSystem(InterFaceSysTem system) {
        this.system = system;
    }

    /**
     * @return the pro
     */
    public InterFacePro getPro() {
        return pro;
    }

    /**
     * @param pro the pro to set
     */
    public void setPro(InterFacePro pro) {
        this.pro = pro;
    }

    /**
     * @return the fieldList
     */
    public List<InterFaceField> getFieldList() {
        return fieldList;
    }

    /**
     * @param fieldList the fieldList to set
     */
    public void setFieldList(List<InterFaceField> fieldList) {
        this.fieldList = fieldList;
    }

    public InterFaceInfo getInterFaceInfo() {
		return interFaceInfo;
	}

	public void setInterFaceInfo(InterFaceInfo interFaceInfo) {
		this.interFaceInfo = interFaceInfo;
	}

}
