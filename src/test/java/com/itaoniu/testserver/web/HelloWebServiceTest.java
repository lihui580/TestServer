package com.itaoniu.testserver.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

import com.itaoniu.testserver.utils.GMTTimeUtils;

public class HelloWebServiceTest {

	@Test
	public void testSay(){
//		JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
//		svr.setServiceClass(HelloWebService.class);
//		svr.setAddress("http://127.0.0.1:8070/testserver/services/soap/HelloWebService");
//		HelloWebService ws = (HelloWebService) svr.create();
//		ws.say("are you ok");
		Date curDate = new Date();
		DateFormat format=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		System.out.println(format.format(curDate));
		try {
			TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
			Date d = GMTTimeUtils.toDate(format.format(curDate));
			System.out.print(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
