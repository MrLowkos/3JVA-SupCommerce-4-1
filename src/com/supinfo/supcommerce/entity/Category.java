package com.supinfo.supcommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Category Product categories
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long				id;
	@Column(unique = true, nullable = false)
	private String				name;
	
	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
