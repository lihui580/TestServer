package com.itaoniu.testserver.business.base;

import com.itaoniu.testserver.exception.BusinessException;

public class BaseBusiness<T extends ErrorCode> {

	protected long throwException(ErrorCode.StatusType statusType) throws BusinessException {
		throw new BusinessException(statusType);
	}

}
