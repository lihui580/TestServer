package com.itaoniu.testserver.web.users;

import java.net.SocketTimeoutException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Test;

import com.itaoniu.testserver.view.webserver.reset.v1.users.params.UpdateUserinfoParams;
import com.itaoniu.testserver.web.base.Base;
import com.itaoniu.testserver.web.tn.LoginTest;

@Base("https://localhost:8443/testserver/reset/users")
public class UsersTest extends LoginTest{

	
	@Test
	@GET
	@Path("/"+LoginTest.UUID)
	public void get_users() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
	
	@Test
	@POST
	@Path("/"+LoginTest.UUID+"/bind")
	public void bind_user() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("user4");
	}
	
	@Test
	@GET
	@Path("/"+LoginTest.UUID+"/info")
	public void get_userinfo() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
	
	@Test
	@POST
	@Path("/"+LoginTest.UUID+"/info")
	public void update_userinfo() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		UpdateUserinfoParams params = new UpdateUserinfoParams();
		params.setImage("/image");
		params.setNickname("uuu");
		params.setSignature("xxxx");
		invoke(params);
	}
	
}
