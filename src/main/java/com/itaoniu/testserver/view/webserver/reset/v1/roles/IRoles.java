package com.itaoniu.testserver.view.webserver.reset.v1.roles;

import java.util.List;

import com.itaoniu.testserver.business.role.model.RoleModel;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.roles.params.AddRoleParams;
import com.itaoniu.testserver.view.webserver.reset.v1.roles.params.UpdateRoleParams;

public interface IRoles {

	/**
	 * 获取角色列表
	 */
	public Result<List<RoleModel>> get_role_list(String uuid);
	
	/**
	 * 获取角色
	 */
	public Result<RoleModel> get_role(String uuid,String name);
	
	/**
	 * 添加角色
	 */
	public Result<RoleModel> add_role(String uuid,AddRoleParams params);
	
	/**
	 * 更新角色
	 */
	public Result<RoleModel> update_role(String uuid,UpdateRoleParams params);
	
	/**
	 * 删除角色
	 */
	public Result<Boolean> delete_role(String uuid,String name);
	
}
