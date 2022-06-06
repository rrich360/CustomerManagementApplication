package com.rogerr.custom.model;

//import javax.validation.constraints.NotBlank;

public class Login {
	
	
//	@NotBlank(message="username can not be empty!")
	
	private String username;
	
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}


