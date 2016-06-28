/**
 * Title: ReturnCodeController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.ReturnCode;
import com.gigold.pay.ifsys.service.RetrunCodeService;

/**
 * Title: ReturnCodeController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日上午9:53:18
 *
 */
@Controller
public class ReturnCodeController extends BaseController {
	@Autowired
	private RetrunCodeService retrunCodeService;

	/**
	 * @return the retrunCodeService
	 */
	public RetrunCodeService getRetrunCodeService() {
		return retrunCodeService;
	}

	/**
	 * @param retrunCodeService the retrunCodeService to set
	 */
	public void setRetrunCodeService(RetrunCodeService retrunCodeService) {
		this.retrunCodeService = retrunCodeService;
	}

	@RequestMapping("/addrspcd.do")
	public @ResponseBody RetrunCodeAddRspDto addRetrunCode(@RequestBody ReturnCodeReqDto dto) {
		debug("调用addRetrunCode");
		RetrunCodeAddRspDto rdto = new RetrunCodeAddRspDto();
		String rcode = dto.validation();
		if (!"00000".equals(rcode)) {
			rdto.setRspCd(rcode);
			return rdto;
		}
		ReturnCode returnCode = null;
		try {
			returnCode = createBO(dto, ReturnCode.class);
		} catch (PendingException e) {
			rdto.setRspCd(CodeItem.IF_FAILURE);
			debug("创建BO失败");
			e.printStackTrace();
			return rdto;
		}
		returnCode = retrunCodeService.addRetrunCode(returnCode);
		if (returnCode != null) {
			rdto.setId(returnCode.getId());
			rdto.setRspCd(SysCode.SUCCESS);
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
		}
		return rdto;
	}

	@RequestMapping("/delrspcdbyifid.do")
	public @ResponseBody ResponseDto deleteReturnCodeByIfId(@RequestBody ReturnCodeReqDto dto) {
		debug("调用deleteReturnCodeByIfId");
		ResponseDto rdto = new ResponseDto();
		int ifId = dto.getIfId();
		if (ifId == 0) {
			rdto.setRspCd(CodeItem.IF_ID_FAILURE);
			return rdto;
		}
		boolean flag = retrunCodeService.deleteReturnCodeByIfId(ifId);
		if (flag) {
			rdto.setRspCd(SysCode.SUCCESS);
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
		}
		return rdto;
	}

	@RequestMapping("/delrspcdbyid.do")
	public @ResponseBody ResponseDto deleteReturnCodeById(@RequestBody ReturnCodeReqDto dto) {
		debug("调用deleteReturnCodeById");
		ResponseDto rdto = new ResponseDto();
		int id = dto.getId();
		if (id == 0) {
			rdto.setRspCd(CodeItem.ID_FAILURE);
			return rdto;
		}
		boolean flag = retrunCodeService.deleteReturnCodeById(id);
		if (flag) {
			rdto.setRspCd(SysCode.SUCCESS);
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
		}
		return rdto;
	}

	@RequestMapping("/updaterspcdbyid.do")
	public @ResponseBody ResponseDto updateReturnCodeById(@RequestBody ReturnCodeReqDto dto) {
		debug("调用updateReturnCodeById");
		ResponseDto rdto = new ResponseDto();
		String rcode = dto.validation();
		if (!"00000".equals(rcode)) {
			rdto.setRspCd(rcode);
			return rdto;
		}
		ReturnCode returnCode = null;
		try {
			returnCode = createBO(dto, ReturnCode.class);
		} catch (PendingException e) {
			rdto.setRspCd(CodeItem.IF_FAILURE);
			debug("创建BO失败");
			e.printStackTrace();
			return rdto;
		}
		boolean flag = retrunCodeService.updateReturnCodeById(returnCode);
		if (flag) {
			rdto.setRspCd(SysCode.SUCCESS);
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
		}
		return rdto;
	}

	@RequestMapping("/getrspcdbyifid.do")
	public @ResponseBody RetrunCodeRspDto getReturnCodeByIfId(@RequestBody ReturnCodeReqDto dto) {
		debug("调用getReturnCodeByIfId");
		RetrunCodeRspDto rdto = new RetrunCodeRspDto();
		int ifId = dto.getIfId();
		if (ifId == 0) {
			rdto.setRspCd(CodeItem.IF_ID_FAILURE);
			return rdto;
		}
		List<ReturnCode> list = retrunCodeService.getReturnCodeByIfId(ifId);
		if (list != null) {
			rdto.setList(list);
			rdto.setRspCd(SysCode.SUCCESS);
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
		}
		return rdto;
	}
}
