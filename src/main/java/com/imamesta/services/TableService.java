package com.imamesta.services;

import com.imamesta.domain.table.MyTable;

public interface TableService {
	
	MyTable getById(Long id);
	
	MyTable updateTable(MyTable table);

}
