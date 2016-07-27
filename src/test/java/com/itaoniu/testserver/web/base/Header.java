package com.itaoniu.testserver.web.base;

public @interface Header {
	
	public String[] value() default {"Content-Type:application/json; charset=UTF-8","Accept:application/json; charset=UTF-8"};
	
}
