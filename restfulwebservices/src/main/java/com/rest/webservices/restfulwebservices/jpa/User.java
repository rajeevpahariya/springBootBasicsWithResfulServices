package com.rest.webservices.restfulwebservices.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about User")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message = "Name must contain at least 2 Characters")
	@ApiModelProperty(notes = "Name should have at least 2 chanracters")
	private String name;
	
	@Past(message = "User's DOB should be past date")
	@ApiModelProperty(notes = "User's DOB should be past date")
	private Date dbo;
	
	@ManyToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User(Integer id, String name, Date dbo) {
		super();
		this.id = id;
		this.name = name;
		this.dbo = dbo;
	}
	
	public User() {
		super();
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
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dbo=" + dbo + "]";
	}
	
}
