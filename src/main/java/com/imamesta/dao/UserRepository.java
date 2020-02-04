package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User getByPassword(String password);
}
