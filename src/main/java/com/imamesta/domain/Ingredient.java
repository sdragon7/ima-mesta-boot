package com.imamesta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "INGREDIENT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.PERSIST)
	private List<ProductIngredients> productIngredients;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ingredient_category_id")
	private IngredientCategory ingredientCategory;
	
	public IngredientCategory getIngredientCategory() {
		return ingredientCategory;
	}

	public void setIngredientCategory(IngredientCategory ingredientCategory) {
		this.ingredientCategory = ingredientCategory;
	}

	
	@Column(name = "UNIT")
	@Enumerated(EnumType.STRING)
	private Units unit;
	
	@Column(name = "REM_QTY")
	private double remainingQuantity;
	
	@Column(name = "LAST_QTY_UPDATE")
	private double lastQuantityUpdate;
	
	
	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}

	public double getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(double remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public double getLastQuantityUpdate() {
		return lastQuantityUpdate;
	}

	public void setLastQuantityUpdate(double lastQuantityUpdate) {
		this.lastQuantityUpdate = lastQuantityUpdate;
	}

	
	
	
	public Ingredient() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductIngredients> getProductIngredients() {
		return productIngredients;
	}

	public void setProductIngredients(List<ProductIngredients> productIngredients) {
		this.productIngredients = productIngredients;
	}
}
