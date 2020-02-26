package com.imamesta.services;

import java.util.List;

import com.imamesta.domain.orders.ActiveOrder;
import com.imamesta.domain.table.MyTable;

public interface MyCheckService {

	MyTable createCheck(Long tableId, List<ActiveOrder> order);
}
