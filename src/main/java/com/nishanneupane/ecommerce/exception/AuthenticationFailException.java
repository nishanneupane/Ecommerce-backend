package com.nishanneupane.ecommerce.exception;

public class AuthenticationFailException extends IllegalArgumentException{
	
	public AuthenticationFailException(String message) {
		super(message);
	}

}
