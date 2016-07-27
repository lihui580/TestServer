package com.itaoniu.testserver.business.users.services;

import java.util.Date;

import com.itaoniu.testserver.business.base.MapBuilder;
import com.itaoniu.testserver.business.users.model.UserLogModel;
import com.itaoniu.testserver.persist.dao.base.IEntityDao;
import com.itaoniu.testserver.persist.pojo.TUserLog;
import com.itaoniu.testserver.utils.CopyUtils;
import com.itaoniu.testserver.utils.Environment;

public class UserLogsService {

	private IEntityDao<TUserLog> userLogDao;

	public IEntityDao<TUserLog> getUserLogDao() {
		return userLogDao;
	}

	public void setUserLogDao(IEntityDao<TUserLog> userLogDao) {
		this.userLogDao = userLogDao;
	}

	/**
	 * 通过用户和用户的客户端获取最后
	 */
	public UserLogModel getLastUserLog(String user,String operation, String userAgent){
		MapBuilder mb = MapBuilder.create().add("user", user).add("operation", operation).add("userAgent", userAgent);
		TUserLog source = userLogDao.get(mb.build(Environment.EXIST_ON),"updateTime","desc");
		return CopyUtils.copy(source, UserLogModel.class);		
	}
	
	/**
	 * 通过用户和用户的客户端获取最后
	 */
	public UserLogModel getLastUserLogByToken(String operation, String token){
		MapBuilder mb = MapBuilder.create().add("token", token).add("operation", operation);
		TUserLog source = userLogDao.get(mb.build(Environment.EXIST_ON),"updateTime","desc");
		return CopyUtils.copy(source, UserLogModel.class);		
	}
	
	
	/**
	 * 添加用户信息
	 * @param user
	 * @param image
	 * @param nickname
	 * @param signature
	 */
	public void addUserLog( String uuid,String user,String type,String session,String token,String platform,String operation, String address,Date expire){
		TUserLog log = new TUserLog();
		log.setUuid(uuid);
		log.setUser(user);
		log.setType(type);
		log.setSession(session);
		log.setToken(token);
		log.setUserAgent(platform);
		log.setOperation(operation);
		log.setAddress(address);
		log.setExpire(expire);
		userLogDao.save(log);
		
	}
	
}
