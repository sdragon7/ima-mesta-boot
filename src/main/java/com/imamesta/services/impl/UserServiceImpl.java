package com.imamesta.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.UserRepository;
import com.imamesta.domain.User;
import com.imamesta.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getByPassword(String password) {
		return userRepository.getByPassword(password);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
