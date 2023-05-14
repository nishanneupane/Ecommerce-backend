package com.nishanneupane.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;

}
