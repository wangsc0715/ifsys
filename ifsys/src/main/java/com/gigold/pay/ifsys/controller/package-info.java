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
package com.gigold.pay.ifsys.controller;

class CodeItem {

	// 失败
	public static final String IF_FAILURE = "D0000";
	// 用户名或密码不能为空
	public static final String USER_PASS_ERROR = "D1001";

	// 返回码不能为空
	public static final String RSP_CODE_FAILURE = "D0002";
	// 返回码描述不能为空
	public static final String RSP_CODE_DESC_FAILURE = "D0003";
	// 所属接口ID不能为空
	public static final String IF_ID_FAILURE = "D0004";
	// ID不能为空
	public static final String ID_FAILURE = "D0005";
	// 关注者调用信息不能为空
    public static final String REMARK_FAILURE = "D0006";
    //被关注接口ID不能为空
    public static final String FLLOW_IF_ID_FAILURE = "D0007";
    //系统ID不能为空
    public static final String SYS_ID_FAILURE = "D0008";
    //请检查必填项是否已录入
    public static final String NEDD_ITEM_FAILURE = "D0009";
    //参数不合法
    public static final String INVAILD_PARM_FAILURE = "D0010";
}