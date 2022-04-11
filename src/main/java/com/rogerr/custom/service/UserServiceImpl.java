package com.rogerr.custom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rogerr.custom.dao.UserDao;
import com.rogerr.custom.model.User;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
		

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}
	
	public User findById(Long id) {
		return userDao.findById(id);
	}
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public void saveUser(User user) {
	userDao.saveUser(user);
	}
	
	public void updateUser(User user) {
		 userDao.updateUser(user);
	}
	
	public void deleteUserById(Long id) {
		userDao.deleteUserById(id);
	}

	public boolean isUserExist(User user) {
		return userDao.isUserExist(user);
	}
	
	public List<User> deleteAllUsers(){
		return userDao.deleteAllUsers();
	}

	

}
