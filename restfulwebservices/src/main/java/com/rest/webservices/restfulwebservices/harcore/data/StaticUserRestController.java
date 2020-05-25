package com.rest.webservices.restfulwebservices.harcore.data;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;

@RestController
public class StaticUserRestController {
	
	@Autowired
	private StaticUserDaoService service;
	
	@GetMapping(path = "/users")
	@ApiOperation(value = "Get All Users", response = List.class)
	public List<StaticUser> getAllUser() {
		return service.getAllUsers();
	}
	
	@GetMapping(path = "/users/{id}")
	@ApiOperation(value = "Get User by Id", response = StaticUser.class)
	public StaticUser getUserById(@PathVariable Integer id) {
		// added code to handle the excepton if user not found.
		StaticUser findUserById = service.findUserById(id);
		if(findUserById == null)
			throw new StaticUserNotFoundException("User not found for ID : "+ id);
		return findUserById;
	}
	
	// HATEOS Example to send link to api for all user api
	@GetMapping(path = "/user/{id}")
	@ApiOperation(value = "Get User by Id with HATEOS", response = Resource.class)
	public Resource<StaticUser> retrieveUser(@PathVariable Integer id) {
		// added code to handle the excepton if user not found.
		StaticUser user = service.findUserById(id);
		if(user == null)
			throw new StaticUserNotFoundException("User not found for ID : "+ id);
		Resource<StaticUser> resource = new Resource<StaticUser>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(this.getClass()).getAllUser());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@DeleteMapping(path = "/users/{id}")
	@ApiOperation(value = "Delete User by Id")
	public void deleteUserById(@PathVariable Integer id) {
		// added code to handle the excepton if user not found.
		StaticUser deleteUser = service.deleteUserById(id);
		if(deleteUser == null)
			throw new StaticUserNotFoundException("User not found for ID : "+ id);
	}
	
	// save user is not returning any status whether user added successfully or not.
	@PostMapping(path = "/users")
	@ApiOperation(value = "Post User")
	public void saveUser(@RequestBody StaticUser user) {
		service.saveUser(user);
	}
	
	// This api will return status coded as 201 which is for create any record. HTTP best practice
	// Header response will have location=localhost:8080/createUser/4
	@PostMapping(path = "/createUser")
	@ApiOperation(value = "Create User with Request Body and Validation", response = ResponseEntity.class)
	public ResponseEntity<Object> createUser(@Valid @RequestBody StaticUser user) {
		StaticUser savedUser = service.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
}
