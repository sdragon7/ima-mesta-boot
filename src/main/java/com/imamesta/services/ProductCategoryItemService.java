package com.imamesta.services;

import java.util.List;

import com.imamesta.domain.ProductCategoryItem;

public interface ProductCategoryItemService {
	
	List<ProductCategoryItem> getByProductCategoryId(Long id);

}
