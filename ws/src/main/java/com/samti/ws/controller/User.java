package com.samti.ws.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;






@Entity

public class User {
	
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String username;
	
	@NotNull
	@Column(name = "email")
	@UniqueEmail
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String education;
	@NotNull
	private String workexp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getWorkexp() {
		return workexp;
	}

	public void setWorkexp(String workexp) {
		this.workexp = workexp;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}

	
	
	
	
	

}
