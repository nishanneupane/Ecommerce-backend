package com.nishanneupane.ecommerce.controller;

import java.util.List;

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

import com.nishanneupane.ecommerce.model.Category;
import com.nishanneupane.ecommerce.response.ApiResponse;
import com.nishanneupane.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	private ResponseEntity<Category> createCategory(@RequestBody Category category){
		return new ResponseEntity<Category>(categoryService.createCategory(category),HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	private ResponseEntity<List<Category>> getAllCategory(){
		return new ResponseEntity<List<Category>>(categoryService.listAllCategory(),HttpStatus.OK);
	}
	
	@GetMapping("/get/by-id/{categoryId}")
	private ResponseEntity<Category> findCategoryById(@PathVariable Integer categoryId){
		return new ResponseEntity<Category>(categoryService.getCategoryById(categoryId),HttpStatus.OK);
	}
	
	@GetMapping("/get/by-name/{categoryName}")
	private ResponseEntity<List<Category>> findCategoryByName(@PathVariable String categoryName){
		return new ResponseEntity<List<Category>>(categoryService.findByCategoryName(categoryName),HttpStatus.OK);
	}
	
	@PutMapping("/edit/{categoryId}")
	private ResponseEntity<Category> editCategory(@RequestBody Category category,@PathVariable Integer categoryId){
		return new ResponseEntity<Category>(categoryService.editCategory(category, categoryId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	private ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Sucessfully",true),HttpStatus.OK);
	}
	

}
