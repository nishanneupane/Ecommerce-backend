package com.nishanneupane.ecommerce.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nishanneupane.ecommerce.dto.ProductDto;
import com.nishanneupane.ecommerce.model.Product;
import com.nishanneupane.ecommerce.model.User;
import com.nishanneupane.ecommerce.model.Whislist;
import com.nishanneupane.ecommerce.service.AuthenticationService;
import com.nishanneupane.ecommerce.service.WhislistService;

@RestController
@RequestMapping("/whislist")
public class WhislistController {

	@Autowired
	private WhislistService whislistService;

	@Autowired
	private AuthenticationService authenticationService;

	// post

	@PostMapping("/add")
	private ResponseEntity<Whislist> postWhislist(@RequestBody Product product, @RequestParam("token") String token) {
		// authenticate
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);

		// save
		Whislist whislist = new Whislist();
		whislist.setUser(user);
		whislist.setProduct(product);
		whislist.setCreatedDate(new Date());

		return new ResponseEntity<Whislist>(whislistService.post(whislist), HttpStatus.CREATED);
	}

	@GetMapping("/{token}")
	private ResponseEntity<List<ProductDto>> getAllWhislists(@PathVariable String token) {
		// authenticate
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);

		return new ResponseEntity<List<ProductDto>>(whislistService.getWhislistForUser(user), HttpStatus.OK);
	}

}
