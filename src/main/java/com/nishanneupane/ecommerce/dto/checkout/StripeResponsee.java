package com.nishanneupane.ecommerce.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StripeResponsee {
	private String sessionId;

	public StripeResponsee(String sessionId) {
		super();
		this.sessionId = sessionId;
	}

}
