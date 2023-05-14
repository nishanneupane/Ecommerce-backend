package com.nishanneupane.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nishanneupane.ecommerce.dto.cart.AddtoCartDto;
import com.nishanneupane.ecommerce.dto.cart.CartDto;
import com.nishanneupane.ecommerce.model.Cart;
import com.nishanneupane.ecommerce.model.Product;
import com.nishanneupane.ecommerce.model.User;
import com.nishanneupane.ecommerce.response.ApiResponse;
import com.nishanneupane.ecommerce.service.AuthenticationService;
import com.nishanneupane.ecommerce.service.CartService;
import com.nishanneupane.ecommerce.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private AuthenticationService authenticationService;

	// post cart api
	@PostMapping("/add")
	private ResponseEntity<Cart> addToCart(@RequestBody AddtoCartDto addtoCartDto,
			@RequestParam("token") String token) {
		// authenticate
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);

		return new ResponseEntity<Cart>(cartService.addToCart(addtoCartDto, user), HttpStatus.CREATED);

	}

	// get all cart items for a user

	@GetMapping("/")
	private ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
		// authenticate
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);

		CartDto cartDto = cartService.listCartItems(user);

		return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
	}

	// delete a cart item for a user

	@DeleteMapping("/delete/{cartItemId}")
	private ResponseEntity<ApiResponse> deleteCartItems(@PathVariable Integer cartItemId,
			@RequestParam("token") String token) {

		// authenticate
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);
		
		cartService.deleteItem(cartItemId,user);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("cart Item Deleted Sucessfully",true), HttpStatus.OK);
	}

}
