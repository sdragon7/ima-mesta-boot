package com.imamesta.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductIngredients implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Id
    @ManyToOne
    @JoinColumn
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn
    private Ingredient ingredient;
    
    @Column(name = "QUANTITY", columnDefinition = "double default 0")
	private double quantity;
	
	@Column(name = "UNIT")
	@Enumerated(EnumType.STRING)
	private Units unit;
    
    public ProductIngredients() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}
