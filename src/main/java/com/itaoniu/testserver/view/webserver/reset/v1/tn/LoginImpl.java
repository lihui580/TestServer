package com.itaoniu.testserver.view.webserver.reset.v1.tn;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.alibaba.fastjson.JSON;
import com.itaoniu.testserver.business.users.model.UserModel;
import com.itaoniu.testserver.business.users.services.UsersService;
import com.itaoniu.testserver.session.SessionProxy;
import com.itaoniu.testserver.session.SessionUtils;
import com.itaoniu.testserver.utils.CxfUtils;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.utils.RSAUtils;
import com.itaoniu.testserver.utils.RSAUtils.RSAKey;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.users.params.RegistParams;

@Path("/tn")
public class LoginImpl implements ILogin{
	
	private UsersService usersService;
	
	public UsersService getUsersService() {
		return usersService;
	}


	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@GET
	@Path("/perlogin")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Override
	public Result<String> perlogin() {
		return Result.success("成功");
	}
	
	@POST
	@Path("/regist")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Override
	public Result<UserModel> regist(RegistParams params) {
		UserModel model = usersService.addUser(params.getAccount(), params.getPassword(), 1);
		return Result.success(model);
	}

	@GET
	@Path("/login")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Override
	public Result<UserModel> login(@QueryParam("account") String account,@QueryParam("password") String password) {
		SessionProxy session = SessionUtils.obtainSession();
		RSAKey key = session.getAttr("private", RSAKey.class);	
		UserModel result = usersService.login(account,RSAUtils.decryptByPrivateKey(password, key));
		return Result.success(result);
	}
	
	@DELETE
	@Path("/logout")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Override
	public Result<String> logout() {
		SessionProxy session = SessionUtils.obtainSession();
		SessionProxy.delete(session);
		return Result.success("成功");
	}

}
