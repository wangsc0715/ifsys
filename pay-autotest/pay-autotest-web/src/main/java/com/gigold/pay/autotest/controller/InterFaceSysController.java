package com.gigold.pay.autotest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.autotest.bo.InterFaceSysTem;
import com.gigold.pay.autotest.service.InterFaceSysService;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.web.BaseController;

@Controller
public class InterFaceSysController extends BaseController{
    @Autowired
    InterFaceSysService interFaceSysService;

    
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
            dto.setRspCd(CodeItem.FAILURE);
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
            dto.setRspCd(CodeItem.FAILURE);
        }

        return dto;

    }

}
