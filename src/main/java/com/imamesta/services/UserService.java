package com.imamesta.services;

import java.util.List;

import com.imamesta.domain.User;

public interface UserService {
	
	User getByPassword(String password);
	
	List<User> getAllUsers();
}
