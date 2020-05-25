package com.rest.webservices.restfulwebservices.harcore.data;

import java.util.Date;

public class ExceptionBean {
	
	private  Date date;
	private String message;
	private String details;
	public Date getDate() {
		return date;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public ExceptionBean(Date date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}
}
