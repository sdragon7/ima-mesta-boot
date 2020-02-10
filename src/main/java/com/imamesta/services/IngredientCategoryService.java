package com.imamesta.services;

import java.util.List;
import java.util.Optional;

import com.imamesta.domain.IngredientCategory;

public interface IngredientCategoryService {

	List<IngredientCategory> findAll();
	Optional<IngredientCategory> getById(Long id);
}
