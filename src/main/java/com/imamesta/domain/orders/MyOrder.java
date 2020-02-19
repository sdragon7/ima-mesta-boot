package com.imamesta.domain.orders;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.imamesta.domain.MyCheck;
import com.imamesta.domain.Product;
import com.imamesta.domain.table.MyTable;

@MappedSuperclass
public class MyOrder  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "QTY")
	private Double quantity;
	

	
	
	@ManyToOne
	private MyTable myTable;
	
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	
	public MyTable getMyTable() {
		return myTable;
	}


	public void setMyTable(MyTable myTable) {
		this.myTable = myTable;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getQuantity() {
		return quantity;
	}


	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
