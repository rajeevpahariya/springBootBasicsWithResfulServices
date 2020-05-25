package com.rest.webservices.restfulwebservices.versoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	@GetMapping("v1/person")
	public Person1 getPerson1() {
		return new Person1("Rajeev Gupta");
	}
	
	@GetMapping("v2/person")
	public Person2 getPerson2() {
		return new Person2(new Name("Rajeev","Gupta"));
	}
	
	@GetMapping(value = "/person/param", params = "version=1")
	public Person1 getParam1() {
		return new Person1("Rajeev Gupta");
	}
	
	@GetMapping(value = "/person/param", params = "version=2")
	public Person2 getParam2() {
		return new Person2(new Name("Rajeev","Gupta"));
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public Person1 getHeader1() {
		return new Person1("Rajeev Gupta");
	}
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public Person2 getHeadedr2() {
		return new Person2(new Name("Rajeev","Gupta"));
	}
	
	
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public Person1 getProduces1() {
		return new Person1("Rajeev Gupta");
	}
	
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public Person2 getProduces2() {
		return new Person2(new Name("Rajeev","Gupta"));
	}
}
