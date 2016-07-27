package com.itaoniu.testserver.view.webserver.reset.v1.employees;

import java.util.List;

import com.itaoniu.testserver.business.employees.model.EmployeeModel;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.employees.params.AddEmployeeParams;
import com.itaoniu.testserver.view.webserver.reset.v1.employees.params.UpdateEmployeeParams;

public interface IEmployees {
	/**
	 * 获取角色列表
	 */
	public Result<List<EmployeeModel>> get_employee_list(String uuid);
	
	/**
	 * 获取角色
	 */
	public Result<EmployeeModel> get_employee(String uuid,String number);
	
	/**
	 * 更新角色
	 */
	public Result<EmployeeModel> add_employee(String uuid,AddEmployeeParams params);
	
	/**
	 * 更新角色
	 */
	public Result<EmployeeModel> update_employee(String uuid,UpdateEmployeeParams params);
	
	/**
	 * 删除角色
	 */
	public Result<Boolean> delete_employee(String uuid,String number);
}
