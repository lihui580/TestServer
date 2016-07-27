package com.itaoniu.testserver.web.employees;

import java.net.SocketTimeoutException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Test;

import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.view.webserver.reset.v1.employees.params.AddEmployeeParams;
import com.itaoniu.testserver.view.webserver.reset.v1.employees.params.UpdateEmployeeParams;
import com.itaoniu.testserver.web.base.Base;
import com.itaoniu.testserver.web.tn.LoginTest;

@Base("http://127.0.0.1:8070/testserver/reset/employees")
public class EmployeesTest extends LoginTest{
	private final static String NUMBER = "001";
	
	@Test
	@POST
	@Path("/"+UUID)	
	public void add_employee() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		AddEmployeeParams params = new AddEmployeeParams();
		params.setName("小李");
		params.setNumber(NUMBER);
		params.setPassword("123456");
		invoke(params);
	}
	
	@Test
	@PUT
	@Path("/"+UUID)	
	public void update_employee() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		UpdateEmployeeParams params = new UpdateEmployeeParams();
		params.setName("小李");
		params.setNumber(NUMBER);
		params.setPassword("12345678");
		params.setStatus(Environment.STATUS_OFF);
		invoke(params);
	}
	
	@Test
	@GET
	@Path("/"+UUID)
	public void get_employee_list() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
	
	@Test
	@GET
	@Path("/"+UUID+"/"+NUMBER)
	public void get_employee() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
	
	@Test
	@DELETE
	@Path("/"+UUID+"/"+NUMBER)
	public void delete_employee() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		invoke("");
	}
}
