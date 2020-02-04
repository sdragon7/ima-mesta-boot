package com.imamesta.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imamesta.dao.ProductCategoryRepository;
import com.imamesta.domain.ProductCategory;
import com.imamesta.services.ProductCategoryService;

@Service
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

}
