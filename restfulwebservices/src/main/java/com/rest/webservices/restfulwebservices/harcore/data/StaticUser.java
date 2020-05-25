package com.rest.webservices.restfulwebservices.harcore.data;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about User")
public class StaticUser {
	
	private Integer id;
	
	@Size(min = 2, message = "Name must contain at least 2 Characters")
	@ApiModelProperty(notes = "Name should have at least 2 chanracters")
	private String name;
	
	@Past(message = "User's DOB should be past date")
	@ApiModelProperty(notes = "User's DOB should be past date")
	private Date dbo;
	
	public StaticUser(Integer id, String name, Date dbo) {
		super();
		this.id = id;
		this.name = name;
		this.dbo = dbo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDbo() {
		return dbo;
	}
	public void setDbo(Date dbo) {
		this.dbo = dbo;
	}
	@Override
	public String toString() {
		return "StaticUser [id=" + id + ", name=" + name + ", dbo=" + dbo + "]";
	}
	
}
