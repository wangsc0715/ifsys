/**
 * Title: InterFaceInvokerController.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.ifsys.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.core.exception.PendingException;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceInvoker;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.InterFaceInvokerService;

/**
 * Title: InterFaceInvokerController<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月23日下午3:27:08
 *
 */
@Controller
public class InterFaceInvokerController extends BaseController {
	@Autowired
	InterFaceInvokerService interFaceInvokerService;

	/**
	 * @return the interFaceInvokerService
	 */
	public InterFaceInvokerService getInterFaceInvokerService() {
		return interFaceInvokerService;
	}

	/**
	 * @param interFaceInvokerService
	 *            the interFaceInvokerService to set
	 */
	public void setInterFaceInvokerService(InterFaceInvokerService interFaceInvokerService) {
		this.interFaceInvokerService = interFaceInvokerService;
	}

	@RequestMapping("/addinvoker.do")
	public @ResponseBody InterFaceInvokerAddResDto addInterFaceInvoker(@RequestBody InterFaceInvokerReqDto dto,
			HttpSession session) {
		debug("调用 addInterFaceInvoker");
		InterFaceInvokerAddResDto rdto = new InterFaceInvokerAddResDto();
		String rspCode = dto.vaildate();
		if (!SysCode.SUCCESS.equals(rspCode)) {
			rdto.setRspCd(rspCode);
			return rdto;
		}
		// 在session中取uid
		UserInfo userInfo = (UserInfo) session.getAttribute(SystemPropertyConfigure.getLoginKey());
		if (userInfo == null) {
			rdto.setRspCd(SysCode.SYS_FAIL);
			rdto.setRspInf("用户未登录");
			return rdto;
		}
		InterFaceInvoker invoker = null;
		try {
			invoker = createBO(dto, InterFaceInvoker.class);
		} catch (PendingException e) {
			debug("创建BO失败");
			e.printStackTrace();
		}

		invoker.setuId(userInfo.getId());
		// 添加关注信息
		invoker = interFaceInvokerService.addInterFaceInvoker(invoker);
		if (invoker != null) {
			rdto.setInvoker(invoker);
			rdto.setRspCd(SysCode.SUCCESS);
			rdto.setRspInf("关注成功");
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
			rdto.setRspInf("关注失败");
		}

		return rdto;

	}

	/**
	 * 
	 * Title: getInvokerList<br/>
	 * Description: 获取接口关注列表<br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月23日下午5:06:49
	 *
	 * @param dto
	 * @param session
	 * @return
	 */
	@RequestMapping("/getinvokerlist.do")
	public @ResponseBody InterFaceInvokerResDto getInvokerListByFollowId(@RequestBody InterFaceInvokerReqDto dto) {
		debug("调用 getInvokerListByFollowId");
		InterFaceInvokerResDto rdto = new InterFaceInvokerResDto();
		if (dto.getIfFollowedId() == 0) {
			rdto.setRspCd(CodeItem.FLLOW_IF_ID_FAILURE);
			return rdto;
		}
		InterFaceInvoker invoker = (InterFaceInvoker) SpringContextHolder.getBean(InterFaceInvoker.class);
		invoker.setIfFollowedId(dto.getIfFollowedId());
		List<InterFaceInvoker> list = interFaceInvokerService.getInvokerList(invoker);
		if (list != null) {
			rdto.setList(list);
			rdto.setRspCd(SysCode.SUCCESS);
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
			rdto.setRspInf("获取失败");
		}

		return rdto;
	}

	/**
	 * 
	 * Title: deleteInterFaceInvoker<br/>
	 * Description: 取消关注<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月21日下午2:50:30
	 *
	 * @param dto
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteinvoker.do")
	public @ResponseBody ResponseDto deleteInterFaceInvoker(@RequestBody InterFaceInvokerReqDto dto,
			HttpSession session) {
		debug("调用 deleteInterFaceInvoker");
		ResponseDto rdto = new ResponseDto();
		// 在session中取uid
		UserInfo userInfo = (UserInfo) session.getAttribute(SystemPropertyConfigure.getLoginKey());
		if (userInfo == null) {
			rdto.setRspCd(SysCode.SYS_FAIL);
			rdto.setRspInf("用户未登录");
			return rdto;
		}

		// 取消关注信息
		boolean flag = interFaceInvokerService.deleteInvoker(dto.getId());
		if (flag) {
			rdto.setRspCd(SysCode.SUCCESS);
			rdto.setRspInf("取消关注成功");
		} else {
			rdto.setRspCd(CodeItem.IF_FAILURE);
			rdto.setRspInf("关注失败");
		}

		return rdto;

	}

}
