package com.samti.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samti.ws.error.NotFoundException;

@Service
@Transactional
public class UserService {
	
	IUserRepository userRepository;
	
	PasswordEncoder passwordEncoder;
	
	
    @Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}



	public void save(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	



	public IUserRepository getUserRepository() {
		return userRepository;
	}



	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}



	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}



	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}



	public User getByEmail(String email) {
		User inDB =  userRepository.findByEmail(email);
		if(inDB == null) {
			throw new NotFoundException();
		}
		return inDB;
		
	}
	
	

}
