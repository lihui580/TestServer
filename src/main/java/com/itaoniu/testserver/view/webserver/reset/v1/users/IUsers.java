package com.itaoniu.testserver.view.webserver.reset.v1.users;

import java.util.List;

import com.itaoniu.testserver.business.users.model.UserInfoModel;
import com.itaoniu.testserver.business.users.model.UserModel;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.users.params.UpdateUserinfoParams;

public interface IUsers {

	/**
	 * 获取用户
	 */
	public Result<List<UserModel>> get_user_list(String uuid);
	
	
	/**
	 * 绑定用户
	 */
	public Result<UserModel> bind_user(String uuid,String account);
	
	/**
	 * 获取用户信息
	 */
	public Result<UserInfoModel> get_userinfo(String uuid);
	
	/**
	 * 更新用户信息
	 */
	public Result<UserInfoModel> update_userinfo(String uuid,UpdateUserinfoParams params);
}
