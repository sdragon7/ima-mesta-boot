package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{

}
