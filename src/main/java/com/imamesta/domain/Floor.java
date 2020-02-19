package com.imamesta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.imamesta.domain.table.MyTable;

@Entity
@Table(name = "FLOOR")
public class Floor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "NAME")
	private String floorName;
	
	@OneToMany(mappedBy = "floor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<MyTable> tables;
	
	public Floor() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public List<MyTable> getTables() {
		return tables;
	}

	public void setTables(List<MyTable> tables) {
		this.tables = tables;
	}
}
