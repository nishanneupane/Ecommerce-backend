package com.nishanneupane.ecommerce.exception;

public class ProductNotExistsException extends IllegalArgumentException{
	
	public ProductNotExistsException(String message) {
		super(message);
	}

}
