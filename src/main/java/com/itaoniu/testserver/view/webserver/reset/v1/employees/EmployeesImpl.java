package com.itaoniu.testserver.view.webserver.reset.v1.employees;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.itaoniu.testserver.annotation.Auth;
import com.itaoniu.testserver.annotation.Permission;
import com.itaoniu.testserver.business.employees.model.EmployeeModel;
import com.itaoniu.testserver.business.employees.services.EmployeesService;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.employees.params.AddEmployeeParams;
import com.itaoniu.testserver.view.webserver.reset.v1.employees.params.UpdateEmployeeParams;

@Permission("雇员模块")
@Path("/employees")
public class EmployeesImpl implements IEmployees{
	
	private EmployeesService employeesService;
	
	public EmployeesService getEmployeesService() {
		return employeesService;
	}

	public void setEmployeesService(EmployeesService employeesService) {
		this.employeesService = employeesService;
	}

	@Auth
	@GET
	@Path("/{uuid}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("获取雇员列表")
	@Override
	public Result<List<EmployeeModel>> get_employee_list(@PathParam("uuid")String uuid) {
		List<EmployeeModel> rt = employeesService.getEmployeeList(uuid);
		return Result.success(rt);
	}

	@Auth
	@GET
	@Path("/{uuid}/{number}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("获取雇员")
	@Override
	public Result<EmployeeModel> get_employee(@PathParam("uuid") String uuid,@PathParam("number")  String number) {
		EmployeeModel rt = employeesService.getEmployee(uuid, number);
		return Result.success(rt);
	}
	
	
	@Auth
	@POST
	@Path("/{uuid}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("添加雇员")
	@Override
	public Result<EmployeeModel> add_employee(@PathParam("uuid") String uuid, AddEmployeeParams params) {
		EmployeeModel rt =employeesService.addEmployee(uuid, params.getNumber(), params.getName(), params.getPassword());
		return Result.success(rt);
	}

	@Auth
	@PUT
	@Path("/{uuid}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("更新雇员")
	@Override
	public Result<EmployeeModel> update_employee(@PathParam("uuid") String uuid, UpdateEmployeeParams params) {
		EmployeeModel rt =employeesService.updateEmployee(uuid, params.getNumber(), params.getName(), params.getPassword(),params.getStatus());
		return Result.success(rt);
	}

	@Auth
	@DELETE
	@Path("/{uuid}/{number}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("删除雇员")
	@Override
	public Result<Boolean> delete_employee(@PathParam("uuid") String uuid,@PathParam("number") String number) {
		employeesService.deleteEmployee(uuid, number);
		return Result.success(true);
	}

}
