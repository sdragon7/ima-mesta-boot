package com.imamesta.domain.orders;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imamesta.domain.Product;
import com.imamesta.domain.table.MyTable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@MappedSuperclass
public class MyOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "QTY")
	private Double quantity;
	

	
	
	@JsonIgnore
	@ManyToOne
	private MyTable myTable;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	
	public MyOrder() {
		super();
	}


	public MyOrder(Double quantity, MyTable myTable, Product product) {
		super();
		this.quantity = quantity;
		this.myTable = myTable;
		this.product = product;
	}


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
