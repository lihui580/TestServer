package com.itaoniu.testserver.web.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ConnectTimeoutException;

import com.alibaba.fastjson.JSON;
import com.itaoniu.testserver.utils.HttpClientUtils;
import com.itaoniu.testserver.utils.StringUtils;

public class BaseTest {
	
	private Log log = LogFactory.getLog(BaseTest.class);
	
	protected String getCurrentUrl() {
		String baseUrl = getClassPath();
		String interfaceUrl = getMethodPath();
		return baseUrl+interfaceUrl;
	}
	
	protected String getMethodPath(){
		String name = Thread.currentThread().getStackTrace()[3].getMethodName();	
		Class<?> clazz = this.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if(!StringUtils.equalsIgnoreCase(method.getName(), name)){
				continue;
			}
			if (!method.isAnnotationPresent(Path.class)) {
				return name;
			}
			Path path = method.getAnnotation(Path.class);
			return path.value();
		}
		return name;
	}
	
	protected String getClassPath() {
		Class<?> clazz = this.getClass();
		Annotation[] annotations = clazz.getAnnotations();
		String baseUrl = "";
		String intefaceUrl = "";
		for (Annotation item : annotations) {
			if (clazz.isAnnotationPresent(Base.class)) {
				baseUrl = clazz.getAnnotation(Base.class).value();
			}
			if (clazz.isAnnotationPresent(Path.class)) {
				intefaceUrl = clazz.getAnnotation(Path.class).value();
			}
		}
		String urls = baseUrl + intefaceUrl;
		log.debug("调用基地址为:【" + urls + "】");
		return urls;
	}
	
	protected String getHttpMethod(){
		String name = Thread.currentThread().getStackTrace()[3].getMethodName();	
		Class<?> clazz = this.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if(!StringUtils.equalsIgnoreCase(method.getName(), name)){
				continue;
			}
			if (method.isAnnotationPresent(GET.class)) {
				return GET.class.getSimpleName();
			}else if (method.isAnnotationPresent(POST.class)) {
				return POST.class.getSimpleName();
			}else if (method.isAnnotationPresent(PUT.class)) {
				return PUT.class.getSimpleName();
			}else if (method.isAnnotationPresent(DELETE.class)) {
				return DELETE.class.getSimpleName();
			}
		}
		return null;		
	}
	
	protected Map<String, String> getHeaders(){
		String name = Thread.currentThread().getStackTrace()[3].getMethodName();	
		Class<?> clazz = this.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");	
		for (Method method : methods) {
			if(!StringUtils.equalsIgnoreCase(method.getName(), name)){
				continue;
			}
			if (method.isAnnotationPresent(Header.class)) {
				String[] headerStrList = clazz.getAnnotation(Header.class).value();
				if(headerStrList==null||headerStrList.length==0){
					return headers;
				}
				
				for(String headerStr:headerStrList){
					String[] spilt = headerStr.split(":");
					if(spilt!=null&&spilt.length==2){
						headers.put(spilt[0], spilt[1]);
					}
				}
			}
		}
		return headers;		
	}
	
	public  String invoke(Object params) throws ConnectTimeoutException, SocketTimeoutException, Exception{
		String baseUrl = getClassPath();
		String interfaceUrl = getMethodPath();
		String url = baseUrl+interfaceUrl;
		Map<String, String> headers = getHeaders();
		String method = getHttpMethod();	
		String result = "调用错误";
		
		System.out.println("**************************************");
		long beginTime = System.currentTimeMillis();
		
		if(StringUtils.equals(method, GET.class.getSimpleName())||StringUtils.equals(method, DELETE.class.getSimpleName())){
			String urlParams = "";
			if(params!=null&&params instanceof UrlParams){
				urlParams = HttpClientUtils.getUrlParams(((UrlParams)params).build(),"UTF-8");
				url=url+"?"+urlParams;
			}		
		}
		
		System.out.println("调用地址："+url);
		System.out.println("方法类型："+method);
		if(StringUtils.equals(method, GET.class.getSimpleName())||StringUtils.equals(method, DELETE.class.getSimpleName())){
			String urlParams = "";
			if(params!=null&&params instanceof UrlParams){
				urlParams = HttpClientUtils.getUrlParams(((UrlParams)params).build(),"UTF-8");
				
			}	
			System.out.println("入      参："+urlParams);
			
		}else{
			System.out.println("入      参："+JSON.toJSONString(params));
			
		}
		
		if(StringUtils.equals(method, GET.class.getSimpleName())){
			result = HttpClientUtils.get(url, headers, "UTF-8", HttpClientUtils.connTimeout, HttpClientUtils.readTimeout);
			
		}else if(StringUtils.equals(method, POST.class.getSimpleName())){
			 result = HttpClientUtils.post(url,JSON.toJSONString(params), headers, "UTF-8", HttpClientUtils.connTimeout, HttpClientUtils.readTimeout);
			 
		}else if(StringUtils.equals(method, PUT.class.getSimpleName())){
			result = HttpClientUtils.put(url,JSON.toJSONString(params), headers, "UTF-8", HttpClientUtils.connTimeout, HttpClientUtils.readTimeout);
			 
		}else if(StringUtils.equals(method, DELETE.class.getSimpleName())){
			result = HttpClientUtils.delete(url, headers, "UTF-8", HttpClientUtils.connTimeout, HttpClientUtils.readTimeout);
			 
		}
		
		System.out.println("出      参："+result);
		long endTime = System.currentTimeMillis();
		System.out.println("总 耗  时：" + (endTime - beginTime) + "ms");
		return result;
	}
		
}
