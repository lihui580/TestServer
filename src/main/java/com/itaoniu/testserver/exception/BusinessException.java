package com.itaoniu.testserver.exception;

import com.itaoniu.testserver.business.base.ErrorCode;


public class BusinessException extends BaseException{
	/**
	 * 业务层
	 */
	private static final long serialVersionUID = 6518092368187751049L;
	
	public BusinessException(ErrorCode.StatusType statusType) {
		super(statusType.getTemplate(), statusType.getStatusCode(), statusType.getReasonPhrase());
	}


	

}
