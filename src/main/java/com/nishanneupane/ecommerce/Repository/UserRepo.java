package com.nishanneupane.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishanneupane.ecommerce.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);

}
