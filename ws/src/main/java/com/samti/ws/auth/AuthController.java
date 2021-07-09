package com.samti.ws.auth;



import java.util.Base64;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.samti.ws.controller.IUserRepository;
import com.samti.ws.controller.User;
import com.samti.ws.controller.UserService;
import com.samti.ws.error.ApiError;

@RestController
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	IUserRepository userRepository;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@PostMapping("/auth")
	@CrossOrigin
	ResponseEntity<?> handleAuthentication(@RequestHeader(name="Authorization", required = false) String authorization) {
		if(authorization == null) {
			ApiError error = new ApiError(401, "Unauthorized request", "/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
			
		}
		String base64encoded = authorization.split("Basic ")[1];
		String decoded = new String(Base64.getDecoder().decode(base64encoded)); //bu kısımda önce decode edip anlayacağımız dile dönüştürdük.
		String[] parts = decoded.split(":"); //sonrasında anlayacağımız dilde iki nokta ile değişkenlere ulaştık.
		String email = parts[0];
		String password = parts[1];
		
		/*List<User> allUsers = userRepository.findAll();
		
		
		for(User u: allUsers) {
			String hashedPassword = u.getPassword();
			if(email == u.getEmail() && passwordEncoder.matches(password, hashedPassword)) {
				return ResponseEntity.ok().build();
			}
			break;
		}*/
		
		User inDB = userService.getByEmail(email); //mailden databesede karşılık gelen objeye ulaşmıyor.
		if(inDB == null ) {
			ApiError error = new ApiError(401, "Unauthorized request", "/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		
		String hashedPassword = inDB.getPassword();
		if(!passwordEncoder.matches(password, hashedPassword)) {
			ApiError error = new ApiError(401, "Unauthorized request", "/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
			//burada inDB null hatası alıyorum. email-password kontrolü oluşmadı. Araştır.
		//sorun findByEmail fonksiyonunda değil. signup validasyonunda çalışıyor.
        //getByEmail methodu da işe yaramadı. 
			
		
		
		return ResponseEntity.ok().build();
		
		
	}

}
