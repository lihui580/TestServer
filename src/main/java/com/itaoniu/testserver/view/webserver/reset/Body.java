package com.itaoniu.testserver.view.webserver.reset;

public class Body<T> {

	public static <T> Body<T> build(T t) {
		Body<T> body = new Body<T>();
		body.setT(t);
		return body;
	}
	
	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

}
