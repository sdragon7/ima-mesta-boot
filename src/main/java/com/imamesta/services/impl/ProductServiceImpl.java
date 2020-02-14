package com.imamesta.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.ProductRepository;
import com.imamesta.domain.Product;
import com.imamesta.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getById(Long id) {
		Product product = productRepository.getOne(id);
		product.getProductIngredients().size();
		return product;
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
}
