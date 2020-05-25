package com.rest.webservices.restfulwebservices.helloWorld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message=message;
	}

	/*
	 * If no getter method created then will get below error
	 * org.springframework.http.converter.HttpMessageNotWritableException: No
	 * converter found for return value of type: class
	 * com.rest.webservices.restfulwebservices.HelloWorldBean
	 */
	
	public String getMessage() { 
		return message; 
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
}
