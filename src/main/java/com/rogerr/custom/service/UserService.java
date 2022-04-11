package com.rogerr.custom.service;

import java.util.List;

import com.rogerr.custom.model.User;



public interface UserService {
	
	User findById(Long id);

	//User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	List<User> findAllUsers();

	List<User> deleteAllUsers();

	public boolean isUserExist(User user);


}
