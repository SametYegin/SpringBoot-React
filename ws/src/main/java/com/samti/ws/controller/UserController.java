package com.samti.ws.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.samti.ws.error.ApiError;
import com.samti.ws.genericresponse.GenericResponse;

@RestController
public class UserController {
	
	
	
	
	@Autowired
    UserService userService;
	
	@Autowired
	IUserRepository userRepository;
	
	
	
	@PostMapping("/users") //@ResponseStatus ile requestin başarılı işlemlerinde cevabı değiştirebiliriz.
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) { //@Valid entitydeki valid constraintlerden geçti mi diye kontrol eder.
		String email = user.getEmail();
		String workexp = user.getWorkexp();
		String password = user.getPassword();
		String education = user.getEducation();
		String username = user.getUsername();
		ApiError error = new ApiError(400, "Validation error", "/users");
		Map<String , String> validationErrors = new HashMap<>();
		if(email == null || email.isEmpty()) {
			
			validationErrors.put("email", "email cannot be null");
			
		}
		
        if(workexp == null || workexp.isEmpty()) {
			
			validationErrors.put("workexp", "workexp cannot be null");
			
		}
        
        if(password == null || password.isEmpty()) {
			
			validationErrors.put("password", "password cannot be null");
			
		}
        
        if(education == null || education.isEmpty()) {
			
			validationErrors.put("education", "education cannot be null");
			
		}
        
        if(username == null || username.isEmpty()) {
			
			validationErrors.put("username", "username cannot be null");
			
		}
        
        if(validationErrors.size() > 0) {
        	error.setValidationErrors(validationErrors);
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
		userService.save(user);
	    //statü bilgisini generic olarak anlayacağımız dilde resmedebiliriz.
	    return ResponseEntity.ok(new GenericResponse("user created"));
	}
	
	@GetMapping("/listall")
	List<User> getUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("/getUser/{email}")
	User getUser(@PathVariable String email) {
		User user = userService.getByEmail(email);
		return user;
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity updateUser(@PathVariable Long id , @RequestBody User user) {
		User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
		
		currentUser.setEducation(user.getEducation());
		currentUser.setWorkexp(user.getWorkexp());
		
		
		
		userRepository.save(currentUser);
		
		
		return ResponseEntity.ok(currentUser);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}



	public UserService getUserService() {
		return userService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

}
