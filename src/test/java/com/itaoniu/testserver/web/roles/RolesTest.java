package com.itaoniu.testserver.web.roles;

import java.net.SocketTimeoutException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Test;

import com.itaoniu.testserver.view.webserver.reset.v1.roles.params.AddRoleParams;
import com.itaoniu.testserver.view.webserver.reset.v1.roles.params.UpdateRoleParams;
import com.itaoniu.testserver.web.base.Base;
import com.itaoniu.testserver.web.tn.LoginTest;

@Base("http://127.0.0.1:8070/testserver/reset/roles")
public class RolesTest extends LoginTest{
	private final static String NAME = "总经理";
	
	@Test
	@POST
	@Path("/"+UUID)
	public void add_role() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		AddRoleParams params = new AddRoleParams();
		params.setNumber("0001");
		params.setName(NAME);
		params.setRank(1);
		invoke(params);
	}
	
	@Test
	@PUT
	@Path("/"+UUID)
	public void update_role() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		UpdateRoleParams params = new UpdateRoleParams();
		params.setNumber("0002");
		params.setName(NAME+"(改)");
		params.setRank(1);
		invoke(params);
	}
	
	@Test
	@GET
	@Path("/"+UUID)
	public void get_role_list() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
	
	@Test
	@GET
	@Path("/"+UUID+"/"+NAME)
	public void get_role() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
	
	@Test
	@DELETE
	@Path("/"+UUID+"/"+NAME)
	public void delete_role() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
}
