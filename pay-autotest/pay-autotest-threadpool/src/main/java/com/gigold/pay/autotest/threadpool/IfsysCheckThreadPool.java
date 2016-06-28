/**
 * Title: SimpleThreadPool.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.annotation.IfSysMockHistoryAnnotation;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.jrn.JrnGeneratorService;
import com.gigold.pay.autotest.resulte.TestResulteData;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.InterFaceService;
import com.gigold.pay.framework.core.Domain;
import com.github.pagehelper.PageInfo;
/**
 * Title: SimpleThreadPool<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月27日下午5:46:16
 *
 */
@Service
public class IfsysCheckThreadPool extends Domain {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	IfSysMockService ifSysMockService;
	@Autowired
	InterFaceService interFaceService;
	// 获取核心数
	private static final int CPUCORECOUNT = Runtime.getRuntime().availableProcessors();
	@IfSysMockHistoryAnnotation("记录测试历史")
	public TestResulteData execute() {
	 ExecutorService executor = Executors.newFixedThreadPool(CPUCORECOUNT*2 + 1);
		// 测试数据
		TestResulteData testResulteData = new TestResulteData();
		// 当前页
		int curPageNum = 1;
		// 总页数
		int pages = 1;
		while (curPageNum <= pages) {
			PageInfo<InterFaceInfo> pageInfo = interFaceService.getAllIfSys(curPageNum);
			List<InterFaceInfo> ifsyslist = pageInfo.getList();
			// 创建线程
			Runnable worker = new CheckThread(ifSysMockService, ifsyslist);
			executor.execute(worker);
			pages = pageInfo.getPages();
			curPageNum++;
		}
		executor.shutdown();
		while (!executor.isTerminated()) { //
		}

		return testResulteData;
	}

}