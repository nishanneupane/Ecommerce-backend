package com.nishanneupane.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishanneupane.ecommerce.Repository.WhislistRepo;
import com.nishanneupane.ecommerce.dto.ProductDto;
import com.nishanneupane.ecommerce.model.User;
import com.nishanneupane.ecommerce.model.Whislist;

@Service
public class WhislistService {

	@Autowired
	private WhislistRepo whislistRepo;
	
	@Autowired
	private ProductService productService;

	public Whislist post(Whislist whislist) {
		return whislistRepo.save(whislist);
	}

	public List<ProductDto> getWhislistForUser(User user){
		
		List<Whislist> whislist = whislistRepo.findByUserOrderByCreatedDateDesc(user);
		
		List<ProductDto> productDtos=new ArrayList<>();
		
		for(Whislist whislist2:whislist) {
			productDtos.add(productService.getProductDto(whislist2.getProduct()));
		}
				
		return productDtos;
	}

}
