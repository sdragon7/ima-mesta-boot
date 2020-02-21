package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.orders.ActiveOrder;

public interface ActiveOrderRepository extends JpaRepository<ActiveOrder, Long> {

}
