package com.rogerr.custom.model;

//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name="USERNAME")
	private String username;
	
	@Column(name="ADDRESS")
	private String address;

	@Column(name="EMAIL")
	private String email;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeOwner", cascade = CascadeType.ALL, orphanRemoval = false)
	//private List<Product> products;
	
	public User() {}

	public User(Long id, String username, String address, String email) {
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User {id=" + id + ", username=" + username + ", address=" + address
				+ ", email=" + email + "}";
	}

	//mvn clean jetty:run -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
	
}
