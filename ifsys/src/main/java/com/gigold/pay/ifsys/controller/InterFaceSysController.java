package com.gigold.pay.ifsys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.service.InterFaceSysService;

@Controller
public class InterFaceSysController extends BaseController{
    @Autowired
    InterFaceSysService interFaceSysService;

    
    /**
	 * @return the interFaceSysService
	 */
	public InterFaceSysService getInterFaceSysService() {
		return interFaceSysService;
	}

	/**
	 * @param interFaceSysService the interFaceSysService to set
	 */
	public void setInterFaceSysService(InterFaceSysService interFaceSysService) {
		this.interFaceSysService = interFaceSysService;
	}

	@RequestMapping(value = "/getSysInfoById.do")
    public @ResponseBody InterFaceSysResponseDto getSysInfoById( @RequestBody InterFaceSysRequestDto qdto) {
        InterFaceSysTem interFaceSystem = qdto.getInterFaceSysTem();
        InterFaceSysResponseDto dto = new InterFaceSysResponseDto();
        List<InterFaceSysTem> rlist = new ArrayList<InterFaceSysTem>();
        interFaceSystem = interFaceSysService.getSysInfoById(interFaceSystem);
        if (interFaceSystem != null) {
            rlist.add(interFaceSystem);
            dto.setSysList(rlist);
            dto.setRspCd(SysCode.SUCCESS);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }

        return dto;

    }

    @RequestMapping(value = "/getAllSysInfo.do")
    public @ResponseBody InterFaceSysResponseDto getAllSysInfo( ) {
        InterFaceSysResponseDto dto = new InterFaceSysResponseDto();
        List<InterFaceSysTem> rlist= interFaceSysService.getAllSysInfo();
        if (rlist != null) {
            dto.setSysList(rlist);
            dto.setRspCd(SysCode.SUCCESS);
        } else {
            dto.setRspCd(CodeItem.IF_FAILURE);
        }

        return dto;

    }

}
