package com.imamesta.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.MyCheckRepository;
import com.imamesta.domain.MyCheck;
import com.imamesta.domain.orders.ActiveOrder;
import com.imamesta.services.ActiveOrderService;
import com.imamesta.services.MyCheckService;

@Service
@Transactional
public class MyCheckServiceImpl implements MyCheckService {

	@Autowired
	private MyCheckRepository myCheckRepository;
	
	@Autowired
	private ActiveOrderService activeOrderService;
	
	@Override
	public MyCheck saveMyCheck(MyCheck myCheck, ActiveOrder order) {
		try {
		activeOrderService.removeOrder(order);
		return myCheckRepository.save(myCheck);
		} catch (Exception e) {
			System.out.println("problem: " + order.getId());
			return null;
		}
	}
}
