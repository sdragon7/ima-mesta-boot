package com.imamesta.services;

import java.util.List;

import com.imamesta.domain.ProductCategory;

public interface ProductCategoryService {

	ProductCategory getById(Long id);
	
	List<ProductCategory> getAll();
}
