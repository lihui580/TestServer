package com.itaoniu.testserver.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.itaoniu.testserver.utils.StringUtils;

public class AccountValidator implements ConstraintValidator<Account, String> {

	@Override
	public void initialize(Account constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {	
		return StringUtils.type(value)!=StringUtils.UNKNOW;
	}

}
