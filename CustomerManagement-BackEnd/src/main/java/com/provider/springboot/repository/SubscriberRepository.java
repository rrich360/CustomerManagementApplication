package com.provider.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provider.springboot.entity.SubscriberImpl;

public interface SubscriberRepository extends JpaRepository<SubscriberImpl, Long> {
	
	public SubscriberImpl findByUsername(String username);
	
	public SubscriberImpl getById(Long id);

}
