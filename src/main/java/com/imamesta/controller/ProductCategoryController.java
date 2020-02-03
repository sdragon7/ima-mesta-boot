package com.imamesta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imamesta.domain.ProductCategory;
import com.imamesta.services.ProductCategoryService;

@RestController
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping("/test")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public ProductCategory returnTedt() {
		return productCategoryService.getById(1L);
	}
}
