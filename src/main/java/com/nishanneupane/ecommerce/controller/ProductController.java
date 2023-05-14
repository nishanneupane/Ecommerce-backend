package com.nishanneupane.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishanneupane.ecommerce.Repository.CategoryRepo;
import com.nishanneupane.ecommerce.dto.ProductDto;
import com.nishanneupane.ecommerce.model.Category;
import com.nishanneupane.ecommerce.response.ApiResponse;
import com.nishanneupane.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@PostMapping("/add")
	private ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
		
		Optional<Category> opt = categoryRepo.findById(productDto.getCategoryId());
		
		return new ResponseEntity<ProductDto>(productService.createProduct(productDto,opt.get()),HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{productId}")
	private ResponseEntity<ProductDto> editProduct(@RequestBody ProductDto product,@PathVariable Integer productId){
		return new ResponseEntity<ProductDto>(productService.editProduct(product, productId),HttpStatus.OK);
	}
	
	@GetMapping("/get/{productId}")
	private ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId){
		return new ResponseEntity<ProductDto>(productService.getProductById(productId),HttpStatus.OK);
	}
	
	@GetMapping("/getname/{productName}")
	private ResponseEntity<ProductDto> getProductByName(@PathVariable String productName){
		return new ResponseEntity<ProductDto>(productService.getProductByProductName(productName),HttpStatus.OK);
	}
	
	
	@GetMapping("get/")
	private ResponseEntity<List<ProductDto>> getAllProducts(){
		return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	private ResponseEntity<ApiResponse> deleteProducts(@PathVariable Integer productId){
		productService.deleteProducts(productId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product Removed Sucessfully",true),HttpStatus.OK);
	}

}
