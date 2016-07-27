package com.itaoniu.testserver.view.webserver.reset.v1.users;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.itaoniu.testserver.annotation.Auth;
import com.itaoniu.testserver.annotation.Permission;
import com.itaoniu.testserver.annotation.TNID;
import com.itaoniu.testserver.business.users.model.UserInfoModel;
import com.itaoniu.testserver.business.users.model.UserModel;
import com.itaoniu.testserver.business.users.services.UserInfosService;
import com.itaoniu.testserver.business.users.services.UsersService;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.users.params.UpdateUserinfoParams;

@Permission("用户模块")
@Path("/users")
public class UsersImpl implements IUsers{

	private UsersService usersService;
	
	private UserInfosService userInfosService;
	

	public UsersService getUsersService() {
		return usersService;
	}


	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}


	public UserInfosService getUserInfosService() {
		return userInfosService;
	}


	public void setUserInfosService(UserInfosService userInfosService) {
		this.userInfosService = userInfosService;
	}


	

	@Auth
	@GET
	@Path("/{uuid}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("获取用户列表")
	@Override
	public Result<List<UserModel>> get_user_list(@TNID @PathParam("uuid") String uuid) {
		List<UserModel> users = usersService.getUserList(uuid,Environment.EXIST_ON);
		return Result.success(users);
	}

	

	@Auth
	@POST
	@Path("/{uuid}/bind")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("绑定账号")
	@Override
	public Result<UserModel> bind_user(@TNID @PathParam("uuid") String uuid,String account) {
		UserModel model = usersService.addUserBind(uuid, account, 1);
		return Result.success(model);
	}
	

	@Auth
	@GET
	@Path("/{uuid}/info")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("获取用户信息")
	@Override
	public Result<UserInfoModel> get_userinfo(@TNID @PathParam("uuid") String uuid) {
		UserInfoModel info = userInfosService.getUserInfo(uuid,Environment.EXIST_ON);
		return Result.success(info);
	}

	@Auth
	@POST
	@Path("/{uuid}/info")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("更新用户信息")
	@Override
	public Result<UserInfoModel> update_userinfo(@TNID @PathParam("uuid") String uuid, UpdateUserinfoParams params) {
		UserInfoModel info = userInfosService.updateUserInfo(uuid, params.getImage(), params.getNickname(), params.getSignature());
		return Result.success(info);
	}


}
