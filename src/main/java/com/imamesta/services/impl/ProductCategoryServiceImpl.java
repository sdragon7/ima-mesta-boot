package com.imamesta.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.ProductCategoryRepository;
import com.imamesta.domain.ProductCategory;
import com.imamesta.services.ProductCategoryService;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepository productCategoryRepo;

	@Override
	public ProductCategory getById(Long id) {
		return productCategoryRepo.getOne(id);
	}
	
	@Override
	public List<ProductCategory> getAll() {
		return productCategoryRepo.findAll();
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return productCategoryRepo.save(productCategory);
	}
}
