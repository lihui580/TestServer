package com.itaoniu.testserver.business.users.services;

import java.util.List;

import com.itaoniu.testserver.business.base.BaseBusiness;
import com.itaoniu.testserver.business.base.MapBuilder;
import com.itaoniu.testserver.business.users.UserErrorCode;
import com.itaoniu.testserver.business.users.model.UserModel;
import com.itaoniu.testserver.persist.dao.base.IEntityDao;
import com.itaoniu.testserver.persist.pojo.TEmployee;
import com.itaoniu.testserver.persist.pojo.TUser;
import com.itaoniu.testserver.persist.pojo.TUuid;
import com.itaoniu.testserver.utils.CopyUtils;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.utils.RSAUtils;
import com.itaoniu.testserver.utils.StringUtils;

public class UsersService  extends BaseBusiness<UserErrorCode>{
	
	private IEntityDao<TUuid> uuidDao;
	
	private IEntityDao<TUser> userDao;
	
	private IEntityDao<TEmployee> employeeDao;

	public IEntityDao<TUuid> getUuidDao() {
		return uuidDao;
	}

	public void setUuidDao(IEntityDao<TUuid> uuidDao) {
		this.uuidDao = uuidDao;
	}

	public IEntityDao<TUser> getUserDao() {
		return userDao;
	}

	public void setUserDao(IEntityDao<TUser> userDao) {
		this.userDao = userDao;
	}

	public IEntityDao<TEmployee> getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(IEntityDao<TEmployee> employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * 登录
	 * @param user 用户账号
	 * @param password MD5值
	 */
	public UserModel login(String userEmp,String password){
		if(userEmp==null||password==null){
			 throwException(UserErrorCode.Status.Code_002);
		}
		
		String[] str = userEmp.split(":");
		String user =null;
		String number = null;
		if(str.length==1){//主账号登录
			user=str[0];
			number="";
			MapBuilder builder = MapBuilder.create().add("user", user);
			
			TUser a = userDao.get(builder.build(Environment.EXIST_ON));
			
			if(a==null){
				 throwException(UserErrorCode.Status.Code_001);
			}
			
			MapBuilder builder1 = MapBuilder.create().add("uuid", a.getUuid());
			
			TUuid uuid = uuidDao.get(builder1.build(Environment.EXIST_ON));
			
			if(uuid==null){
				 throwException(UserErrorCode.Status.Code_001);
			}
			
			String ming = RSAUtils.decryptByPrivateKey(uuid.getPassword(), Environment.PRIVATE_KEY);
			
			if(!StringUtils.equals(ming, password)){		
				throwException(UserErrorCode.Status.Code_008);
			}
			
			return  CopyUtils.copy(a,UserModel.class );
			
		}else{
			user=str[0];
			number=str[1];
			
			MapBuilder builder1 = MapBuilder.create().add("user", user);
			
			TUser a = userDao.get(builder1.build(Environment.EXIST_ON));
			
			if(a==null){
				 throwException(UserErrorCode.Status.Code_001);
			}
			
			MapBuilder builder2 = MapBuilder.create().add("uuid", a.getUuid()).add("number", number);
			
			TEmployee employee= employeeDao.get(builder2.build(Environment.EXIST_ON));
			
			if(employee==null){
				 throwException(UserErrorCode.Status.Code_001);
			}
			
			String ming = RSAUtils.decryptByPrivateKey(employee.getPassword(), Environment.PRIVATE_KEY);
			
			if(!StringUtils.equals(ming, password)){		
				throwException(UserErrorCode.Status.Code_008);
			}
			
			return  CopyUtils.copy(a,UserModel.class );
		}
		
	}
	
	/**
	 * 获取用户账号
	 * @param user
	 * @return
	 */
	public UserModel getUser(String user,int status ,int exist){
		if(user==null){
			 throwException(UserErrorCode.Status.Code_002);
		}
		
		MapBuilder builder = 
				MapBuilder.create()
				.add("user", user)
				.status(status);
		
		TUser a = userDao.get(builder.build(exist));
		
		if(a==null){
			 throwException(UserErrorCode.Status.Code_001);
		}
		
		UserModel u  = CopyUtils.copy(a,UserModel.class );
		
		return u;
	}
	
	/**
	 * 添加用户
	 * @param user 用户账号
	 * @param password 用户密码
	 * @return
	 */
	public UserModel addUser(String user,String password,int status){
		MapBuilder builder = 
				MapBuilder.create()
				.add("user", user);
		
		TUser u = userDao.get(builder.build(Environment.EXIST_ON));
		
		if(u!=null){
			 throwException(UserErrorCode.Status.Code_006);
		}
		
		TUuid uuid = new TUuid();
		uuid.setUuid(StringUtils.UUID());
		uuid.setPassword(RSAUtils.encryptByPublicKey(password, Environment.PUBLIC_KEY));
		uuid.setStatus(Environment.STATUS_ON);
		uuidDao.save(uuid);
		
		TUser a = new TUser();
		a.setUuid(uuid.getUuid());
		a.setUser(user);
		a.setType(StringUtils.type(user));
		a.setStatus(status);
		userDao.save(a);
		return CopyUtils.copy(a, UserModel.class);
	}
	
	/**
	 * 绑定用户
	 * @param user 用户账号
	 * @param password 用户密码
	 */
	public UserModel addUserBind(String uuid,String user,int status){
		MapBuilder builder = 
				MapBuilder.create()
				.add("user", user);
		
		TUser u = userDao.get(builder.build(Environment.EXIST_ON));
		
		if(u!=null){
			 throwException(UserErrorCode.Status.Code_006);
		}
		
		MapBuilder builder1 = 
				MapBuilder.create()
				.add("uuid", uuid);
		
		TUuid uid= uuidDao.get(builder1.build(Environment.EXIST_ON));
		
		if(uid==null){
			 throwException(UserErrorCode.Status.Code_007);
		}
		
		TUser a = new TUser();
		a.setUuid(uid.getUuid());
		a.setUser(user);
		a.setType(StringUtils.type(user));
		a.setStatus(status);
		a.setExist(Environment.EXIST_ON);
		userDao.save(a);
		return CopyUtils.copy(a, UserModel.class);
		
	}
	
	/**
	 * 更新用户状态
	 * @param user 用户账号
	 * @param password 用户密码，密码格式：encrypt::密码MD5 
	 * 例如：使用RSA进行编码
	 * RSA::EACSDSDFSDS99889315
	 * @return
	 */
	public void updateUser(String uuid,String user,int status,int exist){
		MapBuilder builder = 
				MapBuilder.create()
				.add("uuid", uuid)
				.add("user", user);
		
		TUser a = userDao.get(builder.build(Environment.EXIST_ALL));
		
		if(a==null){
			 throwException(UserErrorCode.Status.Code_001);
		}
		
		if(status!=a.getStatus()) a.setStatus(status);
		if(exist==Environment.EXIST_ON||exist==Environment.EXIST_OFF) a.setExist(exist);
		
		userDao.update(a);	
	}
	
	/**
	 * 获取所有用户
	 */
	public List<UserModel> getUserList(String uuid,int exist){
		
		MapBuilder builder = 
				MapBuilder.create()
				.add("uuid", uuid);
				
		List<TUser> list = userDao.find(builder.build(exist));
		
		return CopyUtils.copy(list, UserModel.class);
	
	}
	

	
}
