package com.imamesta.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.domain.orders.ActiveOrder;
import com.imamesta.domain.table.ControlledPosition;
import com.imamesta.domain.table.MyTable;
import com.imamesta.domain.table.TabNumber;
import com.imamesta.dto.MyTableDto;
import com.imamesta.services.ActiveOrderService;
import com.imamesta.services.FloorService;
import com.imamesta.services.MyCheckService;
import com.imamesta.services.TableService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class TableController {
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private ActiveOrderService activeOrderService;
	
	@Autowired
	private MyCheckService myCheckService;
	
	@Autowired
	private FloorService floorService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/table/add/new")
	public MyTableDto addTable(@RequestParam String floorName) {
		MyTable table = new MyTable();
		table.setFloor(floorService.getByName(floorName));
		table.setOrders(new ArrayList<>());
		MyTable tableNew = tableService.updateTable(table);
		return convertTableToDto(tableNew);
	}
	
	@PutMapping("/table/update/position")
	public MyTableDto updateTablePosition(@RequestBody MyTableDto tableDto) {
		MyTable table = tableService.getById(tableDto.getTableNumber());
		table.setX(tableDto.getControlledPosition().getX());
		table.setY(tableDto.getControlledPosition().getY());
		return convertTableToDto(tableService.updateTable(table));
	}
	
	@PutMapping("/table/add/order")
	public MyTableDto addOrder(@RequestBody MyTableDto tableDto) {
		MyTable table = tableService.getById(tableDto.getTableNumber());
		
		boolean addOrder = false;
		if(table.getOrders() == null || table.getOrders().isEmpty()) {
			table.setOrders(new ArrayList<>());
			addOrder = true;
		}
		
		ActiveOrder order = tableDto.getOrders().get(0);
		
		for(ActiveOrder tOrder : table.getOrders()) {			
			if(tOrder.getProduct().getName().equals(order.getProduct().getName()) &&
					tOrder.getMyTab() == order.getMyTab()) {
				
				tOrder.setQuantity(tOrder.getQuantity() + 1);
				addOrder = false;
				break;
			} else {
				addOrder = true;
			}
		}
		
		if(addOrder) {
			table.getOrders().add(order);
		}
		
		order.setMyTable(table);
		table.setActiveTab(order.getMyTab());
		table.setTotal(table.getTotal() + order.getProduct().getPrice());
		table.setTableColor("danger");
		
		return convertTableToDto(tableService.updateTable(table));
	}
	
	@PutMapping("/table/add/tab")
	public MyTableDto addTab(@RequestBody MyTableDto tableDto) {
		MyTable table = tableService.getById(tableDto.getTableNumber());
		table.setNumberOfTabs(table.getNumberOfTabs() + 1);
		table.setActiveTab(table.getNumberOfTabs());
		return convertTableToDto(tableService.updateTable(table));
	}
	
	@PutMapping("/table/{type}/order")
	public MyTableDto increaseOrder(@PathVariable("type") String type, @RequestBody MyTableDto tableDto) {
		MyTable table = tableService.getById(tableDto.getTableNumber());
		
		ActiveOrder order = tableDto.getOrders().get(0);
		
		for(ActiveOrder tOrder : table.getOrders()) {			
			if(tOrder.getProduct().getName().equals(order.getProduct().getName()) &&
					tOrder.getMyTab() == order.getMyTab()) {
				
				if(type.equals("increase")) {
					tOrder.setQuantity(tOrder.getQuantity() + 1);
					table.setTotal(table.getTotal() + order.getProduct().getPrice());
					table.setTableColor("danger");
				}
				else {
					tOrder.setQuantity(tOrder.getQuantity() - 1);
					table.setTotal(table.getTotal() - order.getProduct().getPrice());
					
					if(tOrder.getQuantity() == 0) {
						if(table.getOrders().size() == 1)
							table.setTableColor("success");
						
						table.getOrders().remove(tOrder);
						activeOrderService.removeOrder(tOrder);
					}
				}
				
				break;
			}
		}
		
		order.setMyTable(table);
		table.setActiveTab(order.getMyTab());
		return convertTableToDto(tableService.updateTable(table));
	}
	
	@PostMapping("/table/orders/pay")
	public MyTableDto payOrders(@RequestBody MyTableDto tableDto) {
		return convertTableToDto(myCheckService.createCheck(tableDto.getTableNumber(), tableDto.getOrders()));
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
}
