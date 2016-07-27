package com.itaoniu.testserver.web.tn;

import java.net.SocketTimeoutException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.http.conn.ConnectTimeoutException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itaoniu.testserver.utils.HttpClientUtils;
import com.itaoniu.testserver.utils.RSAUtils;
import com.itaoniu.testserver.utils.RSAUtils.RSAKey;
import com.itaoniu.testserver.view.webserver.reset.v1.users.params.RegistParams;
import com.itaoniu.testserver.web.base.BaseTest;
import com.itaoniu.testserver.web.base.UrlParams;

public class LoginTest extends BaseTest{
	
	public final static  String account ="15577224200";
	
	public static String password="123456";
	
	public final static String UUID = "3A71AA0279634D718ECF5503D4748FDF";
	
	public final static String base = "http://127.0.0.1:8070";
	
	public final static LoginTest test = new LoginTest();
	

	@Before
	public void before() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		test.perlogin();
		RSAKey rsaKey = new RSAKey(HttpClientUtils.getMODULUS().getValue(),HttpClientUtils.getEXPONENT().getValue());
		test.login(account,RSAUtils.encryptByPublicKey(password, rsaKey));
	}
	
	@After
	public void after() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		test.logout();
	}
	
	@Test
	public void test(){}
	
	
	@Test
	@POST
	@Path(base+"/testserver/reset/tn/regist")
	public void regist() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		RegistParams params = new RegistParams();
		params.setAccount(account);
		params.setPassword(password);
		invoke(params);
	}
	
	@GET
	@Path(base+"/testserver/reset/tn/perlogin")
	public void perlogin() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		 invoke("");
	}
	
	@GET
	@Path(base+"/testserver/reset/tn/login")
	public void login(String account,String password) throws ConnectTimeoutException, SocketTimeoutException, Exception{
		UrlParams params = UrlParams.create().add("account", account).add("password", password);
		invoke(params);
	}
	
	@DELETE
	@Path(base+"/testserver/reset/tn/logout")
	public void logout() throws ConnectTimeoutException, SocketTimeoutException, Exception{
		 invoke("");
	}
	
}
