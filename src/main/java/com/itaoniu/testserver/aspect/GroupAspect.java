package com.itaoniu.testserver.aspect;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.itaoniu.testserver.exception.BusinessException;
import com.itaoniu.testserver.utils.CxfUtils;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.utils.RSAUtils;
import com.itaoniu.testserver.utils.ValidatorUtils;
import com.itaoniu.testserver.utils.RSAUtils.RSAKey;
import com.itaoniu.testserver.view.webserver.reset.Result;

@Aspect
@Component
public class GroupAspect implements Ordered{

	@Pointcut("execution(* com.itaoniu.testserver.view.webserver.reset.v1..*.*(..))")
	public void group() {
	}

	@Around("group()")
	public Result<Object> around(JoinPoint joinPoint) {

		
		try {
			//入参判断
			Object[] objects = joinPoint.getArgs();
			for(Object obj :objects){
				if(ValidatorUtils.hasError(obj)){
					return Result.buildError(Response.Status.BAD_REQUEST,ValidatorUtils.getErrorMessage(obj)); 
				}
			}
		
			return (Result<Object>)((ProceedingJoinPoint) joinPoint).proceed();
			
		} catch (Throwable e) {
			//错误返回
			if (e instanceof BusinessException) {
				BusinessException ex = (BusinessException) e;
				return Result.buildError(Response.Status.SERVICE_UNAVAILABLE,ex);
				
			}else{
				return Result.buildError(Response.Status.SERVICE_UNAVAILABLE,e.toString());
				
			}
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
