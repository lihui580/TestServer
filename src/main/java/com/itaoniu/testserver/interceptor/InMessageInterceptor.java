package com.itaoniu.testserver.interceptor;


import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.itaoniu.testserver.session.SessionProxy;
import com.itaoniu.testserver.session.SessionUtils;
import com.itaoniu.testserver.utils.CxfUtils;
import com.itaoniu.testserver.utils.Environment;
import com.itaoniu.testserver.utils.RSAUtils;
import com.itaoniu.testserver.utils.RSAUtils.RSAKey;

public class InMessageInterceptor extends AbstractPhaseInterceptor<Message> {
	
	public InMessageInterceptor() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		SessionProxy session = SessionUtils.obtainSession();
		RSAKey pubk = session.getAttr("public", RSAKey.class);
		RSAKey prik = session.getAttr("private", RSAKey.class);
		if(pubk==null||prik==null){
			Map<String, RSAKey> map = RSAUtils.getRSAKeys();
			session.putAttr("public",map.get("public"));
			session.putAttr("private",map.get("private"));
			CxfUtils.getHttpServletResponse().addHeader(Environment.MODULUS_NAME, map.get("public").getModulus());
			CxfUtils.getHttpServletResponse().addHeader(Environment.EXPONENT_NAME, map.get("public").getExponent());
		}
	}
	
	
}
