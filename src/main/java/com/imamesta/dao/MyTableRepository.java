package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.table.MyTable;

public interface MyTableRepository extends JpaRepository<MyTable, Long> {

	
}
