package com.imamesta.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.MyTableRepository;
import com.imamesta.domain.table.MyTable;
import com.imamesta.services.TableService;

@Service
@Transactional
public class TableServiceImpl implements TableService {
	
	@Autowired
	private MyTableRepository tableRepo;

	@Override
	public MyTable getById(Long id) {
		return tableRepo.getOne(id);
	}

	@Override
	public MyTable updateTable(MyTable table) {
		return tableRepo.save(table);
	}
}
