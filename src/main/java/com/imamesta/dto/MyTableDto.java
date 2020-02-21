package com.imamesta.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imamesta.domain.orders.ActiveOrder;
import com.imamesta.domain.table.ControlledPosition;
import com.imamesta.domain.table.TabNumber;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MyTableDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ActiveOrder> orders;
	
	private Double total;
	
	private Boolean isDraggable;
	
	private String activeTab;
	
	private Integer numberOfTabs;

	private List<TabNumber> tabsToRender;

	private String tableColor;

	private Long tableNumber;
	
	private ControlledPosition controlledPosition;

	public List<ActiveOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ActiveOrder> orders) {
		this.orders = orders;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public Integer getNumberOfTabs() {
		return numberOfTabs;
	}

	public void setNumberOfTabs(Integer numberOfTabs) {
		this.numberOfTabs = numberOfTabs;
	}

	

	public List<TabNumber> getTabsToRender() {
		return tabsToRender;
	}

	public void setTabsToRender(List<TabNumber> tabsToRender) {
		this.tabsToRender = tabsToRender;
	}

	public String getTableColor() {
		return tableColor;
	}

	public void setTableColor(String tableColor) {
		this.tableColor = tableColor;
	}

	public Long getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(Long tableNumber) {
		this.tableNumber = tableNumber;
	}

	public ControlledPosition getControlledPosition() {
		return controlledPosition;
	}

	public void setControlledPosition(ControlledPosition controlledPosition) {
		this.controlledPosition = controlledPosition;
	}

	public Boolean getIsDraggable() {
		return isDraggable;
	}

	public void setIsDraggable(Boolean isDraggable) {
		this.isDraggable = isDraggable;
	}
	
	
	
	
	
	
	
}
