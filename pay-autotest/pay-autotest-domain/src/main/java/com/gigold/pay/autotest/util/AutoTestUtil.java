/**
 * Title: AutoTestUtil.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2016<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.util;

import com.gigold.pay.framework.util.common.StringUtil;

import net.sf.json.JSONObject;

/**
 * Title: AutoTestUtil<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2016年1月7日下午2:50:03
 *
 */
public class AutoTestUtil {
    /**
     * 
     * Title: proTestData<br/>
     * Description: 根据生成规则编号、长度约束 产生测试数据<br/>
     * @author xiebin
     * @date 2016年1月7日下午3:39:50
     *
     * @param reg
     * @param length
     * @return
     */
	public static String proTestDataByReg(String reg,String length){
		//规则1:生成随即字符串
		if("1".equals(reg)){
			
		}
		return null;
	}
	/**
	 * 
	 * Title: proTestDataByRspBody<br/>
	 * Description: <br/>
	 * @author xiebin
	 * @date 2016年1月7日下午5:32:47
	 *
	 * @param postData
	 * @param refjson
	 * @param rspBody
	 * @return
	 */
	public static String proTestDataByRspBody(String postData,String refjson,String rspBody){
		if(StringUtil.isBlank(refjson)){
			return null;
		}
		if(StringUtil.isBlank(rspBody)){
			return null;
		}
		String []refFields=refjson.split(",\\s*");
		JSONObject jsObj=JSONObject.fromObject(rspBody);
		for(String refFied:refFields){
			String paths[]=refFied.split("\\.");
			JSONObject fieldObj=jsObj;
			String fieldName="";
			for( String path:paths){
				fieldObj=fieldObj.getJSONObject(path);
				fieldName=path;
			}
			postData.replaceAll("#{"+fieldName+"}", fieldObj.toString());
			
		}
		return postData;
	}
	
	
}
