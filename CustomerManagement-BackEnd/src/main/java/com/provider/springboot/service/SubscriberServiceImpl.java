package com.provider.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.provider.springboot.api.Subscriber;
import com.provider.springboot.entity.SubscriberImpl;
import com.provider.springboot.repository.SubscriberRepository;

@Service
@Transactional
public class SubscriberServiceImpl implements SubscriberService { 
	
	@Autowired
	private SubscriberRepository subscriberRepo;
	
	//create custom getById() in repo to handle deprecated getById() in JPA 
	@Override
	public Subscriber findById(Long id) {
		return subscriberRepo.getById(id);
	}
	
	@Override
	public Subscriber findByUsername(String username) {
		return subscriberRepo.findByUsername(username);
	}
	
	@Override
	public Subscriber saveUser(Subscriber user) {
		if (user == null) {
			throw new RuntimeException("No subscriber provided to save");
		}
		return subscriberRepo.save(SubscriberImpl.convert(user));
	}
	
	@Override
	public Subscriber updateUser(Subscriber user) {
		if (user == null) {
			throw new RuntimeException("No subscriber is provided to update");
		}
		SubscriberImpl updatedUser = SubscriberImpl.convert(user);
		return subscriberRepo.save(updatedUser);
	}
	
	@Override
	public void deleteUserById(Long id) {
		if (id == null) {
			throw new RuntimeException("No subscriber id is provided");
		}
		subscriberRepo.deleteById(id);
	}
	
	@Override
	public List<Subscriber> findAllUsers(){
		return ImmutableList.copyOf(subscriberRepo.findAll());
	}
	
	@Override
	public boolean isUserExist(Subscriber subscriber) {
		Subscriber foundUser = subscriberRepo.findByUsername(subscriber.getUsername());
		if (foundUser == null) {
			return false;
		}
		return true;
	}
	
}
