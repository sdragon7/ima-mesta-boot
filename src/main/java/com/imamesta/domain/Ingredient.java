package com.imamesta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.PERSIST)
	private List<ProductIngredients> productIngredients;
	
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
