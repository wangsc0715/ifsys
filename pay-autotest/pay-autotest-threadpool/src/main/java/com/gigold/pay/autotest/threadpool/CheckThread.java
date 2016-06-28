/**
 * Title: WorkerThread.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.threadpool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.bo.ReturnCode;
import com.gigold.pay.autotest.service.IfSysAutoTestService;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.InterFaceFieldService;
import com.gigold.pay.autotest.service.RetrunCodeService;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.util.common.StringUtil;

/**
 * Title: CheckThread<br/>
 * Description: 用来检测接口的线程<br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月27日下午5:45:51
 *
 */
public class CheckThread implements Runnable {
	InterFaceFieldService interFaceFieldService;
	IfSysMockService ifSysMockService;
	IfSysAutoTestService ifSysAutoTestService;
	RetrunCodeService retrunCodeService;
	// 存放接口列表
	private List<InterFaceInfo> ifsyslist;

	public CheckThread(IfSysMockService ifSysMockService, List<InterFaceInfo> ifsyslist) {
		this.ifsyslist = ifsyslist;
		this.ifSysMockService = ifSysMockService;
		ifSysAutoTestService = (IfSysAutoTestService) SpringContextHolder.getBean(IfSysAutoTestService.class);
		this.retrunCodeService = (RetrunCodeService) SpringContextHolder.getBean(RetrunCodeService.class);
		this.interFaceFieldService = (InterFaceFieldService) SpringContextHolder.getBean(InterFaceFieldService.class);
	}

	@Override
	public void run() {
		processCommand();
	}

	// 处理接口测试
	private void processCommand() {
		// 写检测接口的代码
		for (InterFaceInfo interFaceInfo : ifsyslist) {
			//第一步 初始化接口的测试数据
			//initData(interFaceInfo);
			//第二步读取接口的测试数据信息 调用httpclient发送请求
			handlerIfSys(interFaceInfo);
		}
	}

	/**
	 * 
	 * Title: handlerIfSys<br/>
	 * Description: 测试接口<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午11:42:32
	 *
	 * @param interFaceInfo
	 */
	public void handlerIfSys(InterFaceInfo interFaceInfo) {
		int ifId = interFaceInfo.getId();
		//根据接口ID获取接口所有的测试数据
		List<IfSysMock> mockList = ifSysMockService.getMockInfoByIfId(ifId);
		interFaceInfo.setMockList(mockList);
		ifSysAutoTestService.autoTest(interFaceInfo);
	}

	/**
	 * 
	 * Title: initData<br/>
	 * Description: 初始化测试数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午11:40:33
	 *
	 * @param interFaceInfo
	 */
	public void initData(InterFaceInfo interFaceInfo) {
		// 获取接口请求字段的JSON展示字符串
		InterFaceField interFaceField = (InterFaceField) SpringContextHolder.getBean(InterFaceField.class);
		interFaceField.setIfId(interFaceInfo.getId());
		interFaceField.setFieldFlag("1");
		String reqJson = interFaceFieldService.getJsonStr(interFaceField);
		if (StringUtil.isNotBlank(reqJson)) {
			interFaceInfo.setReqJsonStr(reqJson);
		}
		// 获取接口响应字段的JSON展示字符串
		interFaceField.setFieldFlag("2");
		String rspJson = interFaceFieldService.getJsonStr(interFaceField);
		if (StringUtil.isNotBlank(rspJson)) {
			interFaceInfo.setRspJsonStr(rspJson);
		}
		List<ReturnCode> returnList = retrunCodeService.getReturnCodeByIfId(interFaceInfo.getId());
		// 遍历返回码 更新测试数据
		for (ReturnCode rscdObj : returnList) {
			IfSysMock ifSysMock = (IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
			ifSysMock.setIfId(interFaceInfo.getId());
			ifSysMock.setRspCodeId(rscdObj.getId());
			ifSysMock.setRequestJson(interFaceInfo.getReqJsonStr());
			ifSysMock.setResponseJson(interFaceInfo.getRspJsonStr());
			ifSysMockService.createIfSysMock(ifSysMock);
		}

	}

}
