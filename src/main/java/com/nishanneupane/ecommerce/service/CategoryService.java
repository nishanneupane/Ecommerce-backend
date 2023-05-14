package com.nishanneupane.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishanneupane.ecommerce.Repository.CategoryRepo;
import com.nishanneupane.ecommerce.model.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
		
	}
	
	public List<Category> listAllCategory(){
		return categoryRepo.findAll();
	}
	
	public Category getCategoryById(Integer categoryId) {
		return categoryRepo.findById(categoryId).orElseThrow();
	}
	
	public Category editCategory(Category category,Integer categoryId) {
		Category cats = categoryRepo.findById(categoryId).orElseThrow();
		cats.setCategoryName(category.getCategoryName());
		cats.setImageUrl(category.getImageUrl());
		cats.setCategoryDescription(category.getCategoryDescription());
		
		return categoryRepo.save(cats);
	}
	
	public void deleteCategory(Integer categoryId) {
		categoryRepo.deleteById(categoryId);
	}
	
	public List<Category> findByCategoryName(String categoryName){
		return categoryRepo.findByCategoryName(categoryName);
	}

}
