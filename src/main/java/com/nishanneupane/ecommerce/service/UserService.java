package com.nishanneupane.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishanneupane.ecommerce.Repository.UserRepo;
import com.nishanneupane.ecommerce.dto.user.ResponseDto;
import com.nishanneupane.ecommerce.dto.user.SignInDto;
import com.nishanneupane.ecommerce.dto.user.SigninResponseDto;
import com.nishanneupane.ecommerce.dto.user.SignupDto;
import com.nishanneupane.ecommerce.exception.AuthenticationFailException;
import com.nishanneupane.ecommerce.exception.CustomException;
import com.nishanneupane.ecommerce.model.AuthenticationToken;
import com.nishanneupane.ecommerce.model.User;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	AuthenticationService authenticationService;
	
	
	@Transactional
	public ResponseDto signUp(SignupDto signupDto) {
		//user is already present
		
		if(Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))) {
			//we have an user
			
			throw new CustomException("User already Present");
		}
		
		//hash the password
		String encryptedPassword=signupDto.getPassword();
		try {
			encryptedPassword=hashPassword(signupDto.getPassword());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//save the user
		
		User user=new User();
		user.setEmail(signupDto.getEmail());
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setEmail(signupDto.getEmail());
		user.setPassword(encryptedPassword);
		
		userRepo.save(user);
		
		
		
		
		//create a token
		AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);
		
		ResponseDto responseDto=new ResponseDto("sucess","signup Sucessfully");
		
		return responseDto;
		
	}

	private String hashPassword(String password)throws NoSuchAlgorithmException {
		
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest=md.digest();
		
		String hash=DatatypeConverter.printHexBinary(digest).toUpperCase();
		

		return hash;
	}

	public SigninResponseDto signIn(SignInDto signInDto) {
		
		//find user by email
		
		User user=userRepo.findByEmail(signInDto.getEmail());
		
		
		if(Objects.isNull(user)) {
			throw new AuthenticationFailException("user is not valid");
		}
		
		//hash the password
		
		try {
			if(!user.getPassword().equals(signInDto.getPassword())) {
				throw new AuthenticationFailException("Wrong Password or email");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//compare the password in DB
		
		//if password match  
		
		AuthenticationToken token=authenticationService.getToken(user);
		
		//retrieve the token
		if(Objects.isNull(token)) {
			throw new CustomException("token is not present");
		}
		return new SigninResponseDto("sucess",token.getToken());
		
		//return response
	}

}
