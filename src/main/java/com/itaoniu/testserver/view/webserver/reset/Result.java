package com.itaoniu.testserver.view.webserver.reset;

import javax.ws.rs.core.Response;

import com.itaoniu.testserver.business.Template;
import com.itaoniu.testserver.exception.BaseException;


public class Result<T> {
	
	public static <T> Result<T> success(T t){
		return build(Body.build(t),Head.build(200, "成功"));
	}
	
	public static <T> Result<T> build(Body<T> body, Head head){
		Result<T> result = new Result<T>();
		result.setBody(body);
		result.setHead(head);
		return result;
	}
	
	/**
	 * 其他错误
	 */
	public static <T> Result<T> buildError(Response.Status status,String msg){
		return build(null,Head.build(status.getStatusCode()+Template.SYSTEM.getTemplateCode(), msg));
	}
	
	/**
	 * 代码错误
	 */
	public static<T> Result<T> buildError(Response.Status status,BaseException ex){
		return build(null,Head.build(status.getStatusCode()+ex.getErrCode(), ex.getMessage()));
	}
	
	private Body<T> body;
	
	private Head head;

	public Body<T> getBody() {
		return body;
	}

	public void setBody(Body<T> body) {
		this.body = body;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

}
