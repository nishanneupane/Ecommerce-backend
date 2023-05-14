package com.nishanneupane.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.val;


@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	
	@ExceptionHandler(value = CustomException.class)
	
	public final ResponseEntity<String> handleCustomExceptiom(CustomException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AuthenticationFailException.class)
	public final ResponseEntity<String> handleAuthFailException(AuthenticationFailException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ProductNotExistsException.class)
	public final ResponseEntity<String> handleProductNotExistsException(ProductNotExistsException exc){
		return new ResponseEntity<String>(exc.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	

}
