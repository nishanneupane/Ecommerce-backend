package com.nishanneupane.ecommerce.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddtoCartDto {
	
	private Integer id;
	
	@NotNull
	private Integer productId;
	
	@NotNull
	private Integer quantity;

}
