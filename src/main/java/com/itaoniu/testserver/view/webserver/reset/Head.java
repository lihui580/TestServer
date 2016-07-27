package com.itaoniu.testserver.view.webserver.reset;

public class Head {

	public static Head build(long code, String msg) {
		Head e = new Head();
		e.setCode(code);
		e.setMsg(msg);
		return e;

	}

	private long code;

	private String msg;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
