package com.imamesta.dto;

import com.imamesta.domain.Units;

public class IngredientDto {

	private String name;
	
	private Long categoryId;
	
	private Double remainingQuantity;
	
	private Units unit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Double getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(Double remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}
	
	
	
}
