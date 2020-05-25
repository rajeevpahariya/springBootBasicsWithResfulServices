package com.rest.webservices.restfulwebservices.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservices.harcore.data.StaticUser;
import com.rest.webservices.restfulwebservices.harcore.data.StaticUserNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserRestController {
	
	@Autowired
	private UserRepositoryService userRepository;
	@Autowired
	private PostRepositoryService postRepository;
	
	@GetMapping(path = "/jpa/users")
	@ApiOperation(value = "Get All Users", response = List.class)
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/jpa/user/{id}")
	@ApiOperation(value = "Get User by Id", response = User.class)
	public User getUserById(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new StaticUserNotFoundException("User not found for ID : "+ id);
		return user.get();
	}
	
	@DeleteMapping(path = "/jpa/user/{id}")
	@ApiOperation(value = "Delete User by Id")
	public void deleteUserById(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping(path = "/jpa/createUser")
	@ApiOperation(value = "Create User with Request Body and Validation", response = ResponseEntity.class)
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping(path = "/jpa/user/{id}/posts")
	@ApiOperation(value = "Get Posts for User", response = List.class)
	public List<Post> getPostByUserId(@PathVariable Integer id) {
		Optional<User> optUser = userRepository.findById(id);
		if(!optUser.isPresent())
			throw new StaticUserNotFoundException("User not found for ID : "+ id);
		return optUser.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/user/{id}/post")
	@ApiOperation(value = "Create User with Request Body and Validation", response = ResponseEntity.class)
	public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post) {
		Optional<User> optUser = userRepository.findById(id);
		if(!optUser.isPresent())
			throw new StaticUserNotFoundException("User not found for ID : "+ id);
		User user = optUser.get();
		post.setUser(user);
		postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
}
