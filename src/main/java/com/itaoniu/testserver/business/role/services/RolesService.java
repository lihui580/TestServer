package com.itaoniu.testserver.business.role.services;

import java.util.List;

import com.itaoniu.testserver.business.base.BaseBusiness;
import com.itaoniu.testserver.business.base.MapBuilder;
import com.itaoniu.testserver.business.role.RoleErrorCode;
import com.itaoniu.testserver.business.role.model.RoleModel;
import com.itaoniu.testserver.business.role.model.RolePermissionModel;
import com.itaoniu.testserver.persist.dao.base.IEntityDao;
import com.itaoniu.testserver.persist.pojo.TRole;
import com.itaoniu.testserver.persist.pojo.TRolePermission;
import com.itaoniu.testserver.persist.pojo.TStoreRoleEmployee;
import com.itaoniu.testserver.utils.CopyUtils;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.utils.StringUtils;

public class RolesService extends BaseBusiness<RoleErrorCode> {

	private IEntityDao<TRole> roleDao;

	private IEntityDao<TRolePermission> rolePermissionDao;

	private IEntityDao<TStoreRoleEmployee> storeRoleEmployeeDao;

	public IEntityDao<TRole> getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IEntityDao<TRole> roleDao) {
		this.roleDao = roleDao;
	}

	public IEntityDao<TRolePermission> getRolePermissionDao() {
		return rolePermissionDao;
	}

	public void setRolePermissionDao(IEntityDao<TRolePermission> rolePermissionDao) {
		this.rolePermissionDao = rolePermissionDao;
	}

	public IEntityDao<TStoreRoleEmployee> getStoreRoleEmployeeDao() {
		return storeRoleEmployeeDao;
	}

	public void setStoreRoleEmployeeDao(IEntityDao<TStoreRoleEmployee> storeRoleEmployeeDao) {
		this.storeRoleEmployeeDao = storeRoleEmployeeDao;
	}

	/**
	 * 获取角色列表
	 * 
	 * @param uuid
	 * @return
	 */
	public List<RoleModel> getRoleList(String uuid) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid);

		List<TRole> roleList = roleDao.find(params.build(Environment.EXIST_ON));

		return CopyUtils.copy(roleList, RoleModel.class);
	}

	/**
	 * 获取角色
	 * 
	 * @param uuid
	 * @param name
	 * @return
	 */
	public RoleModel getRole(String uuid, String number) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid).add("number", number);

		TRole source = roleDao.get(params.build(Environment.EXIST_ON));

		return CopyUtils.copy(source, RoleModel.class);
	}

	/**
	 * 添加角色
	 * 
	 * @param uuid
	 * @param name
	 * @param rank
	 * @return
	 */
	public RoleModel addRole(String uuid, String number, String name,int rank) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid).add("number", number);

		TRole source = roleDao.get(params.build(Environment.EXIST_ALL));

		if (roleDao.exist(source)) {
			throwException(RoleErrorCode.Status.Code_002);
		}

		if (source == null) {
			source = new TRole();
			source.setUuid(uuid);
			source.setNumber(number);
			source.setName(name);
			source.setRank(rank);
		}

		roleDao.save(source);

		return CopyUtils.copy(source, RoleModel.class);
	}
	
	/**
	 * 更新角色
	 * 
	 * @param uuid
	 * @param name
	 * @param rank
	 * @return
	 */
	public RoleModel updateRole(String uuid, String number, String name,int rank) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid).add("number", number);

		TRole source = roleDao.get(params.build(Environment.EXIST_ALL));

		if (!roleDao.exist(source)) {
			throwException(RoleErrorCode.Status.Code_001);
		}

		if(!StringUtils.isEmpty(name))source.setName(name);
		
		if(rank!=0) source.setRank(rank);
		
		roleDao.update(source);

		return CopyUtils.copy(source, RoleModel.class);
	}

	/**
	 * 删除角色
	 * 
	 * @param uuid
	 * @param name
	 */
	public void deleteRole(String uuid, String number) {
		MapBuilder params1 = MapBuilder.create().add("uuid", uuid).add("number", number);

		TRole source = roleDao.get(params1.build(Environment.EXIST_ON));

		if (source == null) {
			return;
		}

		MapBuilder params2 = MapBuilder.create().add("roleId", source.getId());
		storeRoleEmployeeDao.delete(params2.build(Environment.EXIST_ON));

		roleDao.delete(source);

	}

	/**
	 * 增加角色权限
	 * 
	 * @param params
	 * @return
	 */
	public RolePermissionModel addRolePremission(RolePermissionModel params) {
		MapBuilder params2 = MapBuilder.create().add("roleId", params.getRoleId()).add("clazz", params.getClazz()).add("method", params.getMethod());

		TRolePermission p = rolePermissionDao.get(params2.build(Environment.EXIST_ALL));

		if (p == null) {
			p = new TRolePermission();
			p.setRoleId(params.getRoleId());
			p.setClazz(params.getClazz());
			p.setMethod(params.getMethod());
			p.setName(params.getName());

		}

		rolePermissionDao.save(p);

		return CopyUtils.copy(p, RolePermissionModel.class);
	}
	
	
	

}
