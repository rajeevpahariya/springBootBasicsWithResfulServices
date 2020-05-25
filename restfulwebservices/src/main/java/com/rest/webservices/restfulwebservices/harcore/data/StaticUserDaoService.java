package com.rest.webservices.restfulwebservices.harcore.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class StaticUserDaoService {
	
	private static List<StaticUser> users = new ArrayList<StaticUser>();
	private static int counter = 3;
	
	static {
		users.add(new StaticUser(1, "Rajeev", new Date()));
		users.add(new StaticUser(2, "Amar", new Date()));
		users.add(new StaticUser(3, "Swadesh", new Date()));
	}
	
	public List<StaticUser> getAllUsers(){
		return users;
	}
	
	public StaticUser saveUser(StaticUser user) {
		if(user.getId() == null) {
			user.setId(++counter);
		}
		users.add(user);
		return user;
	}
	
	public StaticUser findUserById(Integer id) {
		Optional<StaticUser> findFirst = users.stream()
			.filter(u -> u.getId() == id)
			.findFirst();
		if(findFirst.isPresent()) {
			return findFirst.get();
		}else {
			return null;
		}
	}
	
	public StaticUser deleteUserById(Integer id) {
		Iterator<StaticUser> iterator = users.iterator();
		while(iterator.hasNext()) {
			StaticUser user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
