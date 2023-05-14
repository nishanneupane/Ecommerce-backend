package com.nishanneupane.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishanneupane.ecommerce.Repository.ProductRepository;
import com.nishanneupane.ecommerce.dto.ProductDto;
import com.nishanneupane.ecommerce.exception.ProductNotExistsException;
import com.nishanneupane.ecommerce.model.Category;
import com.nishanneupane.ecommerce.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDto createProduct(ProductDto productDto,Category category) {
		
		Product product = new Product();
		product.setId(productDto.getId());
		product.setImageUrl(productDto.getImageUrl());
		product.setPrice(productDto.getPrice());
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setCategory(category);
		productRepository.save(product);
		
		return modelMapper.map(product, ProductDto.class);
	}
	
	public ProductDto editProduct(ProductDto productDto,Integer productId) {
		Product product = productRepository.findById(productId).orElseThrow();
		
		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());
		product.setProductDescription(productDto.getProductDescription());
		product.setImageUrl(productDto.getImageUrl());
		
		productRepository.save(product);
		
		return modelMapper.map(product, ProductDto.class);
	}
	
	public List<ProductDto> getAllProducts(){
		List<Product> products = productRepository.findAll();
		
		
		List<ProductDto> dtos = products.stream().map(product->modelMapper.map(products, ProductDto.class)).collect(Collectors.toList());
		
		return dtos;
	}
	
	public ProductDto getProductById(Integer productId) {
		Product product = productRepository.findById(productId).orElseThrow();
		
		return modelMapper.map(product, ProductDto.class);
	}
	
	public List<ProductDto> getProductByCategory(Category categoryName) {
		List<ProductDto> product = productRepository.findByCategory(categoryName);
		
		List<ProductDto> collect = product.stream().map(pro->modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return collect;
	}
	
	
	public ProductDto getProductByProductName(String productName) {
		Product product = productRepository.findByProductName(productName);
		
//		List<ProductDto> collect = product.stream().map(pro->modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return modelMapper.map(product, ProductDto.class);
	}
	
	public void deleteProducts(Integer productId) {
		productRepository.deleteById(productId);
	}
	
	public ProductDto getProductDto(Product product) {
		ProductDto dto=new ProductDto();
		dto.setId(product.getId());
		dto.setCategoryId(product.getCategory().getId());
		dto.setImageUrl(product.getImageUrl());
		dto.setPrice(product.getPrice());
		dto.setProductDescription(product.getProductDescription());
		dto.setProductName(product.getProductName());
		
		return dto;
	}

	public Product findById(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		
		if(product.isEmpty()) {
			throw new ProductNotExistsException("Product is invalid "+productId);
		}
		
		return product.get();
		
	}

}