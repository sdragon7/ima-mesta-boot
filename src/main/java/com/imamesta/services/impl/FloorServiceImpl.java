package com.imamesta.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.FloorRepository;
import com.imamesta.domain.Floor;
import com.imamesta.services.FloorService;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorRepository floorRepo;
	
	@Override
	public List<Floor> getFloors() {
		return floorRepo.findAll();
	}

	@Override
	public Floor getFloor(Long id) {
		// TODO Auto-generated method stub
		return floorRepo.getOne(id);
	}
}
