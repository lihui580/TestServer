package com.itaoniu.testserver.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class OutMessageInterceptor extends AbstractPhaseInterceptor<Message>{

	public OutMessageInterceptor() {
		super(Phase.SEND);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
	}
}
