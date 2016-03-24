/**
 * Title: IfSysAutoTestService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gigold.pay.autotest.bo.*;
import com.gigold.pay.autotest.dao.IfSysReferDAO;
import com.gigold.pay.autotest.datamaker.BankCardNo;
import com.gigold.pay.autotest.datamaker.HexNo;
import com.gigold.pay.autotest.datamaker.IdCardNo;
import com.gigold.pay.autotest.datamaker.PhoneNo;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.httpclient.HttpClientService;
import com.gigold.pay.autotest.util.AutoTestUtil;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.util.common.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Title: IfSysAutoTestService<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日下午4:56:57
 *
 */
@Service
public class IfSysAutoTestService extends Domain {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Autowired
	HttpClientService httpClientService;
	@Autowired
	IfSysMockService ifSysMockService;
	@Autowired
	IfSysReferService ifSysReferService;


	public void writeBackContent(IfSysMock mock) {
		// 获得真实返回json
		String responseJson = mock.getRealResponseJson();

		if(mock.getRspCode().equals("NOCODE")){
			/**
			 * 如果没有定义返回码,则需要用其他的验证方式,字段或者什么其他
			 * 如果没有定义返回码,则需要用其他的验证方式,字段或者什么其他
			 * 如果没有定义返回码,则需要用其他的验证方式,字段或者什么其他
			 * 如果没有定义返回码,则需要用其他的验证方式,字段或者什么其他
			 * 如果没有定义返回码,则需要用其他的验证方式,字段或者什么其他
			 * 如果没有定义返回码,则需要用其他的验证方式,字段或者什么其他
			 */
			mock.setTestResult("1");
			mock.setRealRspCode("没有返回码");
			ifSysMockService.writeBackRealRsp(mock);
		}else{
			// 否则使用返回码验证
			JSONObject jsonObject;
			try {
				jsonObject = JSONObject.fromObject(responseJson);
			} catch (Exception e) {
				jsonObject = new JSONObject();
			}
			String relRspCode = String.valueOf(jsonObject.get("rspCd"));
			String testResulte;
			// 1-正常 0-失败 -1-请求或响应存在其他异常
			if (relRspCode.equals(mock.getRspCode())) {// 返回码与预期一致
				testResulte ="1";
			} else if (StringUtil.isNotEmpty(relRspCode)&&(!relRspCode.equals("null"))) {// 返回码与预期不一致,但不为空
				testResulte ="0";
			} else { // 返回码与预期不一致,或为空,或为其他
				testResulte="-1";
			}
			mock.setTestResult(testResulte);
			mock.setRealRspCode(relRspCode);
			ifSysMockService.writeBackRealRsp(mock);
		}

	}
	/**
	 * 
	 * Title: getTestAfterMock<br/>
	 * Description: <br/>
	 * @author xiebin
	 * @date 2016年1月7日下午6:13:20
	 *
	 * @param responseJson
	 * @param mock
	 * @return
	 */
	public IfSysMock getTestAfterMock(String responseJson,IfSysMock mock){
		JSONObject jsonObject;
		try {
			jsonObject = JSONObject.fromObject(responseJson);
		} catch (Exception e) {
			jsonObject = new JSONObject();
		}
		String relRspCode = String.valueOf(jsonObject.get("rspCd"));
		String testResulte;
		// 1-正常 0-失败 -1-请求或响应存在其他异常
		if (relRspCode.equals(mock.getRspCode())) {// 返回码与预期一致
			testResulte ="1";
		} else if (StringUtil.isNotEmpty(relRspCode)&&(!relRspCode.equals("null"))) {// 返回码与预期不一致,但不为空
			testResulte ="0";
		} else { // 返回码与预期不一致,或为空,或为其他
			testResulte="-1";
		}
		mock.setTestResult(testResulte);
		mock.setRealResponseJson(responseJson);
		mock.setRspCode(relRspCode);
		//被依赖的用例需要标识为 N
		mock.setIsCase("N");
		return mock;
	}

