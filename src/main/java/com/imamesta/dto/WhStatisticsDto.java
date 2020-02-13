package com.imamesta.dto;

import java.util.Date;

public class WhStatisticsDto {
	
	private Long ingredientCategoryId;
	
	private Date startDate;
	
	private Date endDate;
	
	public WhStatisticsDto() {
		
	}
	
	public Long getIngredientCategoryId() {
		return ingredientCategoryId;
	}
	
	public void setIngredientCategoryId(Long ingredientCategoryId) {
		this.ingredientCategoryId = ingredientCategoryId;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
