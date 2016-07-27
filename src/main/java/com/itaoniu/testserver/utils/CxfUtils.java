package com.itaoniu.testserver.utils;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.JAXRSServiceFactoryBean;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.spring.JAXRSServerFactoryBeanDefinitionParser.SpringJAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CxfUtils {

	public static HttpServletRequest getHttpServletRequest() {
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		return request;
	}
	
	public static HttpServletResponse getHttpServletResponse() {
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletResponse response = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
		return response;
	}

	public static ServletContext getServletContext() {
		Message message = PhaseInterceptorChain.getCurrentMessage();
		ServletContext servletContext = (ServletContext) message.get(AbstractHTTPDestination.HTTP_CONTEXT);
		return servletContext;
	}

	public static String getRemoteAddr() {
		HttpServletRequest request = getHttpServletRequest();
		return request.getRemoteAddr();
	}

	public static HttpServletRequest getHttpServletRequest(Message message) {
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		return request;
	}

	public static ServletContext getServletContext(Message message) {
		ServletContext servletContext = (ServletContext) message.get(AbstractHTTPDestination.HTTP_CONTEXT);
		return servletContext;
	}
	
	public static HttpServletResponse getHttpServletResponse(Message message) {
		HttpServletResponse response = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
		return response;
	}

	public static String getRemoteAddr(Message message) {
		HttpServletRequest request = getHttpServletRequest(message);
		return request.getRemoteAddr();
	}

	public static String getHeader(String name) {
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader(name);
	}
	
	public static void putHeader(String name,String value){
		HttpServletRequest request = getHttpServletRequest();
		request.setAttribute(name, value);
	}

	public static String getHeader(Message message,String name) {
		HttpServletRequest request = getHttpServletRequest(message);
		return request.getHeader(name);
	}
	public static void putHeader(Message message,String name,String value) {
		HttpServletRequest request = getHttpServletRequest(message);
		request.setAttribute(name, value);
	}

	/**
	 * 获得session
	 * @param message
	 * @return
	 */
	public static HttpSession obtainSession(Message message){
		HttpServletRequest request = CxfUtils.getHttpServletRequest(message);
		HttpSession session = request.getSession();
		return session;
	}
	
	public static HttpSession obtainSession(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		return obtainSession(message);
	}
	
//	/**
//	 * 获得当前session
//	 * @param message
//	 * @return
//	 */
//	public static HttpSession obtainCurrentSession(){
//		Message message = PhaseInterceptorChain.getCurrentMessage();
//		return obtainSession(message);
//	}
//	
//	public static Object getSessionAttr(Message message,String name){
//		HttpSession session = obtainSession(message);
//		return session.getAttribute(name);
//	}
//	
//	public static Object getSessionAttr(String name){
//		HttpSession session = obtainCurrentSession();
//		return session.getAttribute(name);
//	}
//	
//	
//	public static void putSessionAttr(Message message,String name,Object value){
//		HttpSession session = obtainSession(message);
//		session.setAttribute(name, value);
//	}
//	
//	public static void putSessionAttr(String name,Object value){
//		HttpSession session = obtainCurrentSession();
//		session.setAttribute(name, value);
//	}
//	
//	public static void putToken(String value){
//		putSessionAttr("TOKEN",value);
//	}
//	
//	public static String getToken(){
//		HttpSession session = obtainCurrentSession();
//		String token= (String) session.getAttribute("TOKEN");
//		return token;
//	}
	
	public static List<ClassResourceInfo> getClassResourceInfoList() {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		SpringJAXRSServerFactoryBean jaxBean = (SpringJAXRSServerFactoryBean) context.getBean("testserver");
		JAXRSServiceFactoryBean serviceFactory = jaxBean.getServiceFactory();
		List<ClassResourceInfo> classResourceList = serviceFactory.getClassResourceInfo();
		return classResourceList;
	}

}
