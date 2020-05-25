package com.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Configuration;

// POST and DELETE API was not working when enabled the security.
// CSRF is enabled by default so need to disable
// CSRF = Cross Site Request Forgery
@Configuration
public class SecurityConfig //extends WebSecurityConfigurerAdapter
{
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .csrf().disable() .authorizeRequests() .anyRequest().authenticated() .and()
	 * .httpBasic(); }
	 */
}
