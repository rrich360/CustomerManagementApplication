package com.provider.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.provider.springboot.api.Subscriber;


@Entity
@Table(name= "Subscriber")
public class SubscriberImpl implements Subscriber{
	
@Id	
@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "EMAIL")
	private String email;


	public SubscriberImpl(){}
	
	SubscriberImpl(Subscriber subscriber){
		BeanUtils.copyProperties(subscriber, this, SubscriberImpl.class);
	}
	
	
	public static SubscriberImpl convert(Subscriber subscriber) {
		if (subscriber == null) { 
			return null;
		}
		if (subscriber instanceof SubscriberImpl) {
			return (SubscriberImpl) subscriber;
		}
		return new SubscriberImpl(subscriber);
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
	return "User [id=" + id + ", "
			+ "Username=" + username + ", "
					+ "Address=" + address + ","
					+ "Email=" + email + "]";
}

}
