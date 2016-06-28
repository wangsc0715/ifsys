/**
 * Title: package-info.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
/**
 * Title: package-info<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author Devin
 * @date 2015年9月16日下午6:12:27
 *
 */
package com.gigold.pay.autotest.controller;

class CodeItem {

	// 调用失败
	public static final String FAILURE = "A0000";
	// 创建BO失败
	public static final String CREATE_BO_FAILURE = "A1000";
	// 期望返回值不能为空
	public static final String RETURN_CODE_IS_NULL = "A1001";
	// 期望请求报文不能为空
	public static final String REQ_JSON_IS_NULL = "A1002";

	// 所属接口ID不能为空
	public static final String IF_ID_FAILURE = "A0004";
	// 返回码描述不能为空
	public static final String RSP_CODE_DESC_FAILURE = "D0003";
	// 返回码不能为空
	public static final String RSP_CODE_FAILURE = "D0002";
}