package com.nishanneupane.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishanneupane.ecommerce.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	List<Category> findByCategoryName(String categoryName);

}
