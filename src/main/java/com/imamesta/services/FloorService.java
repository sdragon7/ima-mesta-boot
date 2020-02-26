package com.imamesta.services;

import java.util.List;

import com.imamesta.domain.Floor;

public interface FloorService {
	
	List<Floor> getFloors();
	
	Floor getFloor(Long id);
	
	Floor getByName(String floorName);
}
