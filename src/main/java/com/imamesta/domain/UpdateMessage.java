package com.imamesta.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "UPDATE_MESSAGE")
public class UpdateMessage {

	
	@PrePersist
	 protected void onCreate() {
	    date = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)	
	private Ingredient ingredient;
	
	@Column(name = "msg")
	private String msg;
	
	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private UpdateType type;
	
	@Column
	private Date date;
	
	public UpdateMessage(Ingredient ingredient, double quantity, UpdateType type) {
		super();
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public UpdateType getType() {
		return type;
	}

	public void setType(UpdateType type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
