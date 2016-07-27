package com.itaoniu.testserver.view.webserver.reset.v1.roles;

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
import com.itaoniu.testserver.annotation.TNID;
import com.itaoniu.testserver.business.role.model.RoleModel;
import com.itaoniu.testserver.business.role.services.RolesService;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.view.webserver.reset.Result;
import com.itaoniu.testserver.view.webserver.reset.v1.roles.params.AddRoleParams;
import com.itaoniu.testserver.view.webserver.reset.v1.roles.params.UpdateRoleParams;

@Permission("角色模块")
@Path("/roles")
public class RolesImpl implements IRoles{

	private RolesService rolesService;
	
	public RolesService getRolesService() {
		return rolesService;
	}

	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
	}

	@Auth
	@GET
	@Path("/{uuid}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("获取角色列表")
	@Override
	public Result<List<RoleModel>> get_role_list(@TNID @PathParam("uuid") String uuid) {
		List<RoleModel> rt = rolesService.getRoleList(uuid);
		return Result.success(rt);
	}

	@Auth
	@GET
	@Path("/{uuid}/{number}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("获取角色")
	@Override
	public Result<RoleModel> get_role(@TNID @PathParam("uuid") String uuid,@PathParam("number") String number) {
		RoleModel rt = rolesService.getRole(uuid,number);
		return Result.success(rt);
	}

	@Auth
	@POST
	@Path("/{uuid}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("添加角色")
	@Override
	public Result<RoleModel> add_role(@TNID @PathParam("uuid") String uuid,AddRoleParams params) {
		RoleModel rt = rolesService.addRole(uuid, params.getNumber(),params.getName(), params.getRank());
		return Result.success(rt);
	}
	
	@Auth
	@PUT
	@Path("/{uuid}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("更新角色")
	@Override
	public Result<RoleModel> update_role(@TNID @PathParam("uuid") String uuid,UpdateRoleParams params) {
		RoleModel rt = rolesService.updateRole(uuid, params.getNumber(),params.getName(), params.getRank());
		return Result.success(rt);
	}
	

	@Auth
	@DELETE
	@Path("/{uuid}/{number}")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Permission("删除角色")
	@Override
	public Result<Boolean> delete_role(@TNID @PathParam("uuid") String uuid,@PathParam("number") String number) {
		rolesService.deleteRole(uuid, number);
		return Result.success(true);
	}

	

}
