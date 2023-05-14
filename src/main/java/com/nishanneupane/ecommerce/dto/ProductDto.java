package com.nishanneupane.ecommerce.dto;


import jakarta.persistence.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private Integer id;
	
	@NotBlank
	@Column(name="name")
	private String productName;
	
	@NotNull
	private String imageUrl;
	
	@NotEmpty
	@Column(name="description",length = 10000)
	private String productDescription;
	
	private double price;
	
	private Integer categoryId;
}
