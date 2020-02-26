package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.Floor;

public interface FloorRepository extends JpaRepository<Floor, Long> {

	Floor getByFloorName(String floorName);
}
