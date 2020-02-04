package com.imamesta.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.ProductCategoryItemRepository;
import com.imamesta.domain.ProductCategoryItem;
import com.imamesta.services.ProductCategoryItemService;

@Service
@Transactional
public class ProductCategoryItemServiceImpl implements ProductCategoryItemService {

	@Autowired
	private ProductCategoryItemRepository repo;
	
	@Override
	public List<ProductCategoryItem> getByProductCategoryId(Long id) {
		return repo.getByCategoryId(id);
	}
}
