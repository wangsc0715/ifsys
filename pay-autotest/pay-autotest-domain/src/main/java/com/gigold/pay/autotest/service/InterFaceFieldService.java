package com.gigold.pay.autotest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.bo.ReturnCode;
import com.gigold.pay.autotest.dao.InterFaceFieldDao;
import com.gigold.pay.autotest.util.ForMatJSONStr;
import com.gigold.pay.framework.core.SysCode;

@Service
public class InterFaceFieldService {

	@Autowired
	InterFaceFieldDao interFaceFieldDao;
	@Autowired
	RetrunCodeService retrunCodeService;

	/**
	 * @return the interFaceFieldDao
	 */
	public InterFaceFieldDao getInterFaceFieldDao() {
		return interFaceFieldDao;
	}

	/**
	 * @param interFaceFieldDao
	 *            the interFaceFieldDao to set
	 */
	public void setInterFaceFieldDao(InterFaceFieldDao interFaceFieldDao) {
		this.interFaceFieldDao = interFaceFieldDao;
	}

	/**
	 * 
	 * Title: getFirstReqFieldByIfId<br/>
	 * Description: 获取接口的字段列表<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月4日上午9:55:37
	 *
	 * @param interFaceField
	 * @return
	 */
	public List<InterFaceField> getFirstReqFieldByIfId(InterFaceField interFaceField) {
		List<InterFaceField> list = null;
		try {
			list = interFaceFieldDao.getFirstReqFieldByIfId(interFaceField);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	/**
	 * 
	 * Title: getJsonStr<br/>
	 * Description: 获取接口字段的JSON展示字符串<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月4日上午9:57:58
	 *
	 * @param interFaceField
	 * @return
	 */
	public String getJsonStr(InterFaceField interFaceField) {
		String jsonStr = "";
		try {
			StringBuilder ss = new StringBuilder();
			ss.append("{");
			if ("2".equals(interFaceField.getFieldFlag())) {
				ss.append("\"rspCd\"").append(":\"").append(getRspCode(interFaceField.getIfId())).append("\",");
			}
			List<InterFaceField> rlist = getFirstReqFieldByIfId(interFaceField);
			proJSON(ss, rlist, interFaceField.getFieldCheck());
			jsonStr = ss.toString().replaceAll(",\\}", "}").replaceAll(",\\]", "]");
			jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
			jsonStr = ForMatJSONStr.format(jsonStr);

		} catch (Exception e) {
			jsonStr = "";
		}
		return jsonStr;

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
			List<InterFaceField> clist = interFaceFieldDao.getFieldByparentId(ff);
			ss.append("\"" + ff.getFieldName() + "\":");
			if (clist != null && clist.size() > 0) {
				if ("4".equals(ff.getFieldCheck())) {
					ss.append("[{");
				} else {
					ss.append("{\n");
				}
				proJSON(ss, clist, ff.getFieldCheck());
			} else {
				ss.append("\"" + ff.getFieldReferValue() + "\"");
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
