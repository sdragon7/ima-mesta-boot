package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.MyCheck;

public interface MyCheckRepository extends JpaRepository<MyCheck, Long> {

}
