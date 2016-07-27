package com.itaoniu.testserver.exception;

import com.itaoniu.testserver.business.Template;


public class BaseException extends RuntimeException{
	/**
	 * 业务层
	 */
	private static final long serialVersionUID = 6518092368187751049L;
	
	private long errCode;
		
	public BaseException(Template template,long errCode,String message) {
		super(message);
		this.errCode=errCode;
	}
	
	public long getErrCode() {
		return errCode;
	}
	
}
