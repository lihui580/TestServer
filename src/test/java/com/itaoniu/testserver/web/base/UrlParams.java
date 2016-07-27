package com.itaoniu.testserver.web.base;

import java.util.HashMap;
import java.util.Map;

public class UrlParams {
	public static UrlParams create() {
		return new UrlParams();
	}

	private UrlParams() {
		super();
	}

	private Map<String, String> map = new HashMap<String, String>();

	public UrlParams add(String name, String obj) {
		map.put(name, obj);
		return this;
	}

	public Map<String, String> build() {
		return map;
	}
}
