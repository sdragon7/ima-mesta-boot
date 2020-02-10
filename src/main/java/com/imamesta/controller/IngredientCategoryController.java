package com.imamesta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imamesta.dao.IngredientCategoryRepository;
import com.imamesta.dao.IngredientRepository;
import com.imamesta.domain.Ingredient;
import com.imamesta.domain.IngredientCategory;
import com.imamesta.services.IngredientCategoryService;

@RestController
public class IngredientCategoryController {

	@Autowired
	private IngredientCategoryService icr;
	
	@Autowired
	private IngredientRepository irepo;
	
	@GetMapping("/warehouse/categories")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public List<IngredientCategory> getIngredientCategories() {
		List<IngredientCategory> lista = icr.findAll();
		for( IngredientCategory l : lista) {
			for(Ingredient i : l.getIngredients()) {
				System.out.println(i.getName());
			}
		}
		return lista;
		
	}
	
	@GetMapping("/warehouse/ingredients")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public List<Ingredient> getIngredients() {

		return irepo.findAll();
		
	}
	@GetMapping("/warehouse/{id}")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public List<Ingredient> getAllIngredientsByCategoryId(@PathVariable ("id") Long id) {
		Optional<IngredientCategory> optVal = icr.getById(id);
		IngredientCategory ic = optVal.get();
		ic.getIngredients().size();
		return ic.getIngredients();
	}
	
	@PutMapping("/warehouse/ingredients")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public void updateIngredient(@RequestBody Ingredient i) {
		
		Ingredient toBeUpdated =irepo.findById(i.getId()).get();
		toBeUpdated.setRemainingQuantity(i.getRemainingQuantity());
		irepo.save(toBeUpdated);
	}
	
}
