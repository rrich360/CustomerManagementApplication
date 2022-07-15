package com.provider.springboot.model;



import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.provider.springboot.api.Subscriber;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberView implements Subscriber {
	Long id;
	String username;
	String address;
	String email;
	
	
	SubscriberView() {	}

	SubscriberView(Subscriber subscriber){
		BeanUtils.copyProperties(subscriber, this, SubscriberView.class);
	}
	
	public static SubscriberView convert(Subscriber subscriber) {
		if (subscriber == null) {
			return null;
		}
		if(subscriber instanceof SubscriberView) {
			return (SubscriberView) subscriber;
		}

		return new SubscriberView(subscriber);	
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", username=" + username + ", address=" + address + ", email=" + email
				+ "]";
	}
	
}