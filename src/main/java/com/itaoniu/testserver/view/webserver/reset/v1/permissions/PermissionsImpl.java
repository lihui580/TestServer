package com.itaoniu.testserver.view.webserver.reset.v1.permissions;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.itaoniu.testserver.business.permissions.model.PermissionModel;
import com.itaoniu.testserver.business.permissions.services.PermissionsService;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.view.webserver.reset.Result;

@Path("/permissions")
public class PermissionsImpl implements IPermissions{
	
	private PermissionsService permissionsService;
	
	public PermissionsService getPermissionsService() {
		return permissionsService;
	}

	public void setPermissionsService(PermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}

	@GET
	@Path("")
	@Produces({Environment.APPLICATION_JSON})
	@Consumes({ Environment.APPLICATION_XML, Environment.APPLICATION_JSON, Environment.APPLICATION_FORM_URLENCODED })
	@Override
	public Result<List<PermissionModel>> get_permission_list() {
		List<PermissionModel> rt = permissionsService.getMethodPermissionList();
		return Result.success(rt);
	}

}
