package com.nishanneupane.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishanneupane.ecommerce.dto.user.ResponseDto;
import com.nishanneupane.ecommerce.dto.user.SignInDto;
import com.nishanneupane.ecommerce.dto.user.SigninResponseDto;
import com.nishanneupane.ecommerce.dto.user.SignupDto;
import com.nishanneupane.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//signUp
	@PostMapping("/signup")
	private ResponseEntity<ResponseDto> signUp(@RequestBody SignupDto signupDto){
		return new ResponseEntity<ResponseDto>(userService.signUp(signupDto),HttpStatus.CREATED);
	}
	
	
	//signIn
	
	@PostMapping("/signin")
	private ResponseEntity<SigninResponseDto> signIn(@RequestBody SignInDto signInDto){
		return new ResponseEntity<SigninResponseDto>(userService.signIn(signInDto),HttpStatus.OK);
	}

}
