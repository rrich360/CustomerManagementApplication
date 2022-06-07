package com.rogerr.custom.manager;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rogerr.custom.model.SubscriberView;

public interface SubscriberManager {

	ResponseEntity<SubscriberView> findById(Long id);

	ResponseEntity<SubscriberView> findByUsername(String username);

	ResponseEntity<SubscriberView> saveUser(SubscriberView user);

	ResponseEntity<SubscriberView> updateUser(SubscriberView user);

	void deleteUserById(Long id);

	ResponseEntity<SubscriberView[]> findAllUsers();

	void deleteAllUsers(List<SubscriberView> SubscriberList);

	Boolean isUserExist(SubscriberView user);
}
