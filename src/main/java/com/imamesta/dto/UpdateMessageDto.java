package com.imamesta.dto;

import java.util.Date;

import com.imamesta.domain.UpdateType;

public class UpdateMessageDto {

	private String msg;
	private double quantity;
	private Date date;
	private Long id;
	private UpdateType type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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

}
