package com.rogerr.custom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rogerr.custom.dao.UserDao;
import com.rogerr.custom.model.Subscriber;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
		

	public List<Subscriber> findAllUsers() {
		return userDao.findAllUsers();
	}
	
	public Subscriber findById(Long id) {
		return userDao.findById(id);
	}
	
	public Subscriber findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public void saveUser(Subscriber user) {
	userDao.saveUser(user);
	}
	
	public void updateUser(Subscriber user) {
		 userDao.updateUser(user);
	}
	
	public void deleteUserById(Long id) {
		userDao.deleteUserById(id);
	}

	public boolean isUserExist(Subscriber user) {
		return userDao.isUserExist(user);
	}
	
	public List<Subscriber> deleteAllUsers(){
		return userDao.deleteAllUsers();
	}

	

}
