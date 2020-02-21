package com.imamesta.dto;

import java.io.Serializable;
import java.util.List;

public class FloorDto implements Serializable{

	private Long id;

	private String floorName;

	private List<MyTableDto> tables;
	
	
	public void addTableDto(MyTableDto mtdto) {
		tables.add(mtdto);
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
	
	public List<MyTableDto> getTables() {
		return tables;
	}

	public void setTables(List<MyTableDto> tables) {
		this.tables = tables;
	}

	
	
}
