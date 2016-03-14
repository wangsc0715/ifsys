/**
 * Title: IfSysAutoTestService.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gigold.pay.autotest.bo.IfSysFeildRefer;
import com.gigold.pay.autotest.dao.IfSysReferDAO;
import com.gigold.pay.autotest.datamaker.IdCardNo;
import com.gigold.pay.autotest.datamaker.PhoneNo;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.autotest.bo.InterFaceInfo;
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


	public void writeBackContent(IfSysMock mock, String responseJson) {

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
		ifSysMockService.writeBackRealRsp(mock,testResulte,responseJson,relRspCode);
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
	 * 
	 * Title: autoTest<br/>
	 * Description: 自动化测试核心代码 测试用例之间的依赖<b/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午10:50:15
	 *
	 * @param interFaceInfo
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
			// 1、获取该测试用例调用时依赖的其他用例的调用列表
			List<IfSysMock> invokerOrderList = new ArrayList<IfSysMock>();
			// 放到第一位
			invokerOrderList.add(mock);
			invokerOrder(invokerOrderList, mock.getId());
			// 存放依赖的cookies
			CookieStore cookieStore=new BasicCookieStore();
			// 2、 按照调用序号依次调用被依赖测试用例
			invokRefCase(invokerOrderList,cookieStore);
			// 3、最后调用目标接口
			//invokCase(mock,cookieStore);

		}

	}

	/**
	 * 
	 * Title: invokRefCase<br/>
	 * Description:按调用序号依次调用被依赖测试用例 <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月22日下午4:51:14
	 *
	 * @param invokerOrderList
	 */
	public void invokRefCase(List<IfSysMock> invokerOrderList,CookieStore cookieStore) {
		/**
		 * *** 替换接口前后依赖的字段
		 *
		 * 1.定义调用列表中所有mock返回的结果
		 */
		Map<Integer,String> allRespMap = new HashMap<>();// 临时变量

		for (int i = invokerOrderList.size() - 1; i >= 0; i--) {
			IfSysMock refmock = invokerOrderList.get(i);


			/**
			 * 2.根据1中返回的结果,以及当前接口所依赖的返回,替换请求报文
			 */
			String postData = refmock.getRequestJson();
			if(StringUtil.isBlank(postData)){
				debug("用例请求报文为空----"+refmock.getCaseName());
				return;
			}
			// 1.获取当前接口所依赖的所有字段,
			List<IfSysFeildRefer> referFields=ifSysReferService.queryReferFields(refmock.getFollowId());
			for(IfSysFeildRefer referField :referFields){
				//2.根据返回字段,替换当前报文; 别名 => mockid => feild 依次遍历 allRespMap
				int nowMockId = referField.getRef_mock_id(); // 当前用例数据的id
				String path = referField.getRef_feild(); // 当前用例数据所依赖的域

				// 根据每一个依赖的用例,在临时变量中查询出记录的返回的json
				String backJson = allRespMap.get(nowMockId);
				// 根据每个依赖的域,在返回的json中查询出值
				String backField = gatJsonValByPath(backJson,path);
				postData.replace(referField.getAlias() ,backField);// 替换别名代表的值
			}

			/**
			 * 额外:替换常量如:唯一手机号, 唯一邮箱号, 唯一身份证号, 当前日期 等
			 */
			try {

				String str_phone = "#{CONST-FRESH-PHONE-NO}";
				String str_idcard = "#{CONST-FRESH-IDCARD-NO}";
				String str_nowdata = "#{CONST-NOW-DATA}";
				// 替换手机号
				if(postData.indexOf(str_phone)>=0){ // 存在则替换
					postData = postData.replace(str_phone, PhoneNo.getUnusedPhoneNo());
					PhoneNo.renewPhone();
				}

				// 替换身份证号
				if(postData.indexOf(str_idcard)>=0){ // 不存在则不替换
					String idcardNo = IdCardNo.getUnusedNo();
					postData.replace(str_idcard, idcardNo);
					IdCardNo.disableNo(idcardNo);
				}

				// 替换当前日期
				if(postData.indexOf(str_nowdata)>=0){
					// do something
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("替换后的最终postData=>>"+postData);



			// 定义返回
			String responseJson = "";
			try {
				/**
				 * 3.发送http请求
				 */
				responseJson=httpClientService.httpPost(refmock.getAddressUrl(), postData,cookieStore);
				/**
				 * 4 在次把本次结果存在返回结果内返回存在结果里
				 */
				allRespMap.put(refmock.getId(),responseJson);

			} catch (Exception e) {
				debug("调用失败   调用被依赖测试用例过程中出现异常");
			}finally {
				writeBackRefCaseContent(refmock,responseJson);
			}

		}
	}

	/**
	 * 
	 * Title: invokCase<br/>
	 * Description: 调用目标测试用例<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月22日下午4:49:39
	 *
	 * @param mock
	 */
	public void invokCase(IfSysMock mock,CookieStore cookieStore) {
		// 期望请求报文
		String postData = mock.getRequestJson();
		if(StringUtil.isBlank(postData)){
			debug("用例请求报文为空----"+mock.getCaseName());
			return;
		}
		//先处理请求报文
		//postData=preHanlderReuestBody(postData,mock);
		// 实际请求后，返回的报文（返回码和返回实体）
		String responseJson = "";
		try {
			responseJson = httpClientService.httpPost(mock.getAddressUrl(), postData,cookieStore);
		} catch (Exception e) {
			responseJson = "";
			debug("调用失败 调用目标测试用例过程中出现异常:"+e.getMessage());
		}finally {
			// 实际结果回写
			writeBackContent(mock, responseJson);
		}

	}

	/**
	 * 
	 * Title: preHanlderReuestBody<br/>
	 * Description: 处理请求报文 前置<br/>
	 * @author xiebin
	 * @date 2016年1月7日下午5:52:28
	 *
	 * @param postData
	 * @param mock
	 * @return
	 */
	public String preHanlderReuestBody(String postData, IfSysMock mock) {
		// 如果需要的话需要先完善请求报文
		if (!StringUtil.isBlank(mock.getCheckJson())) {
			// 1、需要自动生成数据
			String checkJoson = mock.getCheckJson();
			JSONObject jo = JSONObject.fromObject(checkJoson);
			JSONArray jsArry = jo.getJSONArray("caseInfo");
			for (int j = 0; j < jsArry.size(); j++) {
				JSONObject joInfo = jsArry.getJSONObject(j);
				String name = joInfo.getString("name");
				String reg = joInfo.getString("reg");
				String length = joInfo.getString("length");
				// 调用公用方法 根据reg length等条件生成数据
				String fieldvalue = AutoTestUtil.proTestDataByReg(reg, length);
				postData = postData.replace("#{" + name + "}", fieldvalue);
			}
		}
		// 2、需要依赖其他用例生成请求报文的
		// 获取该用例所有被依赖的用例的信息
		List<IfSysMock> refMockList = ifSysMockService.getRefMockInfoByMockId(mock.getId());
		for (IfSysMock re : refMockList) {
			String refjson = re.getRspRefJson();
			if (!StringUtil.isBlank(refjson)) {
				String rspBody = re.getRealResponseJson();
				// 根据refjson 替换 postData
				postData = AutoTestUtil.proTestDataByRspBody(postData, refjson, rspBody);
			}
		}

		return postData;
	}
	
	
	
	/**
	 * 
	 * Title: autoTest<br/>
	 * Description: 自动化测试核心代码 接口哦间的依赖<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午10:50:15
	 *
	 * @param interFaceInfo
	 */
	// public void autoTest(InterFaceInfo interFaceInfo) {
	// //设置接口访问的完整地址
	// String url = getAddressUrl(interFaceInfo.getAddressUrl(),
	// interFaceInfo.getIfUrl());
	// // 1、获取接口调用时依赖的接口调用列表
	// List<IfSysMock> invokerOrderList = new ArrayList<IfSysMock>();
	// invokerOrder(invokerOrderList, interFaceInfo.getId());
	// // 2、依次调用被依赖的接口
	// for (int i = invokerOrderList.size() - 1; i >= 0; i--) {
	// IfSysMock mock = invokerOrderList.get(i);
	// /**
	// * 调用HTTP请求
	// */
	// // 期望请求报文
	// String postData = mock.getRequestJson();
	// // 实际请求后，返回的报文（返回码和返回实体）
	// try{
	// httpClientService.httpPost(mock.getAddressUrl(), postData);
	// }catch(Exception e){
	// debug("调用失败");
	// }
	//
	// }
	// // 3、最后调用目标接口
	// for (IfSysMock mock : interFaceInfo.getMockList()) {
	// /**
	// * 调用HTTP请求
	// */
	// // 期望请求报文
	// String postData = mock.getRequestJson();
	// // 实际请求后，返回的报文（返回码和返回实体）
	// String responseJson="";
	// try{
	// responseJson=httpClientService.httpPost(url, postData);
	// }catch(Exception e){
	// responseJson="";
	// debug("调用失败");
	// }
	// // 实际结果回写
	// writeBackContent(mock, responseJson);
	//
	// }
	// }
	//

	/**
	 * 
	 * Title: invokerOrder<br/>
	 * Description: 获取该测试用例调用时依赖的其他用例的调用列表<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月22日下午4:14:44
	 *
	 * @param invokerOrderList
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
				mock.setAddressUrl(url);
				invokerOrderList.add(mock);
			}
			
			// 如果被依赖测试用例还依赖其他测试用例
			invokerOrder(invokerOrderList, refer.getRefMockId());
		}
	}

	/**
	 * 
	 * Title: invokerOrder<br/>
	 * Description: 获取接口调用时依赖的接口调用列表 确定接口调用顺序<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日下午4:06:23
	 *
	 * @param invokerOrderList
	 * @param ifId
	 */
	// public void invokerOrder(List<IfSysMock> invokerOrderList, int ifId) {
	// // 获取被测接口依赖其他接口的列表
	// List<IfSysRefer> referList = ifSysReferService.getReferList(ifId);
	// // 如果有依赖 遍历依赖 列表
	// for (int i = referList.size() - 1; i >= 0; i--) {
	// IfSysRefer refer = referList.get(i);
	// // 获取被依赖的接口
	// IfSysMock mock = ifSysMockService.getReferByIfId(refer.getRefId());
	// String url = getAddressUrl(mock.getAddressUrl(), mock.getIfURL());
	// mock.setAddressUrl(url);
	// invokerOrderList.add(mock);
	// invokerOrder(invokerOrderList, mock.getIfId());
	// }
	// }

	/**
	 * 
	 * Title: getAddressUrl<br/>
	 * Description: 获取接口完整地址<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月10日上午10:43:23
	 *
	 * @return
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
			if(json.get(path[i]) instanceof JSONArray){
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
					return json.get(path[i]).toString();
				}
				json = JSONObject.fromObject(json.get(path[i]));
			}
		}
		return json.toString();
	}

	public static void main(String[] args){
		String json = "{\n" +
				"    \"key1\": \"val1\",\n" +
				"    \"key2\": [\n" +
				"        {\n" +
				"            \"key21\": \"val21\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"key22\": \"val22\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";
		String key  = "key2[0].key21";
		System.out.println(gatJsonValByPath(json,key));
	}
}
