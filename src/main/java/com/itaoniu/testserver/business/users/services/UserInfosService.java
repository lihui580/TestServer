package com.itaoniu.testserver.business.users.services;

import com.itaoniu.testserver.business.base.BaseBusiness;
import com.itaoniu.testserver.business.base.MapBuilder;
import com.itaoniu.testserver.business.users.UserErrorCode;
import com.itaoniu.testserver.business.users.model.UserInfoModel;
import com.itaoniu.testserver.persist.dao.base.IEntityDao;
import com.itaoniu.testserver.persist.pojo.TUserInfo;
import com.itaoniu.testserver.utils.CopyUtils;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.utils.StringUtils;

public class UserInfosService extends BaseBusiness<UserErrorCode>{

	private IEntityDao<TUserInfo> userInfoDao;
	
	public IEntityDao<TUserInfo> getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(IEntityDao<TUserInfo> userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	
	/**
	 * 获取用户信息
	 */
	public UserInfoModel getUserInfo(String uuid,int exist){
		if(uuid==null){
			 throwException(UserErrorCode.Status.Code_007);
		}
		
		MapBuilder builder = 
				MapBuilder.create()
				.add("uuid", uuid);
		
		TUserInfo info = userInfoDao.get(builder.build(exist));
		
		if(info==null){
			 throwException(UserErrorCode.Status.Code_009);
		}
		
		return CopyUtils.copy(info, UserInfoModel.class);
		
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @param image
	 * @param nickname
	 * @param signature
	 */
	public UserInfoModel updateUserInfo(String uuid,String image,String nickname,String signature){
		if(uuid==null){
			 throwException(UserErrorCode.Status.Code_007);
		}
		
		MapBuilder builder = 
				MapBuilder.create()
				.add("uuid", uuid);
		
		TUserInfo info = userInfoDao.get(builder.build(Environment.EXIST_ON));
		
		if(info==null){
			info = new TUserInfo();
			
			info.setUuid(uuid);
			
			info.setNickname(StringUtils.notNull(image));
			
			info.setImage(StringUtils.notNull(nickname));
			
			info.setSignature(StringUtils.notNull(signature));
			
			userInfoDao.save(info);
			
		}else{
			if(!StringUtils.isEmpty(nickname)) info.setNickname(nickname);
			
			if(!StringUtils.isEmpty(nickname)) info.setImage(image);
			
			if(!StringUtils.isEmpty(nickname)) info.setSignature(signature);
			
			userInfoDao.update(info);
			
		}

		return CopyUtils.copy(info, UserInfoModel.class);
	}
}
