package com.nishanneupane.ecommerce.dto.cart;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	
	@Valid
	@NotEmpty
	private List<CartItemDto> cartItems;
	
	@PositiveOrZero
	private double totalCost;

}
