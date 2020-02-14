package com.imamesta.services;

import com.imamesta.domain.Product;

public interface ProductService {

	Product getById(Long id);
	
	Product saveProduct(Product product);
}
