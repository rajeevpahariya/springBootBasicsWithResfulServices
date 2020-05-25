package com.spring.basics.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBasicsApplication {

	public static void main(String[] args) {
		//ApplicationContext context = 
				SpringApplication.run(SpringBootBasicsApplication.class, args);
		/*for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}*/
	}

}
