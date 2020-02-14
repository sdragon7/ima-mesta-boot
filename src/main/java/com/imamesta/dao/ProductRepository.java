package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
