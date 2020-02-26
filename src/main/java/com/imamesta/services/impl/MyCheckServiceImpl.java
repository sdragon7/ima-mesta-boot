package com.imamesta.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.MyCheckRepository;
import com.imamesta.domain.MyCheck;
import com.imamesta.domain.orders.ActiveOrder;
import com.imamesta.domain.orders.PaidOrder;
import com.imamesta.domain.table.MyTable;
import com.imamesta.services.ActiveOrderService;
import com.imamesta.services.MyCheckService;
import com.imamesta.services.TableService;

@Service
@Transactional
public class MyCheckServiceImpl implements MyCheckService {

	@Autowired
	private MyCheckRepository myCheckRepository;
	
	@Autowired
	private ActiveOrderService activeOrderService;
	
	@Autowired
	private TableService tableService;
	
	@Override
	public MyTable createCheck(Long tableId, List<ActiveOrder> orders) {
		MyTable table = tableService.getById(tableId);
		
		if(orders.size() == 0) {
			return table;
		}
		
		MyCheck check = new MyCheck();
		check.setMyOrders(new ArrayList<>());
		check.setTotal(0.0);
		
		for(ActiveOrder order : orders) {
			PaidOrder pOrder = new PaidOrder();
			pOrder.setMyTable(table);
			pOrder.setProduct(order.getProduct());
			pOrder.setQuantity(order.getQuantity());
			pOrder.setMyCheck(check);
			check.setTotal(check.getTotal() + (order.getProduct().getPrice() * order.getQuantity()));
			check.getMyOrders().add(pOrder);
			table.setTotal(table.getTotal() - (order.getProduct().getPrice() * order.getQuantity()));
			table.setTableColor("success");
			activeOrderService.removeOrder(order);
		}
		
		myCheckRepository.save(check);
		return tableService.updateTable(table);
	}
}