	/**
	 * 
	 * Title: writeBackRefCaseContent<br/>
	 * Description: 回写被依赖的用例测试结果<br/>
	 * @author xiebin
	 * @date 2016年1月7日下午6:01:15
	 *
	 * @param mock
	 * @param responseJson
	 */
	public void writeBackRefCaseContent(IfSysMock mock, String responseJson) {
       try{
		ifSysMockService.writeBackRefCase(getTestAfterMock(responseJson,mock));
       }catch(Exception e){
    	   debug("调用 writeBackRefCase 异常"+e.getMessage());
       }
	}
	

	/**
	 * 自动化测试核心代码 测试用例之间的依赖
	 * @param interFaceInfo 每页接口用例信息
	 */
	public void autoTest(InterFaceInfo interFaceInfo) {
		// 获取接口访问的完整地址
		String url = getAddressUrl(interFaceInfo.getAddressUrl(), interFaceInfo.getIfUrl());
		// 调用接口所有的测试用例
		for (IfSysMock mock : interFaceInfo.getMockList()) {
			//判断用例是否有被依赖
			List<IfSysRefer> listRef=ifSysReferService.getReferByRefMockId(mock.getId());
			//如果用例被其他用例依赖了 则进入下一次循环 
			if(listRef!=null&&listRef.size()!=0){
				continue;
			}
			// 设置接口访问的完整地址
			mock.setAddressUrl(url);
			// 设置接口访问的主机地址
			mock.setSysUrl(interFaceInfo.getAddressUrl());
			// 1、获取该测试用例调用时依赖的其他用例的调用列表
			List<IfSysMock> invokerOrderList = new ArrayList<IfSysMock>();
			// 第一位放入目标接口
			invokerOrderList.add(mock);
			// 然后加入依赖用例
			invokerOrder(invokerOrderList, mock.getId());
			// 存放依赖的cookies
			CookieStore cookieStore=new BasicCookieStore();
			// 2、 按照调用序号依次调用被依赖测试用例
			invokRefCase(invokerOrderList,cookieStore);
		}

	}


