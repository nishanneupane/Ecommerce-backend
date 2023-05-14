package com.nishanneupane.ecommerce.dto.cart;

import com.nishanneupane.ecommerce.dto.ProductDto;
import com.nishanneupane.ecommerce.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
	private Integer id;
	private Integer quantity;
	private Product product;
}
