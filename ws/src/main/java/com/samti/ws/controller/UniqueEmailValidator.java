package com.samti.ws.controller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
	
	
	@Autowired
	IUserRepository userRepository;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			return false;
		}
		return true;
	}
	

}
