package com.imamesta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.domain.Product;
import com.imamesta.domain.ProductCategory;
import com.imamesta.domain.ProductCategoryItem;
import com.imamesta.services.ProductCategoryItemService;
import com.imamesta.services.ProductCategoryService;

@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private ProductCategoryItemService productCategoryItemService;
	
	@GetMapping("/test")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public List<ProductCategory> returnTedt() {
		List<ProductCategory> pc = productCategoryService.getAll();
		for(ProductCategory p : pc) {
			System.out.println("Kategorija: " + p.getName());
			List<ProductCategoryItem> items = productCategoryItemService.getByProductCategoryId(p.getId());
			for(ProductCategoryItem item : items) {
				System.out.println("Podkategorija: " + item.getName());
				for(Product pp : item.getItems()) {
					System.out.println("Proizvod: " + pp.getName());
				}
			}
		}
		
		
		return pc;
	}
}
