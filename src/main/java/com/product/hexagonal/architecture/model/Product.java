package com.product.hexagonal.architecture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String title; 	
	@Column 
	private String text;
	@Column
	private String imageFile;
	
}
