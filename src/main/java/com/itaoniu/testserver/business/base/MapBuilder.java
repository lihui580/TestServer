package com.itaoniu.testserver.business.base;

import java.util.HashMap;
import java.util.Map;

import com.itaoniu.testserver.utils.Environment;

public final class MapBuilder {

	public static MapBuilder create() {
		return new MapBuilder();
	}

	private MapBuilder() {
		super();
	}

	private Map<String, Object> map = new HashMap<String, Object>();

	public MapBuilder add(String name, Object obj) {
		map.put(name, obj);
		return this;
	}
	
	public MapBuilder status(int status){
		switch(status){
		case Environment.STATUS_ON:
		case Environment.STATUS_OFF:
		case Environment.STATUS_UNBIND:
			map.put("status",status);
			break;
		default:
			map.remove("status");
		}
		return  this;
	}

	public Map<String, Object> build(int exist) {
		switch(exist){
		case Environment.EXIST_ON:
		case Environment.EXIST_OFF:
			map.put("exist",exist);
			break;
		default:
			map.remove("exist");
		}	
		return map;
	}

}
