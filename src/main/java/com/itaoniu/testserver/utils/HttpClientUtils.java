package com.itaoniu.testserver.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author lihui
 *
 */
public class HttpClientUtils {

	public static final int connTimeout = 10000;
	public static final int readTimeout = 10000;
	public static final String charset = "UTF-8";
	private static HttpClient client = null;
	private static Header[] SetCookies =null;
	private static Header MODULUS =null;
	private static Header EXPONENT =null;

	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(128);
		cm.setDefaultMaxPerRoute(128);
		client = HttpClients.custom().setConnectionManager(cm).build();
	}
	
	public static Header[] getCookies(){
		return SetCookies;
	}
	
	public static Header getMODULUS(){
		return MODULUS;
	}
	
	public static Header getEXPONENT(){
		return EXPONENT;
	}
	

	public static String getUrlParams(Map<String, String> map, String valueEnc) {
		if (null == map || map.keySet().size() == 0) {
			return ("");
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : "";
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append("&");
			}
		}
		String strURL = "";
		strURL = url.toString();
		if ("&".equals("" + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}

		return (strURL);
	}

	public HttpEntity getFormParams(Map<String, String> params) {
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
			Set<Entry<String, String>> entrySet = params.entrySet();
			for (Entry<String, String> entry : entrySet) {
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			return entity;
		} else {
			return null;
		}
	}

	/**
	 * 发送一个 Post 请求, 使用指定的字符集编码.
	 * 
	 * @param url
	 * @param body
	 *            RequestBody
	 * @param mimeType
	 *            例如 application/xml "application/x-www-form-urlencoded"
	 *            a=1&b=2&c=3
	 * @param charset
	 *            编码
	 * @param connTimeout
	 *            建立链接超时时间,毫秒.
	 * @param readTimeout
	 *            响应超时时间,毫秒.
	 * @return ResponseBody, 使用指定的字符集编码.
	 * @throws ConnectTimeoutException
	 *             建立链接超时异常
	 * @throws SocketTimeoutException
	 *             响应超时
	 * @throws Exception
	 */
	public static String post(String url, String body, Map<String, String> headers, String charset, Integer connTimeout, Integer readTimeout) throws ConnectTimeoutException, SocketTimeoutException,
			Exception {
		HttpClient client = null;
		HttpPost post = new HttpPost(url);
		String result = "";
		try {
			if (StringUtils.isNotBlank(body)) {
				HttpEntity entity = new StringEntity(body,charset);
				post.setEntity(entity);
			}
			/**
			 * 设置头
			 */
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());

			result = invoke(url,post,charset);
			
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
		return result;
	}

	/**
	 * 发送一个 Put 请求, 使用指定的字符集编码.
	 * 
	 * @param url
	 * @param body
	 *            RequestBody
	 * @param mimeType
	 *            例如 application/xml "application/x-www-form-urlencoded"
	 *            a=1&b=2&c=3
	 * @param charset
	 *            编码
	 * @param connTimeout
	 *            建立链接超时时间,毫秒.
	 * @param readTimeout
	 *            响应超时时间,毫秒.
	 * @return ResponseBody, 使用指定的字符集编码.
	 * @throws ConnectTimeoutException
	 *             建立链接超时异常
	 * @throws SocketTimeoutException
	 *             响应超时
	 * @throws Exception
	 */
	public static String put(String url, String body, Map<String, String> headers, String charset, Integer connTimeout, Integer readTimeout) throws ConnectTimeoutException, SocketTimeoutException,
			Exception {
		HttpClient client = null;
		HttpPut put = new HttpPut(url);
		String result = "";
		try {
			if (StringUtils.isNotBlank(body)) {
				HttpEntity entity = new StringEntity(body,charset);
				put.setEntity(entity);
			}
			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					put.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			put.setConfig(customReqConf.build());

			result = invoke(url,put,charset);
			
		} finally {
			put.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
		return result;
	}

	/**
	 * 发送一个 GET 请求
	 * 
	 * @param url
	 * @param charset
	 * @param connTimeout
	 *            建立链接超时时间,毫秒.
	 * @param readTimeout
	 *            响应超时时间,毫秒.
	 * @return
	 * @throws ConnectTimeoutException
	 *             建立链接超时
	 * @throws SocketTimeoutException
	 *             响应超时
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> headers, String charset, Integer connTimeout, Integer readTimeout) throws ConnectTimeoutException, SocketTimeoutException, Exception {

		HttpClient client = null;
		HttpGet get = new HttpGet(url);

		if (headers != null && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				get.addHeader(entry.getKey(), entry.getValue());
			}
		}

		String result = "";
		try {
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			get.setConfig(customReqConf.build());

			result = invoke(url,get,charset);
			
		} finally {
			get.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
		return result;
	}

	/**
	 * 发送一个 Delete 请求
	 * 
	 * @param url
	 * @param charset
	 * @param connTimeout
	 *            建立链接超时时间,毫秒.
	 * @param readTimeout
	 *            响应超时时间,毫秒.
	 * @return
	 * @throws ConnectTimeoutException
	 *             建立链接超时
	 * @throws SocketTimeoutException
	 *             响应超时
	 * @throws Exception
	 */
	public static String delete(String url, Map<String, String> headers, String charset, Integer connTimeout, Integer readTimeout) throws ConnectTimeoutException, SocketTimeoutException, Exception {

		HttpClient client = null;
		HttpDelete delete = new HttpDelete(url);

		if (headers != null && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				delete.addHeader(entry.getKey(), entry.getValue());
			}
		}

		String result = "";
		try {
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			delete.setConfig(customReqConf.build());

			result = invoke(url,delete,charset);
			
		} finally {
			delete.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				((CloseableHttpClient) client).close();
			}
		}
		return result;
	}

	/**
	 * 从 response 里获取 charset
	 * 
	 * @param ressponse
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getCharsetFromResponse(HttpResponse ressponse) {
		// Content-Type:text/html; charset=GBK
		if (ressponse.getEntity() != null && ressponse.getEntity().getContentType() != null && ressponse.getEntity().getContentType().getValue() != null) {
			String contentType = ressponse.getEntity().getContentType().getValue();
			if (contentType.contains("charset=")) {
				return contentType.substring(contentType.indexOf("charset=") + 8);
			}
		}
		return null;
	}

	/**
	 * 创建 SSL连接
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

				@Override
				public void verify(String host, SSLSocket ssl) throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert) throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
				}

			});

			return HttpClients.custom().setSSLSocketFactory(sslsf).build();

		} catch (GeneralSecurityException e) {
			throw e;
		}
	}
	
	private static String invoke(String url,HttpRequestBase method,String charset) throws GeneralSecurityException, ClientProtocolException, IOException{
		HttpResponse res = null;
		
		if(SetCookies!=null&&SetCookies.length>0){
			for(Header item:SetCookies ){
				method.addHeader("Cookie", item.getValue());
			}
		}
		
		if (url.startsWith("https")) {
			// 执行 Https 请求.
			client = createSSLInsecureClient();
			res = client.execute(method);
		} else {
			// 执行 Http 请求.
			client = HttpClientUtils.client;
			res = client.execute(method);
		}
		
		Header[]  c = res.getHeaders("Set-Cookie");
		Header[] m = res.getHeaders("MODULUS");
		Header[] e = res.getHeaders("EXPONENT");
		if(c!=null) SetCookies=c;
		if(m!=null&&m.length>0) MODULUS = m[0];
		if(e!=null&&e.length>0) EXPONENT = e[0];
		
		String result = EntityUtils.toString(res.getEntity(),charset);
		
		return result;
	}

}