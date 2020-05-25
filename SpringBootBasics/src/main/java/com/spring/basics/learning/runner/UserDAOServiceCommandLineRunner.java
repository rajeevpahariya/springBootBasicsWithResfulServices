package com.spring.basics.learning.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.basics.learning.DAO.UserDAOService;
import com.spring.basics.learning.jpa.User;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner{

	private static final Logger logger = 
			LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);
	@Autowired
	private UserDAOService userService;
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("Rajeev", "Admin");
		userService.insert(user);
		logger.info("User Created : "+ user);
	}

}
