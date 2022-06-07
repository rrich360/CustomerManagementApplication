package com.provider.springboot.service;

import java.util.List;

import com.provider.springboot.api.Subscriber;



public interface SubscriberService {
	
	Subscriber findById(Long id);

	Subscriber findByUsername(String username);

	Subscriber saveUser(Subscriber user);

	Subscriber updateUser(Subscriber user);

	void deleteUserById(Long id);

	List<Subscriber> findAllUsers();

	//void deleteAllUsers(List<SubscriberView> SubscriberList);

	boolean isUserExist(Subscriber user);
}
