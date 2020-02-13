package com.imamesta.dto;

import java.util.Date;

public class WhStatisticsDto {
	
	private Long ingradientCategoryId;
	
	private Date startDate;
	
	private Date endDate;
	
	public WhStatisticsDto() {
		
	}

	public Long getIngradientCategoryId() {
		return ingradientCategoryId;
	}

	public void setIngradientCategoryId(Long ingradientCategoryId) {
		this.ingradientCategoryId = ingradientCategoryId;
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
