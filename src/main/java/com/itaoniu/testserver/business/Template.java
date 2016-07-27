package com.itaoniu.testserver.business;

public enum Template {
	UNKNOW(0, "未知模块"), 
	SYSTEM(1, "系统模块"), 
	USER(2, "用户模块"), 
	USERINFO(3, "用户信息模块"),
	ROLE(4,"角色模块"),
	PERMISSION(5,"权限模块"),
	EMPLOYEE(6,"雇员模块"),
	
	;

	private final int code;

	private final String name;

	private Template(final int templateCode, final String name) {
		this.code = templateCode * TEMPLATE_MASK;
		this.name = name;
	}

	public int getTemplateCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static Template templateOf(final int statusCode) {
		switch (statusCode / TEMPLATE_MASK) {
		case 1:
			return Template.SYSTEM;
		case 2:
			return Template.USER;
		case 3:
			return Template.USERINFO;
		case 4:
			return Template.ROLE;
		case 5:
			return Template.PERMISSION;
		case 6:
			return Template.EMPLOYEE;
		default:
			return Template.UNKNOW;
		}
	}

	public final static int TEMPLATE_MASK = 1000000;

	public final static int STATUS_MASK = 1000;

}
