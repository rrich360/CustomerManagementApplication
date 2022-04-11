package com.rogerr.custom.dao;

import java.util.List;

import com.rogerr.custom.model.User;

public interface UserDao {

	
	 User findById(Long id);

		User findByUsername(String username);

		void saveUser(User user);

		void updateUser(User user);

		void deleteUserById(Long id);

		List<User> findAllUsers();

		List<User> deleteAllUsers();

		public Boolean isUserExist(User user);

}
