package com.nishanneupane.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishanneupane.ecommerce.model.User;
import com.nishanneupane.ecommerce.model.Whislist;

public interface WhislistRepo extends JpaRepository<Whislist, Integer>{
	List<Whislist> findByUserOrderByCreatedDateDesc(User user);


	

}
