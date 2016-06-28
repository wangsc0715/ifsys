/**
 * Title: AutoTestUtil.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2016<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

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
	
	
		private static final String cellStart[]=new String[]{
				//移动
				"134",
				"135",
				"136",
				"137",
				"138",
				"139",
				"147",
				"150",
				"151",
				"152",
				"157",
				"158",
				"159",
				"182",
				"187",
				"188",
				//联通
				"130",
				"131",
				"132",
				"155",
				"156",
				"185",
				"186",
				"186",
				"186",
                //电信
				"133",
				"153",
				"180",
				"189"
		};
	
	
	
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
		String regStr="";
		//默认生成六位
		int len=6;
		try{
			len=Integer.parseInt(length);
		}catch(Exception e){
			len=6;
		}
		
		switch (reg) {
		case "1" :
			//纯数字字符串
			regStr=RandomStringUtils.randomNumeric(len);
			break;
		case "2" :
			//生成符合邮箱规则的字符串 不保证其真实性
			//10为随随机字符@3位随机字符.com
			regStr=	RandomStringUtils.randomAlphanumeric(10)+"@"+RandomStringUtils.randomAlphanumeric(3)+".com";
			break;
		case "3" :
			//生成手机号
			int index=ramdoMuberRanger(1,27);
			regStr=cellStart[index]+RandomStringUtils.randomNumeric(8);
			break;
		case "4" :
			//生成随机中文字符串
			regStr=getRandomJianHan(len);
			break;
		default:
			//生成随机字符串 数字字母组合
			regStr=RandomStringUtils.randomAlphabetic(len);
			regStr=regStr.toLowerCase();
		}
		
			
	
		return regStr;
	}
	
	
	/**
	 * 
	 * Title: getRandomJianHan<br/>
	 * Description: 获取指定长度随机简体中文<br/>
	 * @author xiebin
	 * @date 2016年1月8日上午10:06:09
	 *
	 * @param len
	 * @return
	 */
    public static String getRandomJianHan(int len)
    {
        String ret="";
          for(int i=0;i<len;i++){
              String str = null;
              int hightPos, lowPos; // 定义高低位
              Random random = new Random();
              hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
              lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
              byte[] b = new byte[2];
              b[0] = (new Integer(hightPos).byteValue());
              b[1] = (new Integer(lowPos).byteValue());
              try
              {
                  str = new String(b, "GBk"); //转成中文
              }
              catch (UnsupportedEncodingException ex)
              {
                  ex.printStackTrace();
              }
               ret+=str;
          }
      return ret;
    }
    
	/**
	 * 
	 * Title: ramdoMuberRanger<br/>
	 * Description: 生成某个范围内的随机数字 [min,max]<br/>
	 * @author xiebin
	 * @date 2016年1月8日上午9:56:57
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static int ramdoMuberRanger(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
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
