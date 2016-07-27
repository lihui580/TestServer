package com.itaoniu.testserver.session;

import java.util.Map;

public class Session {
	
	private String id;
	
	private Map<String,String> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	
}
