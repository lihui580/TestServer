package com.itaoniu.testserver.session;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.itaoniu.testserver.utils.CxfUtils;
import com.itaoniu.testserver.utils.RedisUtils;
import com.itaoniu.testserver.utils.StringUtils;

public final class SessionProxy {
	
	private final  static String SESSION_NAME = "SESSION";
	private final static int expire = 30*60;
	
	public static void put(SessionProxy proxy){
		RedisUtils.put(SessionProxy.buildSessionKey(proxy.getId()), proxy.getSession(), expire);
	}
	
	public static SessionProxy get(String session){
		try{
			if(session==null) return null;
			Session s = RedisUtils.get(SessionProxy.buildSessionKey(session), Session.class);
			if(s==null) return null;
			return new SessionProxy(s);
		}catch(Exception e){
			RedisUtils.delete(SessionProxy.buildSessionKey(session));
			return null;
		}
	}
	
	public static void delete(SessionProxy proxy){
		RedisUtils.delete((SessionProxy.buildSessionKey(proxy.getId())));
	}
	
	public static void delete(String session){
		RedisUtils.delete((SessionProxy.buildSessionKey(session)));
	}
	
	public static void expire(String session){
		RedisUtils.expire(SessionProxy.buildSessionKey(session), expire);
	}
	
	public static void expire(SessionProxy proxy){
		RedisUtils.expire(SessionProxy.buildSessionKey(proxy.getId()), expire);
	}
	
	

	private static String buildSessionKey(String key){
		return SESSION_NAME+":"+key;
	}
	
	private final Session session;
	
	
	public SessionProxy() {
		super();
		session = new Session();
		this.session.setId(StringUtils.UUID());
		this.session.setData(new HashMap<String, String>());
	}
	
	public SessionProxy(Session session) {
		super();
		this.session=session;
	}

	public Session getSession() {
		return session;
	}
	
	public String getId() {
		return session.getId();
	}

	public <T> void putAttr(String key,T value){
		if(value instanceof String){
			this.session.getData().put(key, (String)value);
			
		}else{
			this.session.getData().put(key, JSON.toJSONString(value));

		}
		SessionProxy.put(this);
		
	}
	
	public void deleteAttr(String key){
		this.session.getData().remove(key);
		SessionProxy.put(this);		
	}
	
	
	public <T> T getAttr(String key,Class<T> clazz){
		String str =this.session.getData().get(key);
		return JSON.parseObject(str, clazz);
	}
	

}
