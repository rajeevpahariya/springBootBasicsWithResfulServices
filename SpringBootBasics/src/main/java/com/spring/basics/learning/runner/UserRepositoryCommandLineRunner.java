package com.spring.basics.learning.runner;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.basics.learning.jpa.User;
import com.spring.basics.learning.repository.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner{

	private static final Logger logger = 
			LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);
	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("Amar", "Sub-Admin");
		userRepository.save(user);
		logger.info("User Created : "+ user);
		
		Optional<User> userFindById = userRepository.findById(2L);
		logger.info("User found by id : "+ userFindById);
		
		List<User> users = userRepository.findAll();
		logger.info("All users : "+ users);
	}

}
