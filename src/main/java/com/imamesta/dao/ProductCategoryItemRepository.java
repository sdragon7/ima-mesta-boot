package com.imamesta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.ProductCategoryItem;

public interface ProductCategoryItemRepository extends JpaRepository<ProductCategoryItem, Long> {
	
	List<ProductCategoryItem> getByCategoryId(Long id);

}
