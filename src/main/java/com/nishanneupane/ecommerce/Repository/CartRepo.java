package com.nishanneupane.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishanneupane.ecommerce.model.Cart;
import com.nishanneupane.ecommerce.model.User;

public interface CartRepo extends JpaRepository<Cart, Integer>{
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

}
