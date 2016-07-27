package com.itaoniu.testserver.view.webserver.reset.v1.users.params;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.itaoniu.testserver.constraint.Account;

public class RegistParams {

	@Account
	@NotBlank(message="账号不能为空")
	@Size(min=6,max=32,message="账号长度不符合要求")
	private String account;
	
	@NotBlank(message="密码不能为空")
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

