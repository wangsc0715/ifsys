package com.gigold.pay.ifsys.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gigold.pay.framework.base.DomainFactory;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.util.common.StringUtil;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.bo.InterFaceInfo;
import com.gigold.pay.ifsys.bo.InterFacePro;
import com.gigold.pay.ifsys.bo.InterFaceSysTem;
import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.service.InterFaceFieldService;
import com.gigold.pay.ifsys.service.InterFaceProService;
import com.gigold.pay.ifsys.service.InterFaceService;
import com.gigold.pay.ifsys.service.InterFaceSysService;
import com.gigold.pay.ifsys.service.UserInfoService;
import com.gigold.pay.ifsys.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class InterFaceController extends BaseController {
	@Autowired
	InterFaceService interFaceService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	InterFaceSysService interFaceSysService;
	@Autowired
	InterFaceProService interFaceProService;
	@Autowired
	InterFaceFieldService interFaceFieldService;

	/**
	 * @param interFaceService
	 *            the interFaceService to set
	 */
	public void setInterFaceService(InterFaceService interFaceService) {
		this.interFaceService = interFaceService;
	}

	/**
	 * @param userInfoService
	 *            the userInfoService to set
	 */
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	/**
	 * @param interFaceSysService
	 *            the interFaceSysService to set
	 */
	public void setInterFaceSysService(InterFaceSysService interFaceSysService) {
		this.interFaceSysService = interFaceSysService;
	}

	/**
	 * @param interFaceProService
	 *            the interFaceProService to set
	 */
	public void setInterFaceProService(InterFaceProService interFaceProService) {
		this.interFaceProService = interFaceProService;
	}

	/**
	 * @param interFaceFieldService
	 *            the interFaceFieldService to set
	 */
	public void setInterFaceFieldService(InterFaceFieldService interFaceFieldService) {
		this.interFaceFieldService = interFaceFieldService;
	}

	/**
	 * 根据Id获取接口明细信息 用户接口修改页
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryInterFaceById")
	public @ResponseBody InterFaceByIdResponseDto getInterFaceById(@RequestBody InterFaceRequestDto qdto) {
		debug("调用getInterFaceById：");
		InterFaceByIdResponseDto dto = new InterFaceByIdResponseDto();
		InterFaceInfo interFaceInfo = qdto.getInterFaceInfo();
		if (interFaceInfo == null || interFaceInfo.getId() == 0) {
			dto.setRspCd(CodeItem.IF_ID_FAILURE);
			return dto;
		}
		// 获取接口信息
		interFaceInfo = interFaceService.getInterFaceById(qdto.getInterFaceInfo());
		if (interFaceInfo == null) {
			dto.setRspCd(CodeItem.IF_FAILURE);
			return dto;
		}
		// 获取所属系统信息
		InterFaceSysTem interFaceSysTem = (InterFaceSysTem) SpringContextHolder.getBean(InterFaceSysTem.class);
		interFaceSysTem.setId(interFaceInfo.getIfSysId());
		interFaceSysTem = interFaceSysService.getSysInfoById(interFaceSysTem);
		if (interFaceSysTem == null) {
			dto.setRspCd(CodeItem.IF_FAILURE);
			return dto;
		}
		// 获取所属产品信息
		InterFacePro interFacePro = (InterFacePro) SpringContextHolder.getBean(InterFacePro.class);
		interFacePro.setId(interFaceInfo.getIfProId());
		interFacePro = interFaceProService.getProInfoById(interFacePro);
		if (interFacePro == null) {
			dto.setRspCd(CodeItem.IF_FAILURE);
			return dto;
		}
		// 获取设计者信息
		UserInfo userInfo = (UserInfo) SpringContextHolder.getBean(UserInfo.class);
		userInfo.setId(interFaceInfo.getUid());
		userInfo = userInfoService.getUser(interFaceInfo.getUid());
		if (userInfo == null) {
			dto.setRspCd(CodeItem.IF_FAILURE);
			return dto;
		}
		// 获取接口定义字段信息
		InterFaceField interFaceField = (InterFaceField) SpringContextHolder.getBean(InterFaceField.class);
		interFaceField.setIfId(interFaceInfo.getId());
		List<InterFaceField> fieldList = interFaceFieldService.getFieldByIfId(interFaceField);
		if (fieldList == null) {
			dto.setRspCd(CodeItem.IF_FAILURE);
			return dto;
		}
		// 设置接口信息
		dto.setInterFaceInfo(interFaceInfo);
		// 设置接口所属系统信息
		dto.setSystem(interFaceSysTem);
		// 设置接口所属产品信息
		dto.setPro(interFacePro);
		// 设置接口设计者信息
		dto.setUserInfo(userInfo);
		// 设置接口请求、响应字段信息
		dto.setFieldList(fieldList);
		dto.setRspCd(SysCode.SUCCESS);
		return dto;
	}

	/**
	 * 
	 * Title: queryInterFaceByPage<br/>
	 * Description: 查询接口列表<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月3日下午2:41:50
	 *
	 * @param qdto
	 * @return
	 */
	@RequestMapping("/queryByCondition")
	public @ResponseBody InterFacePageResponseDto queryInterFaceByPage(
			@RequestBody InterFaceFuzzyQueryRequestDto qdto) {
		int pageSize = Integer.parseInt(
				SystemPropertyConfigure.getProperty(Constant.SYSTEMPARAM_PAGESIZE, String.valueOf(Constant.PAGE_SIZE)));
		PageHelper.startPage(qdto.getPageInfo().getPageNum(), pageSize);
		List<InterFaceInfo> list = interFaceService.queryInterFaceByPage(qdto.getInterFaceInfo());
		debug("传入的参数：" + qdto.getPageInfo().getPageNum() + "====" + qdto.getPageInfo().getPageSize());
		InterFacePageResponseDto dto = new InterFacePageResponseDto();
		if (list != null) {
			PageInfo<InterFaceInfo> pageInfo = new PageInfo<InterFaceInfo>(list);
			dto.setPageInfo(pageInfo);
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}
		return dto;
	}
	// /**
	// * 分页查询
	// *
	// * @param qdto
	// * @return
	// */
	//
	// @RequestMapping("/getInterFaceByPage")
	// public @ResponseBody InterFacePageResponseDto
	// getAllInterFace(@RequestBody InterFacePageRequestDto qdto) {
	// debug("调用getInterFaceByPage：");
	// int
	// pageSize=Integer.parseInt(SystemPropertyConfigure.getProperty(Constant.SYSTEMPARAM_PAGESIZE,
	// String.valueOf(Constant.PAGE_SIZE)));
	// PageHelper.startPage(qdto.getPageInfo().getPageNum(), pageSize);
	// List<InterFaceInfo> list = interFaceService.getAllInterFaceByPage();
	// debug("传入的参数：" + qdto.getPageInfo().getPageNum() + "====" +
	// qdto.getPageInfo().getPageSize());
	// InterFacePageResponseDto dto = new InterFacePageResponseDto();
	// if (list != null) {
	// PageInfo<InterFaceInfo> pageInfo = new PageInfo<InterFaceInfo>(list);
	// dto.setPageInfo(pageInfo);
	// dto.setRspCd(SysCode.SUCCESS);
	// } else {
	// dto.setRspCd(CodeItem.IF_FAILURE);
	// }
	// return dto;
	//
	// }

	/**
	 * 新增接口基本信息
	 * 
	 * @param interFaceInfo
	 * @return
	 */

	@RequestMapping("/addInterface")
	public @ResponseBody InterFaceResponseDto addInterface(@RequestBody InterFaceRequestDto qdto, HttpSession session) {
		InterFaceResponseDto dto = new InterFaceResponseDto();
		InterFaceInfo interFaceInfo = qdto.getInterFaceInfo();
		if(interFaceInfo==null){
			dto.setRspCd(CodeItem.NEDD_ITEM_FAILURE);
			return dto;
		}
		if(StringUtil.isBlank(interFaceInfo.getIfName())
				||StringUtil.isBlank(interFaceInfo.getIfProtocol())
				||StringUtil.isBlank(interFaceInfo.getIfUrl())
				||0==interFaceInfo.getIfSysId()
			    ||0==interFaceInfo.getIfProId()){
			dto.setRspCd(CodeItem.NEDD_ITEM_FAILURE);
			return dto;
		}
		UserInfo user = (UserInfo) session.getAttribute(SystemPropertyConfigure.getLoginKey());
		if (user != null) {
			interFaceInfo.setIfCreateBy(user.getId());
		}
		interFaceInfo.setIfCreateTime(new Timestamp((new Date()).getTime()));
		boolean flag = interFaceService.addInterFace(interFaceInfo);
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);
			dto.setInterFaceInfo(interFaceInfo);

		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}

		return dto;

	}

	/**
	 * 删除接口
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteInterFaceById")
	public @ResponseBody InterFaceResponseDto deleteInterFace(@RequestBody InterFaceRequestDto qdto) {
		InterFaceResponseDto dto = new InterFaceResponseDto();
		InterFaceInfo interFaceInfo=qdto.getInterFaceInfo();
		if(interFaceInfo==null||interFaceInfo.getId()==0){
			dto.setRspCd(CodeItem.IF_ID_FAILURE);
			return dto;
		}
		boolean flag = interFaceService.deleteInterFaceById(qdto.getInterFaceInfo());
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);

		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}
		return dto;

	}

	/**
	 * 修改接口基本信息
	 * 
	 * @param interFaceInfo
	 * @return
	 */
	@RequestMapping("/updateInterFace")
	public @ResponseBody InterFaceResponseDto updateInterFace(@RequestBody InterFaceRequestDto qdto) {
		InterFaceInfo interFaceInfo = qdto.getInterFaceInfo();
		InterFaceResponseDto dto = new InterFaceResponseDto();
		if(interFaceInfo==null||interFaceInfo.getId()==0){
			dto.setRspCd(CodeItem.IF_ID_FAILURE);
			return dto;
		}
		boolean flag = interFaceService.updateInterFace(interFaceInfo);
		
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);
			dto.setInterFaceInfo(interFaceInfo);
		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}
		return dto;

	}

}
