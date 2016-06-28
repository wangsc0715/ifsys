package com.gigold.pay.autotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.autotest.service.IfSysReferService;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ResponseDto;

@Controller
public class IfSysReferController extends BaseController {
	@Autowired
	IfSysReferService ifSysReferService;

	@RequestMapping(value = "/addmockrefer.do")
	public @ResponseBody ResponseDto addmockrefer(@RequestBody IfSysReferAddReqDto qdto) {
		IfSysRefer ifSysRefer = null;
		try {
			ifSysRefer = createBO(qdto, IfSysRefer.class);
		} catch (PendingException e) {
			debug("创建BO失败");
			e.printStackTrace();
		}
		ResponseDto dto = new ResponseDto();
		boolean flag = ifSysReferService.addMockRefer(ifSysRefer);
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.FAILURE);
		}
		return dto;

	}
	
	@RequestMapping(value = "/updpatemockrefer.do")
	public @ResponseBody ResponseDto updpatemockrefer(@RequestBody IfSysReferAddReqDto qdto) {
		IfSysRefer ifSysRefer = null;
		try {
			ifSysRefer = createBO(qdto, IfSysRefer.class);
		} catch (PendingException e) {
			debug("创建BO失败");
			e.printStackTrace();
		}
		ResponseDto dto = new ResponseDto();
		boolean flag = ifSysReferService.updateMockRefer(ifSysRefer);
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.FAILURE);
		}
		return dto;

	}
	

	@RequestMapping(value = "/deletemockrefer.do")
	public @ResponseBody ResponseDto deleteMockRefer(@RequestBody IfSysReferAddReqDto qdto) {
		ResponseDto dto = new ResponseDto();
		boolean flag = ifSysReferService.deleteMockRefer(qdto.getId());
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.FAILURE);
		}
		return dto;

	}
	@RequestMapping(value = "/getreferList.do")
	public @ResponseBody ResponseDto getReferList(@RequestBody IfSysReferAddReqDto qdto) {
		IfSysReferListRspDto dto = new IfSysReferListRspDto();
		List<IfSysRefer> list= ifSysReferService.getReferList(qdto.getMockId());
	    if (list!=null) {
	    	dto.setList(list);
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.FAILURE);
		}
		return dto;

	}

	

}
