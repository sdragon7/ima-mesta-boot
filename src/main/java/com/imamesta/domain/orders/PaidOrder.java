package com.imamesta.domain.orders;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.imamesta.domain.MyCheck;

@Entity
@Table(name = "PAID_ORDER")
public class PaidOrder extends MyOrder {

	@ManyToOne
	@JoinColumn
	private MyCheck myCheck;

	public MyCheck getMyCheck() {
		return myCheck;
	}

	public void setMyCheck(MyCheck myCheck) {
		this.myCheck = myCheck;
	}
	
}
