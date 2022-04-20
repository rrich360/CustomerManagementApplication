package com.rogerr.custom.dao;

import java.util.List;

import com.rogerr.custom.model.Subscriber;

public interface UserDao {

	
	 Subscriber findById(Long id);

		Subscriber findByUsername(String username);

		void saveUser(Subscriber user);

		void updateUser(Subscriber user);

		void deleteUserById(Long id);

		List<Subscriber> findAllUsers();

		List<Subscriber> deleteAllUsers();

		public Boolean isUserExist(Subscriber user);

}
