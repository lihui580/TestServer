package com.itaoniu.testserver.view.webserver.reset.v1.tn;

import com.itaoniu.testserver.business.users.model.UserModel;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.users.params.RegistParams;

public interface ILogin {

	/**
	 * 登录前
	 */
	public Result<String> perlogin();
	
	/**
	 * 注册
	 */
	public Result<UserModel> regist(RegistParams params);
	
	/**
	 * 登录
	 */
	public Result<UserModel> login(String account,String password);
	
	/**
	 * 登录
	 */
	public Result<String> logout();
}
