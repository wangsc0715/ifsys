package com.gigold.pay.autotest.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gigold.pay.autotest.bo.IfSysMockResponse;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.core.Domain;

/**
 * 
 * Title: HttpClientService<br/>
 * Description: 调用汇添富接口<br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月10日下午4:54:14
 *
 */
@Service
public class HttpClientService extends Domain{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	// 建立http连接超时，单位毫秒
	private static int CONNECT_TIMEOUT = 30 * 1000;
	private static int SO_TIMEOUT = 30 * 1000;
	private static String CHARSET="UTF-8";
	/**
	 * 
	 * Title: setTimeOut<br/>
	 * Description: 设置超时<br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月10日上午10:52:47
	 *
	 * @param httpclient
	 */
	public void setTimeOut(HttpClient httpclient) {
		// 请求超时
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
		// 读取超时
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

	}


	/**
	 * 设置默认请求头
	 * @param httpPost 请求
     */
	public void setHeader(HttpPost httpPost) {
		httpPost.setHeader("accept", "*/*");
		httpPost.setHeader("connection", "Keep-Alive");
		httpPost.setHeader("Content-Type", "application/json");
	}

	/**
	 * 设置自定义请求头
	 * @param httpPost 请求
	 * @param headMap 补充请求头
     */
	public void setHeader(HttpPost httpPost, Map<String,String> headMap) {
		httpPost.setHeader("accept", "*/*");
		httpPost.setHeader("connection", "Keep-Alive");
		httpPost.setHeader("Content-Type", "application/json");
		for(String head:headMap.keySet()){
			httpPost.setHeader(head,headMap.get(head));
		}
	}

	/**
	 * 请求发送
	 * @param url 请求地址
	 * @param postData 请求包体
	 * @param cookieStore cookie信息
	 * @param extraHeader 额外的包头信息
	 * @return 返回结果
     * @throws Exception
     */
	public IfSysMockResponse httpPost(String url, String postData, CookieStore cookieStore, Map<String,String> extraHeader) throws Exception{
		String responseData = "";
		DefaultHttpClient httpclient = getHttpClient();
		List<Cookie> clist= cookieStore.getCookies();
		for(Cookie c:clist ){
			BasicClientCookie bc=(BasicClientCookie)c;
			bc.setPath("/");
		}
		//设置cookies
		httpclient.setCookieStore(cookieStore);
		HttpPost httppost = createPostMethed(url);
		// 设置超时
		setTimeOut(httpclient);
		// 设置请求头
		setHeader(httppost,extraHeader);


		try {
			setRequestParams(httppost, postData);
		} catch (UnsupportedEncodingException e1) {
			debug("请求参数中存在非法字符");
			e1.printStackTrace();
		}

		IfSysMockResponse ifSysMockResponse = new IfSysMockResponse();

		try {
			HttpResponse response = httpclient.execute(httppost);
			//获取cookies
			cookieStore=httpclient.getCookieStore();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				/* 读返回数据 */
				responseData = EntityUtils.toString(response.getEntity(), CHARSET);
				ifSysMockResponse.setResponseStr(responseData);
				/* 读取返回头 */
				Header[] headers = response.getAllHeaders();
				// 入库返回头
				ifSysMockResponse.setHeaders(arrHeadToMap(headers));
			} else {
				debug("服务器响应失败 : 返回状态" + statusCode);
			}
		} catch (IOException e) {
			debug("服务器响应失败");
		}

		// 获取所有请求头
		Header[] requestHeaders = httppost.getAllHeaders();
		// 记录请求头
		ifSysMockResponse.setRequestHeaders(arrHeadToMap(requestHeaders));

		return ifSysMockResponse;
	}


	public HttpPost createPostMethed(String url) {
		return new HttpPost(url);
	}

	public HttpGet createGettMethed(String url) {
		return new HttpGet(url);
	}

	public DefaultHttpClient getHttpClient() {
		return new DefaultHttpClient();
	}

	/**
	 * 
	 * Title: setProxy<br/>
	 * 有需要则设置代理: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月6日下午1:41:54
	 *
	 * @param httpclient

	 */
	public void setProxy(DefaultHttpClient httpclient, String proxyHost, int proxyPort, String userName,
			String password) {
		httpclient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort),
				new UsernamePasswordCredentials(userName, password));
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);
		httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);

	}

	/**
	 * 
	 * Title: setRequestParams<br/>
	 * 设置请求参数 和请求头: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月6日下午1:41:18
	 *
	 * @param httppost
	 * @throws UnsupportedEncodingException
	 */
	public void setRequestParams(HttpPost httppost, String requestData) throws UnsupportedEncodingException {

		StringEntity entity = new StringEntity(requestData, CHARSET);// 解决中文乱码问题
		entity.setContentEncoding(CHARSET);
		httppost.setEntity(entity);
	}

	/**
	 * 将数组类型的head转换为Map类型
	 * @param headers 数组类型head
	 * @return map类型head
     */
	public static Map<String, String> arrHeadToMap(Header [] headers){
		Map<String,String> _headrs = new HashMap<>();
		if (headers==null || headers.length<=0)return _headrs;
		for (Header header : headers) {
			_headrs.put(header.getName(), header.getValue());
		}
		return _headrs;
	}
	
	
	

}
