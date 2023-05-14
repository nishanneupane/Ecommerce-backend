package com.nishanneupane.ecommerce.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishanneupane.ecommerce.Repository.TokenRepository;
import com.nishanneupane.ecommerce.exception.AuthenticationFailException;
import com.nishanneupane.ecommerce.model.AuthenticationToken;
import com.nishanneupane.ecommerce.model.User;

@Service
public class AuthenticationService {
	
	@Autowired
	private TokenRepository tokenRepository;

	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
		
		
	}

	public AuthenticationToken getToken(User user) {
		
		return tokenRepository.findByUser(user);
	}
	
	public User getUser(String token) {
		final AuthenticationToken authenticationToken=tokenRepository.findByToken(token);
		
		if(Objects.isNull(authenticationToken)) {
			return null;
		}
		
		//tokan is not null
		return authenticationToken.getUser();
	}
	
	public void authenticate(String token) {
		//null check
		if(Objects.isNull(token)) {
			throw new AuthenticationFailException("token not present");
		}
		
		if(Objects.isNull(getUser(token))) {
			throw new AuthenticationFailException("token not valid");
		}
	}

}
