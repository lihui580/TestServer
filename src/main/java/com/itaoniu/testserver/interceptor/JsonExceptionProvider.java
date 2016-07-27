package com.itaoniu.testserver.interceptor;

import java.util.Locale;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.itaoniu.testserver.view.webserver.reset.Result;


public class JsonExceptionProvider implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception exception) {
		if(exception instanceof JsonParseException||exception instanceof JsonMappingException){
			return buildResponse(Response.Status.BAD_REQUEST,"输入参数解析失败");	
			
		}else if(exception instanceof ClientErrorException){
			return buildResponse(Response.Status.EXPECTATION_FAILED,"未找到相应接口");
			
		}else{
			return buildResponse(Response.Status.BAD_REQUEST, exception.getMessage());
			
		}
	}

	private Response buildResponse(Response.Status status,String errorMsg){
		Result<Object> result = Result.buildError(status,errorMsg);
		ResponseBuilder rb = Response.status(status);
		rb.type("application/json;charset=UTF-8");
		rb.entity(result);
		rb.language(Locale.SIMPLIFIED_CHINESE);
		Response r = rb.build();
		return r;
	}
	

}
