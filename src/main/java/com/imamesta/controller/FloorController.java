package com.imamesta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.domain.Floor;
import com.imamesta.services.FloorService;

@RestController
public class FloorController {
	
	@Autowired
	private FloorService floorService;
	
	@GetMapping("/floor/list")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public List<Floor> getFloors() {
		return floorService.getFloors();
	}
	

	
}
