package com.imamesta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	
	
	@OneToMany( mappedBy="ingredient",cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UpdateMessage> messages;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ingredient_category_id")
	private IngredientCategory ingredientCategory;
	
	@Column(name = "UNIT")
	@Enumerated(EnumType.STRING)
	private Units unit;
	
	@Column(name = "REM_QTY")
	private Double remainingQuantity;
	
	@Column(name = "LAST_QTY_INC", columnDefinition="default 0.0")
	private Double lastQuantityIncrease;
	
	@Column(name = "LAST_QTY_DEC", columnDefinition="default 0.0")
	private Double lastQuantityDecrease;
	
	@Column(name = "LAST_QTY_UPDATE", columnDefinition="default 0.0")
	private Double lastQuantityUpdate;
	
	

	public Double getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(Double remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public Double getLastQuantityIncrease() {
		return lastQuantityIncrease;
	}

	public void setLastQuantityIncrease(Double lastQuantityIncrease) {
		this.lastQuantityIncrease = lastQuantityIncrease;
	}

	public Double getLastQuantityDecrease() {
		return lastQuantityDecrease;
	}

	public void setLastQuantityDecrease(Double lastQuantityDecrease) {
		this.lastQuantityDecrease = lastQuantityDecrease;
	}

	public Double getLastQuantityUpdate() {
		return lastQuantityUpdate;
	}

	public void setLastQuantityUpdate(Double lastQuantityUpdate) {
		this.lastQuantityUpdate = lastQuantityUpdate;
	}
	
	public IngredientCategory getIngredientCategory() {
		return ingredientCategory;
	}

	public void setIngredientCategory(IngredientCategory ingredientCategory) {
		this.ingredientCategory = ingredientCategory;
	}
	
	
	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
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

	public List<UpdateMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<UpdateMessage> messages) {
		this.messages = messages;
	}
}
