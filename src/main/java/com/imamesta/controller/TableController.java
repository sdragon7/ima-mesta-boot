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
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.domain.MyCheck;
import com.imamesta.domain.orders.ActiveOrder;
import com.imamesta.domain.orders.PaidOrder;
import com.imamesta.domain.table.ControlledPosition;
import com.imamesta.domain.table.MyTable;
import com.imamesta.domain.table.TabNumber;
import com.imamesta.dto.MyTableDto;
import com.imamesta.services.ActiveOrderService;
import com.imamesta.services.MyCheckService;
import com.imamesta.services.TableService;

@RestController
public class TableController {
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private ActiveOrderService activeOrderService;
	
	@Autowired
	private MyCheckService myCheckService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PutMapping("/table/add/order")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
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
		
		return convertTableToDto(tableService.updateTable(table));
	}
	
	@PutMapping("/table/add/tab")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public MyTableDto addTab(@RequestBody MyTableDto tableDto) {
		MyTable table = tableService.getById(tableDto.getTableNumber());
		table.setNumberOfTabs(table.getNumberOfTabs() + 1);
		table.setActiveTab(table.getNumberOfTabs());
		return convertTableToDto(tableService.updateTable(table));
	}
	
	@PutMapping("/table/{type}/order")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public MyTableDto increaseOrder(@PathVariable("type") String type, @RequestBody MyTableDto tableDto) {
		MyTable table = tableService.getById(tableDto.getTableNumber());
		
		ActiveOrder order = tableDto.getOrders().get(0);
		
		for(ActiveOrder tOrder : table.getOrders()) {			
			if(tOrder.getProduct().getName().equals(order.getProduct().getName()) &&
					tOrder.getMyTab() == order.getMyTab()) {
				
				if(type.equals("increase")) {
					tOrder.setQuantity(tOrder.getQuantity() + 1);
					table.setTotal(table.getTotal() + order.getProduct().getPrice());
				}
				else {
					tOrder.setQuantity(tOrder.getQuantity() - 1);
					table.setTotal(table.getTotal() - order.getProduct().getPrice());
					
					if(tOrder.getQuantity() == 0) {
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
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
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