	/**
	 * 按调用序号依次调用被依赖测试用例
	 * @param invokerOrderList 用例调用列表
	 * @param cookieStore 用来维持cookie的变量
     */
	public void invokRefCase(List<IfSysMock> invokerOrderList,CookieStore cookieStore) {
		/**
		 * 初始化持续变量
		 */
		Map<Integer,String> allRespMap = new HashMap<>();// 临时变量,存放各个依赖用例的包体返回
		Map<Integer,Map> allHeadMap = new HashMap<>();// 临时变量,存放各个依赖用例的头部
		Map<String,String> replacedStrs = new HashMap<>();// 已替换变量,存放占位符替换的变量
		Map<String, String> responseHead = null; // 接口返回头信息
		String responseJson = ""; // 接口返回字符串

		for (int i = invokerOrderList.size() - 1; i >= 0; i--) {
			IfSysMock refmock = invokerOrderList.get(i);
			// 替换请求报文
			String postData = refmock.getRequestJson();
			if(StringUtil.isBlank(postData)){
				debug("用例请求报文为空----"+refmock.getCaseName());
				return;
			}

			/**
			 * 请求组装
			 */
			// 替换占位符
			postData = replaceHolder(postData,refmock.getId(),allRespMap,allHeadMap, replacedStrs);
			// 回写真实请求json
			refmock.setRealRequestJson(postData);

			/**
			 * 头部组装
			 */
			//若存在头部依赖
			Map<String,String> extraHeaders = new HashMap<>();

			/**
			 * 地址组装
			 */
			// 替换占位符
			String realddressUrl = refmock.getRequestPath();
			if(StringUtil.isBlank(realddressUrl)){
				// 若真实地址不存在则用接口地址
				realddressUrl = refmock.getAddressUrl();
			}else{
				// 若存在则直接使用,先要替换占位符
				realddressUrl = replaceHolder(realddressUrl,refmock.getId(),allRespMap,allHeadMap,replacedStrs);
				realddressUrl = getAddressUrl(refmock.getSysUrl(),realddressUrl);
			}
			// 回写真实请求地址
			refmock.setRealRequestPath(realddressUrl);


			/**
			 * 发送请求
			 */
			try {
				// 发送请求
				IfSysMockResponse ifSysMockResponse = httpClientService.httpPost(realddressUrl, postData,cookieStore,extraHeaders);
				if(ifSysMockResponse==null)throw new Exception("请求返回null");
				responseJson = ifSysMockResponse.getResponseStr();
				responseHead = ifSysMockResponse.getHeaders();

				// 拿到真实请求头
				Map<String,String> reqHeaders = ifSysMockResponse.getRequestHeaders();
				List<Cookie> cks = cookieStore.getCookies();
				// 请求头中加入cookie
				reqHeaders.put("Cookie",stringfiyCookiesList(cks));
				// 回写真实请求头
				refmock.setRealRequestHead(mapHeadToStr(reqHeaders));

				// 记录当次请求结果
				allRespMap.put(refmock.getId(),responseJson);// 返回json
				allHeadMap.put(refmock.getId(),responseHead);// 返回头
			} catch (Exception e) {
				debug("调用失败   调用被依赖测试用例过程中出现异常");
			}finally {

				// 回写真实返回json
				if(StringUtil.isNotEmpty(responseJson))refmock.setRealResponseJson(responseJson);
				// 回写真实返回头
				if(responseHead!=null){refmock.setRealResponseHead(mapHeadToStr(responseHead));}
				writeBackContent(refmock);
			}

			/**
			 * 回调组装
			 */

			// 若是最后一个,并在回调sql则执行回调

			/**
			 * 若 i==0 则执行回调
			 */
		}
	}

