package com.rogerr.custom.service;

import java.util.List;

import com.rogerr.custom.model.User;



public interface UserService {
	
	User findById(long id);

	User findByName(String name);

	User saveUser(User user);

	User updateUser(User user);

	Long deleteUserById(long id);

	List<User> findAllUsers();

	List<User> deleteAllUsers();

	public boolean isUserExist(User user);


}
