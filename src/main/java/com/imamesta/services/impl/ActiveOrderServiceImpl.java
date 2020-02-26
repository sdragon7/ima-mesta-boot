package com.imamesta.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.ActiveOrderRepository;
import com.imamesta.domain.orders.ActiveOrder;
import com.imamesta.services.ActiveOrderService;

@Service
@Transactional
public class ActiveOrderServiceImpl implements ActiveOrderService {

	@Autowired
	private ActiveOrderRepository orderRepo;
	
	@Override
	//@Transactional(propagation = Propagation.MANDATORY)
	public void removeOrder(ActiveOrder order) throws RuntimeException{
		 orderRepo.delete(order);
	}
}
