package com.itaoniu.testserver.view.webserver.soap;

import javax.jws.WebService;

import org.springframework.web.bind.annotation.RequestMapping;

@WebService
public interface HelloWebService {
	
	public void say(String value);
	
}
