package com.imamesta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.imamesta.domain.orders.ActiveOrder;

@Entity
@Table(name = "MY_TABLE")
public class MyTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy = "myTable", cascade = CascadeType.PERSIST)
	private List<ActiveOrder> myOrders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ActiveOrder> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(List<ActiveOrder> myOrders) {
		this.myOrders = myOrders;
	}
	
	
	
	
	
	
}
