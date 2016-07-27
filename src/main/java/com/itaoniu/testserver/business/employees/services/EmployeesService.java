package com.itaoniu.testserver.business.employees.services;

import java.util.Date;
import java.util.List;

import com.itaoniu.testserver.business.base.BaseBusiness;
import com.itaoniu.testserver.business.base.MapBuilder;
import com.itaoniu.testserver.business.employees.EmployeesErrorCode;
import com.itaoniu.testserver.business.employees.model.EmployeeModel;
import com.itaoniu.testserver.persist.dao.base.IEntityDao;
import com.itaoniu.testserver.persist.pojo.TEmployee;
import com.itaoniu.testserver.persist.pojo.TStoreRoleEmployee;
import com.itaoniu.testserver.utils.CopyUtils;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.utils.RSAUtils;
import com.itaoniu.testserver.utils.StringUtils;

public class EmployeesService extends BaseBusiness<EmployeesErrorCode> {

	private IEntityDao<TEmployee> employeeDao;

	private IEntityDao<TStoreRoleEmployee> storeRoleEmployeeDao;

	public IEntityDao<TEmployee> getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(IEntityDao<TEmployee> employeeDao) {
		this.employeeDao = employeeDao;
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
	public List<EmployeeModel> getEmployeeList(String uuid) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid);

		List<TEmployee> list = employeeDao.find(params.build(Environment.EXIST_ON));

		return CopyUtils.copy(list, EmployeeModel.class);
	}

	/**
	 * 获取角色
	 * 
	 * @param uuid
	 * @param name
	 * @return
	 */
	public EmployeeModel getEmployee(String uuid, String number) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid).add("number", number);

		TEmployee source = employeeDao.get(params.build(Environment.EXIST_ON));

		return CopyUtils.copy(source, EmployeeModel.class);
	}

	public EmployeeModel addEmployee(String uuid, String number, String name, String password) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid).add("number", number);

		TEmployee source = employeeDao.get(params.build(Environment.EXIST_ALL));

		if (employeeDao.exist(source)) {
			throwException(EmployeesErrorCode.Status.Code_002);
		}

		if (source == null) {
			source = new TEmployee();
			source.setUuid(uuid);
			source.setName(name);
			source.setNumber(number);
			source.setPassword(RSAUtils.encryptByPublicKey(password, Environment.PUBLIC_KEY));
			source.setStatus(Environment.STATUS_ON);
		}
		
		employeeDao.save(source);

		return CopyUtils.copy(source, EmployeeModel.class);
	}

	/**
	 * 更新雇员
	 * 
	 * @param uuid
	 * @param name
	 * @param rank
	 * @return
	 */
	public EmployeeModel updateEmployee(String uuid, String number, String name, String password,int status) {
		MapBuilder params = MapBuilder.create().add("uuid", uuid).add("number", number);

		TEmployee source = employeeDao.get(params.build(Environment.EXIST_ALL));

		if (!employeeDao.exist(source)) {
			throwException(EmployeesErrorCode.Status.Code_001);
			
		}

		if (!StringUtils.isEmpty(name)) source.setName(name);

		if (!StringUtils.isEmpty(password)) source.setPassword(RSAUtils.encryptByPublicKey(password, Environment.PUBLIC_KEY));
		
		if(status!=Environment.STATUS_ALL) source.setStatus(status);

		employeeDao.update(source);

		return CopyUtils.copy(source, EmployeeModel.class);
	}

	/**
	 * 删除角色
	 * 
	 * @param uuid
	 * @param name
	 */
	public void deleteEmployee(String uuid, String number) {
		MapBuilder params1 = MapBuilder.create().add("uuid", uuid).add("number", number);

		TEmployee source = employeeDao.get(params1.build(Environment.EXIST_ON));

		if (source == null) {
			return;
		}

		MapBuilder params2 = MapBuilder.create().add("employeeId", source.getId());
		storeRoleEmployeeDao.delete(params2.build(Environment.EXIST_ON));

		employeeDao.delete(source);

	}
}
