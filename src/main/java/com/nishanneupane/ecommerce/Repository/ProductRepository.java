package com.nishanneupane.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishanneupane.ecommerce.dto.ProductDto;
import com.nishanneupane.ecommerce.model.Category;
import com.nishanneupane.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<ProductDto> findByCategory(Category categoryName);
	Product findByProductName(String productName);
	

}
