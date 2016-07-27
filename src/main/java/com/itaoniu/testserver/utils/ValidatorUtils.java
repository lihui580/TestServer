package com.itaoniu.testserver.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtils {

	private static Validator validator;

	public static Validator getInstance() {
		if (validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		return validator;
	}
	
	/**
	 * 输入是否有错误
	 * @param t
	 * @return
	 */
	public static <T> boolean hasError(T t){
		Set<ConstraintViolation<T>> set = getInstance().validate(t);
		if(set.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取错误信息
	 * @param t
	 * @return
	 */
	public static <T> Set<ConstraintViolation<T>> validate(T t){
		return getInstance().validate(t);
	}
	
	/**
	 * 获取最近的一条错误信息
	 * @param t
	 * @return
	 */
	public static <T> String getErrorMessage(T t){
		Set<ConstraintViolation<T>> set = getInstance().validate(t);
		if(set.size()>0){
			return set.iterator().next().getMessage();
		}
		return null;
	}
	
}
