package com.rogerr.custom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Subscriber {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="USERNAME")
	private String username;
	
	@Column(name="ADDRESS")
	private String address;

	@Column(name="EMAIL")
	private String email;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeOwner", cascade = CascadeType.ALL, orphanRemoval = false)
	//private List<Product> products;
	
	public Subscriber() {}

	public Subscriber(Long id, String username, String address, String email) {
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
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
	
}
