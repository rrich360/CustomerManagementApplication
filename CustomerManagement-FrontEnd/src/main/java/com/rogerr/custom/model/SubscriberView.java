package com.rogerr.custom.model;

import org.springframework.beans.BeanUtils;

import com.rogerr.custom.api.Subscriber;
import com.rogerr.custom.model.SubscriberView;


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
   			@Override
   			public Long getId() {
   					return id;
   				}
   			
   			@Override
   			public void setId(Long id) {
   					this.id = id;
   				}
   			
   			@Override
   			public String getUsername() {
   					return username;
   				}
   			
   			@Override
   			public void setUsername(String username) {
   					this.username = username;
   				}
   			
   			@Override
   			public String getAddress() {
   					return address;
   				}
   			
   			@Override
   			public void setAddress(String address) {
   					this.address = address;
   				}
   			
   			@Override
   			public String getEmail() {
   					return email;
   				}
   			
   			@Override
   			public void setEmail(String email) {
   					this.email = email;
   				}

  @Override
  public String toString() {
	return "Subscriber [id=" + id + ", username=" + username + ", address=" + address + ", email=" + email + "]";
  		}
  
   }