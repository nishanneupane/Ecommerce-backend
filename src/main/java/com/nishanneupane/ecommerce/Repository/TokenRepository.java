package com.nishanneupane.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishanneupane.ecommerce.model.AuthenticationToken;
import com.nishanneupane.ecommerce.model.User;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>{
	
	AuthenticationToken findByUser(User user);
	AuthenticationToken findByToken(String token);

}