	/**
	 * 替换接口占位符依赖
	 * @param requestStr 原始请求参数
	 * @param mockid 目标用例
	 * @param allRespMap 依赖用例的所有返回<用例id,用例返回字符串>
	 * @param allHeadMap 依赖用例的所有头部返回<用例id,用例头部列表>
	 * @param replacedStrs 依赖用例的所有返回<占位符,占位符取值>
     * @return 替换后的字符串
     */
	public String replaceHolder(String requestStr,int mockid,Map<Integer,String> allRespMap,Map<Integer,Map> allHeadMap,Map<String,String> replacedStrs){
		try {
			// 1.获取当前接口所依赖的所有字段,
			List<IfSysFeildRefer> referFields=ifSysReferService.queryReferFields(mockid);
			for(IfSysFeildRefer referField :referFields){
				//2.根据返回字段,替换当前报文; 别名|mockid|feild 依次遍历 allRespMap
				int nowMockId = referField.getRef_mock_id(); // 当前用例数据的id
				String path = referField.getRef_feild(); // 当前用例数据所依赖的域
				// 根据每一个依赖的用例,在临时变量中查询出记录的返回的json
				String backJson = allRespMap.get(nowMockId);
				// 根据每个依赖的域,在返回的json中查询出值
				String backField = gatJsonValByPath(backJson,path);
				if(requestStr.contains(referField.getAlias())){
					requestStr = requestStr.replace(referField.getAlias() ,backField);// 替换别名代表的值
					System.out.println(requestStr);
				}
			}


		/**
		 * 额外:替换常量如:唯一手机号, 唯一邮箱号, 唯一身份证号, 当前日期 等
		 */
			String str_phone = "#{CONST-FRESH-PHONE-NO}";
			String str_idcard = "#{CONST-FRESH-IDCARD-NO}";
			String str_nowdata = "#{CONST-NOW-DATA}";
			String str_hex_6 = "#{CONST-HEX-6}";
			String str_bankcard_no = "#{CONST-BANK-CARD-NO}";


			// 替换有效银行卡
			if(requestStr.contains(str_bankcard_no)){ // 存在则替换
				if(replacedStrs.containsKey(str_bankcard_no)){// 若整个会话中已经存过当前值则直接用
					requestStr = requestStr.replace(str_bankcard_no,replacedStrs.get(str_bankcard_no) );
				}else{// 否则新取一个,然后刷新列表
					// 取值
					String bankCardNo = BankCardNo.getUnusedNo();
					// 替换
					if(bankCardNo!=null)requestStr = requestStr.replace(str_bankcard_no,bankCardNo );
					// 刷新值
					BankCardNo.renewNo();
					// 入库值
					BankCardNo.addToAvalidList(bankCardNo,"接口系统:mock-"+String.valueOf(mockid));
					// 记录值
					replacedStrs.put(str_bankcard_no,bankCardNo);
				}
			}


			// 替换16进制数
			if(requestStr.contains(str_hex_6)){ // 存在则替换
				// 先看会话中是否已经存过了
				if(replacedStrs.containsKey(str_bankcard_no)){
					requestStr = requestStr.replace(str_hex_6,replacedStrs.get(str_hex_6) );
				}else{
					String hex = HexNo.getLastHexNo();
					requestStr = requestStr.replace(str_hex_6,hex );
					HexNo.renewNo();
					HexNo.disableNo(hex.trim(),"接口系统:mock-"+String.valueOf(mockid));
					replacedStrs.put(str_hex_6,hex);
				}
			}

			// 替换手机号
			if(requestStr.contains(str_phone)){ // 存在则替换
				if(replacedStrs.containsKey(str_phone)){
					requestStr = requestStr.replace(str_phone,replacedStrs.get(str_phone) );
				}else {
					String unUsedNo = PhoneNo.getUnusedPhoneNo();
					if(unUsedNo!=null)requestStr = requestStr.replace(str_phone, unUsedNo);
					PhoneNo.renewPhone();
					PhoneNo.addToAvalidList(unUsedNo, "接口系统:mock-" + String.valueOf(mockid));
					replacedStrs.put(str_phone,unUsedNo);

				}
			}

			// 替换身份证号
			if(requestStr.contains(str_idcard)){ // 不存在则不替换
				if(replacedStrs.containsKey(str_idcard)){
					requestStr = requestStr.replace(str_idcard,replacedStrs.get(str_idcard) );
				}else {
					String idcardNo = IdCardNo.getUnusedNo();
					if(idcardNo!=null)requestStr = requestStr.replace(str_idcard, idcardNo);
					IdCardNo.disableNo(idcardNo, "接口系统:mock-" + String.valueOf(mockid));
					replacedStrs.put(str_idcard,idcardNo);

				}
			}

			// 替换当前日期
			if(requestStr.contains(str_nowdata)){
				Format format = new SimpleDateFormat("yyyy-MM-dd");
				requestStr = requestStr.replace(str_nowdata,format.format(new Date()));
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestStr.trim();
	}



	/**
	 * 获取该测试用例调用时依赖的其他用例的调用列表
	 * @param invokerOrderList 接口调用列表
	 * @param mockId 目标接口ID
     */
	public void invokerOrder(List<IfSysMock> invokerOrderList, int mockId) {
		// 获取被测用例依赖其他用例的列表
		List<IfSysRefer> referList = ifSysReferService.getReferList(mockId);
		// 如果有依赖 遍历依赖 列表
		for (int i = referList.size() - 1; i >= 0; i--) {
			IfSysRefer refer = referList.get(i);
			// 获取被依赖的用例数据
			IfSysMock mock = ifSysMockService.getReferByIfId(refer.getRefMockId());
			if(mock!=null){
				String url = getAddressUrl(mock.getAddressUrl(), mock.getIfURL());
				mock.setSysUrl(mock.getAddressUrl());
				mock.setAddressUrl(url);
				invokerOrderList.add(mock);
			}
			
			// 如果被依赖测试用例还依赖其他测试用例
			invokerOrder(invokerOrderList, refer.getRefMockId());
		}
	}


	/**
	 * 获取接口完整地址
	 * @param url 系统地址
	 * @param action 接口path
     * @return 返回完整地址
     */
	public String getAddressUrl(String url, String action) {
		String addressUrl = "";
		if (StringUtil.isNotBlank(url) && StringUtil.isNotBlank(action)) {
			url=url.trim();
			action=action.trim();
			if (url.endsWith("/")) {
				if(action.startsWith("/")){
					addressUrl=url+action.substring(1);
				}else{
					addressUrl = url + action;
				}
				
			} else {
				if(action.startsWith("/")){
					addressUrl=url+action;
				}else{
					addressUrl = url + "/" + action;
				}
				
				
			}
		}
		return addressUrl;
	}

	/**
	 * 根据表达式 从json字符串中取值
	 * @param jsonString json字符串
	 * @param field 查找表达式
     * @return 返回字符串
     */
	public static String gatJsonValByPath(String jsonString,String field){
		JSONObject json;
		// 判断传入字符串是否为空
		if(jsonString==null||jsonString.isEmpty()||field.isEmpty())return "";
		try {
			json = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			json = new JSONObject();
		}

		// 替换数组[]为.
		field = field.replaceAll("\\[",".");
		field = field.replaceAll("]\\.",".");
		field = field.replaceAll("]",".");
		field = field.replaceAll("\\.$","");
		System.out.println(field);
		// 逐级查找path对应的值
		String[] path = field.split("\\.");
		for(int i = 0; i<path.length;i++){
			if(!json.isEmpty() && json.get(path[i]) instanceof JSONArray){
				JSONArray jsonArr = JSONArray.fromObject(json.get(path[i]));
				if(i>=path.length-1){
					// 若下一个位置在path[]中已经超标,
					// 则当前jsonArray对象已经是最后的位置,
					// 直接返回即可
					System.out.println("out");
					return jsonArr.toString();
				}else{
					int idxOfJsonArr = Integer.parseInt(path[i+1]); //path的下一个位置转为整型就是所需值的下标
					json=jsonArr.getJSONObject(idxOfJsonArr);
					i++;
				}
			}else{
				if(i>=path.length-1){
					if(json.get(path[i])!=null){
						return json.get(path[i]).toString();
					}else{
						return "";
					}
				}
				json = JSONObject.fromObject(json.get(path[i]));
			}
		}
		return json.toString();
	}

	/**
	 * 头信息转换成字符串
	 * @param headers map类型的头信息
	 * @return 字符串类型的头
     */
	public static String mapHeadToStr(Map<String,String> headers){
		if(headers!=null){
			String headStr = "";
			for(String ahead : headers.keySet()){
				headStr += ahead+":"+headers.get(ahead)+"\n";
			}
			return headStr;
		}else {
			return "";
		}
	}

	/**
	 * 头信息转 Map
	 * @param headers 字符串类型头信息
	 * @return map 类型头信息
     */
	public static Map<String,String> strHeadToMap(String headers){
		Map<String,String> headMap = new HashMap<>();
		if(StringUtil.isNotEmpty(headers)){
			String[] headArr = headers.trim().split("\n");
			for(String aheader:headArr){
				String key = aheader.substring(0,aheader.indexOf(":"));
				String val = aheader.substring(aheader.indexOf(":"));
				headMap.put(key,val);
			}
		}
		return headMap;
	}

	public static String stringfiyCookiesList(List<Cookie> cookieList){
		String cksstr = "";
		if(cookieList==null||cookieList.size()<=0)return cksstr;

		for(Cookie cookie : cookieList){
			cksstr +="; "+cookie.getName()+"="+cookie.getValue();
		}
		return cksstr.substring(1);
	}

}
