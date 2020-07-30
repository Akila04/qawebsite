package com.example.demo;

import org.springframework.data.annotation.Id;


public class logindetails {

	@Id
	private String name;
	private String password;
	private String email;
	
	
	
	public logindetails() {

	}


	public logindetails(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
