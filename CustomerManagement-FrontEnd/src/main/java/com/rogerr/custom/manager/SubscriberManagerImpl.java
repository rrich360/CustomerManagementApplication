package com.rogerr.custom.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerr.custom.model.SubscriberView;

@Service
public class SubscriberManagerImpl implements SubscriberManager {

	Logger logger = LoggerFactory.getLogger(SubscriberManagerImpl.class);
	private final String RESOURCE_URI = "/user/";
	
	@Value("${service.url}")
	private String serviceBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<SubscriberView> findById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<SubscriberView> responseEntity = restTemplate.getForEntity(requestUri+"/{id}", SubscriberView.class, Long.toString(id));
		return responseEntity;
	}
	

	@Override
	public ResponseEntity<SubscriberView> findByUsername(String username) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<SubscriberView> responseEntity = restTemplate.getForEntity(requestUri+"?username={username}", SubscriberView.class, username);
		return responseEntity;
	}
	

	@Override
	public ResponseEntity<SubscriberView> saveUser(SubscriberView user) {
		if (user == null) {
			throw new RuntimeException("No subscriber to save");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		
		return restTemplate.postForEntity(requestUri, user, SubscriberView.class);
	}

	
	@Override
	public ResponseEntity<SubscriberView> updateUser(SubscriberView user) {
		if (user == null || user.getId() == null) {
			throw new RuntimeException("No subsdcriber to update");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		
		return restTemplate.exchange(requestUri + "/{id}",
				HttpMethod.PUT,
				new HttpEntity<>(user),
				SubscriberView.class,
				Long.toString(user.getId()));
	}

	
	@Override
	public void deleteUserById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

	
	@Override
	public ResponseEntity<SubscriberView[]> findAllUsers() {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		ResponseEntity<SubscriberView[]> responseEntity = restTemplate.getForEntity(requestUri, SubscriberView[].class);
		return responseEntity;
	}

	
	@Override
	public void deleteAllUsers(List<SubscriberView> subscriberList) {
		if (subscriberList == null) {
			throw new RuntimeException("No Subscribers provided!");
		}
		for (SubscriberView subscribers : subscriberList) {
					deleteUserById(subscribers.getId());
		}
	}

	
	@Override
	public Boolean isUserExist(SubscriberView user) {
		
		return user != null && user.getId() != null && findById(user.getId()) != null;
	}

}
