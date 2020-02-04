package com.imamesta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.imamesta.domain.User;
import com.imamesta.dto.UserDto;
import com.imamesta.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public User getUser(@RequestBody UserDto userdto) {
		System.out.println(userdto.getPassword());
		List<User> users = userService.getAllUsers();
		for(User user : users) {
			if(passwordEncoder.matches(userdto.getPassword(), user.getPassword())) {
				return user;
			}
		}
		return null;
	}
}
