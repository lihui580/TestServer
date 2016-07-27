package com.itaoniu.testserver.aspect;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.itaoniu.testserver.business.users.model.UserLogModel;
import com.itaoniu.testserver.business.users.model.UserModel;
import com.itaoniu.testserver.business.users.services.UserLogsService;
import com.itaoniu.testserver.exception.BusinessException;
import com.itaoniu.testserver.session.SessionUtils;
import com.itaoniu.testserver.utils.CxfUtils;
import com.itaoniu.testserver.utils.GMTTimeUtils;
import com.itaoniu.testserver.utils.StringUtils;
import com.itaoniu.testserver.view.webserver.reset.Result;


/**
 * 
 * @author lihui
 * 需要用到登陆验证的接口必须遵守
 * 方法必须标示@Auth
 * 方法第一个参数 必须是uuid 且 带有@TNID
 */
@Aspect
@Component
public class AuthAspect implements Ordered{
	
	private UserLogsService userLogsService;

	public UserLogsService getUserLogsService() {
		return userLogsService;
	}

	public void setUserLogsService(UserLogsService userLogsService) {
		this.userLogsService = userLogsService;
	}

	@Pointcut("execution( * com.itaoniu.testserver.view.webserver.reset.v1.tn.LoginImpl.login(..))")
	public void login() {
	}
	
	@Before(value = "login()&&args(account, password)")
	public void loginBefore(JoinPoint joinPoint, String account, String password){

//		String userAgent = CxfUtils.getHeader("User-Agent");
//		String token = CxfUtils.getToken();
//		
//		
//		// 1.获取该类型的客户端最后登录的信息
//		UserLogModel log = StringUtils.isEmpty(token) ? null : userLogsService.getLastUserLog(account, "login",userAgent);
//		
//		if(log==null) return;
//		
//		if(!StringUtils.equals(token, log.getToken()) ||log.getExpire().compareTo(new Date()) < 0){
//			throw new BusinessException(AspectErrorCode.Status.Code_001);
//		}
		
	}
	

	@AfterReturning(value = "login()&&args(account, password)",returning="result")
	public void loginAround(JoinPoint joinPoint, String account, String password,Result<UserModel> result) {
		
		String session = SessionUtils.obtainSession().getId();
		String userAgent = CxfUtils.getHeader("User-Agent");
		String address = CxfUtils.getRemoteAddr();
		String token = "无用";
		UserModel model = result.getBody().getT();
		String[] str = account.split(":");
		String type="";
		if(str.length==2){
			type="子账号";
		}else{
			type="主账号";
		}
		
		// 1.获取该类型的客户端最后登录的信息
		UserLogModel log = StringUtils.isEmpty(token) ? null : userLogsService.getLastUserLog(account, "login",userAgent);

		// 登陆过
		if(log==null){
		
			//没有登录过
			//CxfUtils.putToken(token = StringUtils.UUID());
			userLogsService.addUserLog(model.getUuid(),account, type,session, token, userAgent, "login", address, GMTTimeUtils.calc(new Date(), Calendar.DAY_OF_MONTH, 7));
		
		}else if (StringUtils.equals(token, log.getToken()) && log.getExpire().compareTo(new Date()) > 0) {
			// 1.最后登录的token与传入token一致，且没有超时,那么增加超时时间
			userLogsService.addUserLog(model.getUuid(),account, type,session, token, userAgent, "login", address, GMTTimeUtils.calc(new Date(), Calendar.DAY_OF_MONTH, 7));
		
		}
		
	}
	
	@Pointcut("execution(@com.itaoniu.testserver.annotation.Auth * com.itaoniu.testserver.view.webserver.reset.v1..*.*(@com.itaoniu.testserver.annotation.TNID (java.lang.String),..))")
	public void auth() {
	}
	
	
	@Before(value="auth()&&args(uuid,..)")
	public void authBefore(JoinPoint joinPoint,String uuid) {
//		String token = CxfUtils.getToken();
//		// 1.获取该类型的客户端最后登录的信息
//		UserLogModel log = StringUtils.isEmpty(token) ? null : userLogsService.getLastUserLogByToken("login", token);
//		if(log==null){
//			//没有登录
//			throw new BusinessException(AspectErrorCode.Status.Code_002);
//			
//		}else if(log.getExpire().compareTo(new Date())<0){
//			throw new BusinessException(AspectErrorCode.Status.Code_001);
//			
//		}
//		else if(!StringUtils.equals(log.getUuid(), uuid)){
//			throw new BusinessException(AspectErrorCode.Status.Code_003);
//		}
	}
	
	@Override
	public int getOrder() {
		return 2;
	}
}
