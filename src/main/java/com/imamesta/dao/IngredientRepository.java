package com.imamesta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imamesta.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
