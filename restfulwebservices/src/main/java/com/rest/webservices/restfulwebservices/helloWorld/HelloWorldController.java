package com.rest.webservices.restfulwebservices.helloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Hello World", description = "Hello World Conrtoller for bigenner")
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//@RequestMapping(method = RequestMethod.GET , path = "/hello-world")
	@GetMapping(path = "/hello-world")
	@ApiOperation(value = "Get Hellow World API", response = String.class)
	public String getHelloWorld() {
		return "Hello World!!!!!";
	}
	
	@GetMapping(path = "/hello-world-bean")
	@ApiOperation(value = "Get Hellow World API from Bean", response = HelloWorldBean.class)
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean!");
	}
	
	// Path variable ex
	@GetMapping(path = "/hello-world/{name}")
	@ApiOperation(value = "Get Hellow World With Path Variable", response = HelloWorldBean.class)
	public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
	
	// Hello World Internalization
	@GetMapping(path = "/hello-world-int")
	@ApiOperation(value = "Get Hellow World Internationalization", response = String.class)
	public String getHelloWorldinternalization(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	// If we have used acceptHeaderLocaleResolver
	@GetMapping(path = "/hello-world-int-header")
	@ApiOperation(value = "Get Hellow World Internationalization with header", response = String.class)
	public String getHelloWorldinternalizationWithAcceptHeader() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
