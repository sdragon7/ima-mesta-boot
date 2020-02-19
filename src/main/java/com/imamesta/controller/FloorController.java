package com.imamesta.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.dao.MyTableRepository;
import com.imamesta.domain.Floor;
import com.imamesta.domain.table.ControlledPosition;
import com.imamesta.domain.table.MyTable;
import com.imamesta.domain.table.TabNumber;
import com.imamesta.dto.FloorDto;
import com.imamesta.dto.MyTableDto;
import com.imamesta.services.FloorService;

@RestController
public class FloorController {
	
	@Autowired
	private FloorService floorService;
	
	@Autowired
	private MyTableRepository tableRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/floor/list")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public List<FloorDto> getFloors() {
		List<Floor> floors = floorService.getFloors();
		List<FloorDto> floorDtos = new ArrayList<>();
		
		for(Floor floor : floors) {
			floorDtos.add(convertFloorToDto(floor));
		
		}
		
		
		return floorDtos;
	}
	
	@GetMapping("/floor/{id}")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public FloorDto getFloor(@PathVariable Long id) {
		return convertFloorToDto(floorService.getFloor(id));
	
	}
	
	private FloorDto convertFloorToDto(Floor floor) {
		FloorDto floorDto = modelMapper.map(floor, FloorDto.class);
		floorDto.setTables(new ArrayList<>()); // ovo mora jer mapper prekopira neke vrednosti koje nisu okej
		for(MyTable t : floor.getTables()) {
			MyTableDto mtdto = convertTableToDto(t);
			floorDto.addTableDto(mtdto);
		}
		return floorDto;	
	}
	
	
	@GetMapping("/tables/{id}")
	public MyTableDto getTable(@PathVariable Long id) {
		return convertTableToDto(tableRepo.getOne(id));
	}
	
	
	private MyTableDto  convertTableToDto(MyTable myTable) {
		MyTableDto myTableDto = modelMapper.map(myTable, MyTableDto.class);
		
		myTableDto.setControlledPosition(new ControlledPosition(myTable.getX(), myTable.getY()));
		
		List<TabNumber> tabsToRender = new ArrayList<>();
		for(int i = 1; i <= myTable.getNumberOfTabs(); i++) {
			tabsToRender.add(new TabNumber(String.valueOf(i)));
		}
		myTableDto.setTabsToRender(tabsToRender);
		myTableDto.setActiveTab(String.valueOf(myTable.getActiveTab()));
		myTableDto.setTableNumber(myTable.getId());
		return myTableDto;
	}
	
	private MyTable convertDtoToTable(MyTableDto myTableDto) {
		MyTable myTable = modelMapper.map(myTableDto, MyTable.class);
		myTable.setX(myTableDto.getControlledPosition().getX());
		myTable.setY(myTableDto.getControlledPosition().getY());
		myTable.setId(myTableDto.getTableNumber());
		myTable.setActiveTab(Integer.parseInt(myTableDto.getActiveTab()));
	
		return myTable;
	}
	

	
}
