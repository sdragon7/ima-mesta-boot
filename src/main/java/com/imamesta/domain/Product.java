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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Lob
	@Column(name = "image")
	private byte[] image;
	
	@Column(name = "PRICE", columnDefinition = "double default 0")
	private double price;
	
	@Column(name = "UNIT")
	@Enumerated(EnumType.STRING)
	private Units unit;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "category_item_id")
	private ProductCategoryItem categoryItem;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	private List<ProductIngredients> productIngredients;

	public Product() {
		
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}

	public List<ProductIngredients> getProductIngredients() {
		return productIngredients;
	}

	public void setProductIngredients(List<ProductIngredients> productIngredients) {
		this.productIngredients = productIngredients;
	}
}
