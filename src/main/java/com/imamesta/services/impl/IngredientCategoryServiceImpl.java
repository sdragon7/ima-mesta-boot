package com.imamesta.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imamesta.dao.IngredientCategoryRepository;
import com.imamesta.domain.IngredientCategory;
import com.imamesta.services.IngredientCategoryService;

@Service
@Transactional
public class IngredientCategoryServiceImpl implements IngredientCategoryService {

	@Autowired
	private IngredientCategoryRepository icr;
	@Override
	public List<IngredientCategory> findAll() {
		List<IngredientCategory> list = icr.findAll();
		for(IngredientCategory l : list) {
			l.getIngredients().size();
		}
		return list;
	}
	@Override
	public Optional<IngredientCategory> getById(Long id) {
		// TODO Auto-generated method stub
		return icr.findById(id);
	}
	@Override
	public IngredientCategory save(IngredientCategory ic) {
		return icr.save(ic);
	}
	
	

}
