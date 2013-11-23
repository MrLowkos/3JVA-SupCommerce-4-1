package com.supinfo.supcommerce.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Category
 * 
 * Product categories
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 4.1
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue
	private Long				id;
	private String				name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
