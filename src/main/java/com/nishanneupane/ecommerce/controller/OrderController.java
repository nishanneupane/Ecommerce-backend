package com.nishanneupane.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishanneupane.ecommerce.dto.checkout.CheckoutItemDto;
import com.nishanneupane.ecommerce.dto.checkout.StripeResponsee;
import com.nishanneupane.ecommerce.service.AuthenticationService;
import com.nishanneupane.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private OrderService orderService;
	
	//stripe checkout api
	
	@PostMapping("/create-checkout-session")
	private ResponseEntity<StripeResponsee> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtos) throws StripeException{
		
		com.stripe.model.checkout.Session session=orderService.createSession(checkoutItemDtos);
		
		StripeResponsee response=new StripeResponsee(session.getId());
		
		return new ResponseEntity<StripeResponsee>(response,HttpStatus.OK);
		
	}

}
