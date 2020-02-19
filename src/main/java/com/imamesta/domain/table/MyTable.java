package com.imamesta.domain.table;

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
import com.imamesta.domain.Floor;
import com.imamesta.domain.orders.ActiveOrder;

@Entity
@Table(name = "MY_TABLE")
public class MyTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy = "myTable", cascade = CascadeType.PERSIST)
	private List<ActiveOrder> orders;
	
	@ManyToOne
	@JoinColumn(name = "floor_id")
	@JsonIgnore
	private Floor floor;
	
	
	private Double total = 0.0;
	
	private Double x;
	
	private Double y;
	
	private Boolean draggable = true;
	
	private Integer numberOfTabs = 2;
	
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
}
