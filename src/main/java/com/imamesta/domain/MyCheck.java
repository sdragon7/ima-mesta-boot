package com.imamesta.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.imamesta.domain.orders.MyOrder;
import com.imamesta.domain.orders.PaidOrder;

@Entity
@Table(name = "MY_CHECK")
public class MyCheck {


	@PrePersist
	protected void onCreate() {
	    date = new Date();
	}
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@OneToMany(mappedBy = "myCheck", cascade = CascadeType.PERSIST)
	private List<PaidOrder> myOrders;
	

	private Double total;
	
	
	private Date date;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<PaidOrder> getMyOrders() {
		return myOrders;
	}


	public void setMyOrders(List<PaidOrder> myOrders) {
		this.myOrders = myOrders;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	
	
	
}
