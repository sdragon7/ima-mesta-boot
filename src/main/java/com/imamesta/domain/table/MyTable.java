package com.imamesta.domain.table;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imamesta.domain.Floor;
import com.imamesta.domain.orders.ActiveOrder;

@Entity
@Table(name = "MY_TABLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MyTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy = "myTable", cascade = { CascadeType.ALL })
	private List<ActiveOrder> orders;
	
	@ManyToOne
	@JoinColumn(name = "floor_id")
	@JsonIgnore
	private Floor floor;
	
	
	private Double total = 0.0;
	
	private Double x;
	
	private Double y;
	
	private Boolean isDraggable = true;
	
	private Integer numberOfTabs = 2;
	
	private Integer activeTab;
	
	private String tableColor = "success";
	
	public MyTable() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ActiveOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ActiveOrder> orders) {
		this.orders = orders;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}


	public Boolean getIsDraggable() {
		return isDraggable;
	}

	public void setIsDraggable(Boolean isDraggable) {
		this.isDraggable = isDraggable;
	}

	public Integer getNumberOfTabs() {
		return numberOfTabs;
	}

	public void setNumberOfTabs(Integer numberOfTabs) {
		this.numberOfTabs = numberOfTabs;
	}

	public Integer getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(Integer activeTab) {
		this.activeTab = activeTab;
	}
	public String getTableColor() {
		return tableColor;
	}

	public void setTableColor(String tableColor) {
		this.tableColor = tableColor;
	}
	
}
