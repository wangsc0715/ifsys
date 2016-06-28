package com.gigold.pay.ifsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.gigold.pay.framework.core.SysCode;
import com.gigold.pay.framework.util.common.StringUtil;
import com.gigold.pay.framework.web.BaseController;
import com.gigold.pay.framework.web.ResponseDto;
import com.gigold.pay.ifsys.bo.InterFaceField;
import com.gigold.pay.ifsys.bo.ReturnCode;
import com.gigold.pay.ifsys.service.InterFaceFieldService;
import com.gigold.pay.ifsys.service.RetrunCodeService;
import com.gigold.pay.ifsys.util.ForMatJSONStr;

@Controller
public class InterFaceFieldController extends BaseController {
	@Autowired
	InterFaceFieldService interFaceFeildService;
	@Autowired
	RetrunCodeService retrunCodeService;

	/**
	 * @return the interFaceFeildService
	 */
	public InterFaceFieldService getInterFaceFeildService() {
		return interFaceFeildService;
	}

	/**
	 * @param interFaceFeildService the interFaceFeildService to set
	 */
	public void setInterFaceFeildService(InterFaceFieldService interFaceFeildService) {
		this.interFaceFeildService = interFaceFeildService;
	}

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

	/**
	 * 
	 * Title: getRspCode<br/>
	 * Description: 获取接口对应的返回码信息<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月8日上午9:32:03
	 *
	 * @param ifId
	 * @return
	 */
	public String getRspCode(int ifId) {
		List<ReturnCode> rspCdList = retrunCodeService.getReturnCodeByIfId(ifId);
		StringBuilder sbui = new StringBuilder();
		if (rspCdList != null) {
			int size = rspCdList.size();
			int i = 0;
			for (ReturnCode rspCd : rspCdList) {
				sbui.append(rspCd.getRspCode()).append(":").append(rspCd.getRspCodeDesc());
				if (i < size - 1) {
					sbui.append("; ");
				}
				i++;
			}
		}
		return sbui.toString();

	}

	/**
	 * 
	 * Title: getReqestInfoByIfId<br/>
	 * 根据接口ID获取接口请求部分的信息 转为JSON格式的字符串: <br/>
	 * 
	 * @author xb
	 * @date 2015年10月12日上午9:31:30
	 *
	 * @param qdto
	 * @return getRequestJson
	 */
	@RequestMapping(value = "/getInterFaceFieldsJson.do")
	public @ResponseBody InterFaceFieldResJsonDto getReqestInfoByIfId(@RequestBody InterFaceFieldReqDto qdto) {
		InterFaceField interFaceField = qdto.getInterFaceField();
		InterFaceFieldResJsonDto dto = new InterFaceFieldResJsonDto();
		if(interFaceField==null){
			dto.setRspCd(CodeItem.NEDD_ITEM_FAILURE);
			return dto;
		}
		if(StringUtil.isBlank(interFaceField.getFieldFlag())||interFaceField.getIfId()==0){
			dto.setRspCd(CodeItem.NEDD_ITEM_FAILURE);
			return dto;
		}
		if(!"1".equals(interFaceField.getFieldFlag())&&!"2".equals(interFaceField.getFieldFlag())){
			dto.setRspCd(CodeItem.INVAILD_PARM_FAILURE);
			return dto;
		}
		StringBuilder ss = new StringBuilder();
		ss.append("{");
		//设置响应字段 返回码
		if ("2".equals(interFaceField.getFieldFlag())) {
			ss.append("\"rspCd\"").append(":\"").append(getRspCode(interFaceField.getIfId())).append("\" /*返回码*/,");
		}
		List<InterFaceField> rlist = interFaceFeildService.getFirstReqFieldByIfId(interFaceField);
		if(rlist!=null){
		    proJSON(ss, rlist, interFaceField.getFieldCheck());
		}
		String jsonStr = ss.toString().replaceAll(",\\}", "}").replaceAll(",\\]", "]");
		jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
		
		dto.setRspCd(SysCode.SUCCESS);
		dto.setJsonStr(ForMatJSONStr.format(jsonStr));
		return dto;

	}

	@RequestMapping(value = "/addInterFaceField.do")
	public @ResponseBody InterFaceFieldResAddDto addInterFaceField(@RequestBody InterFaceFieldReqDto qdto) {
		InterFaceFieldResAddDto dto = new InterFaceFieldResAddDto();
		InterFaceField interFaceField = qdto.getInterFaceField();
		boolean flag = interFaceFeildService.addInterFaceField(interFaceField);
		if (flag) {
			dto.setInterFaceField(interFaceField);
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}
		return dto;

	}

	@RequestMapping(value = "/getFieldByIfId.do")
	public @ResponseBody InterFaceFieldResListDto getFieldByIfId(@RequestBody InterFaceFieldReqDto qdto) {
		InterFaceFieldResListDto dto = new InterFaceFieldResListDto();
		InterFaceField interFaceField = qdto.getInterFaceField();
		List<InterFaceField> list = interFaceFeildService.getFieldByIfId(interFaceField);
		if (list != null) {
			dto.setList(list);
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}
		return dto;

	}

	@RequestMapping(value = "/deleteFieldByLevel.do")
	public @ResponseBody ResponseDto deleteFieldByLevel(@RequestBody InterFaceFieldReqDto qdto) {
		ResponseDto dto = new ResponseDto();
		InterFaceField interFaceField = qdto.getInterFaceField();
		boolean flag = interFaceFeildService.deleteFieldByLevel(interFaceField);
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}
		return dto;

	}

	@RequestMapping(value = "/updateInterFaceField.do")
	public @ResponseBody ResponseDto updateInterFaceField(@RequestBody InterFaceFieldReqDto qdto) {
		ResponseDto dto = new ResponseDto();
		InterFaceField interFaceField = qdto.getInterFaceField();
		boolean flag = interFaceFeildService.updateInterFaceField(interFaceField);
		if (flag) {
			dto.setRspCd(SysCode.SUCCESS);
		} else {
			dto.setRspCd(CodeItem.IF_FAILURE);
		}
		return dto;

	}

	/**
	 * 
	 * Title: proJSON<br/>
	 * 将接口请求部分解析成JSON字符串: <br/>
	 * 
	 * @author xb
	 * @date 2015年10月12日上午9:32:51
	 *
	 * @param ss
	 * @param list
	 */
	public void proJSON(StringBuilder ss, List<InterFaceField> list, String parentFieldCheck) {
		int i = 0;

		InterFaceField ff = null;
		for (i = 0; i < list.size(); i++) {
			ff = list.get(i);
			List<InterFaceField> clist = interFaceFeildService.getFieldByparentId(ff);
			ss.append("\"" + ff.getFieldName() + "\":");
			if (clist != null && clist.size() > 0) {
				if ("4".equals(ff.getFieldCheck())) {
					ss.append("[{");
				} else {
					ss.append("{" + "/*" + ff.getFieldDesc() + "*/\n");
				}
				proJSON(ss, clist, ff.getFieldCheck());
			} else {
				ss.append("\"" + ff.getFieldReferValue() + "\" /*" + ff.getFieldDesc() + "*/");
				if (i < list.size() - 1) {
					ss.append(",");
				}
			}
		}

		if (i > 0) {
			if (!StringUtils.isEmpty(parentFieldCheck) && parentFieldCheck.equals("4")) {
				ss.append("}]");
			} else {
				ss.append("}");
			}
			ss.append(",");
		}

	}

}
