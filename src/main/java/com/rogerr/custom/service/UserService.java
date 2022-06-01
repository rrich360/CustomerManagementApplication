package com.rogerr.custom.service;

import java.util.List;

import com.rogerr.custom.model.Subscriber;



public interface UserService {
	
	Subscriber findById(Long id);

	//User findByName(String name);

	void saveUser(Subscriber user);

	void updateUser(Subscriber user);

	void deleteUserById(Long id);

	List<Subscriber> findAllUsers();

	List<Subscriber> deleteAllUsers();

	public boolean isUserExist(Subscriber user);


}
