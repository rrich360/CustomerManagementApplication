package com.rogerr.custom.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.rogerr.custom.model.User;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateSubscribers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public User saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
		return user;
	}

	public User updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
		return user;
	}

	public Long deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
				return id;
		    }
		}
		return null;
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public List<User> deleteAllUsers(){
		users.clear();
		return users;
	}

	private static List<User> populateSubscribers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Alicia Keys", "New York", "Alicia.PianoKeys@gmail.com"));
		users.add(new User(counter.incrementAndGet(),"Taylor Russell", "London", "Panic.Room19@gmail.com"));
		users.add(new User(counter.incrementAndGet(),"Tom Cruise", "Los Angeles, California", "Top.Gun@gmail.com"));
		return users;
	}

}
