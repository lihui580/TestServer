package com.itaoniu.testserver.view.webserver.reset.v1.permissions;

import java.util.List;

import com.itaoniu.testserver.business.permissions.model.PermissionModel;
import com.itaoniu.testserver.view.webserver.reset.Result;

public interface IPermissions {

	public Result<List<PermissionModel>> get_permission_list(); 
}
