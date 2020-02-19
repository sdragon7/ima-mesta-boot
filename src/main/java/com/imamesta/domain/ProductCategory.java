package com.imamesta.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Lob
	@Column(name = "image")
	private byte[] image;
	
	@OneToMany(mappedBy="category", cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private List<ProductCategoryItem> items;
	
	public ProductCategory() {
		
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

	public List<ProductCategoryItem> getItems() {
		return items;
	}

	public void setItems(List<ProductCategoryItem> items) {
		this.items = items;
	}
}
