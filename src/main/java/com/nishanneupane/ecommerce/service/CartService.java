package com.nishanneupane.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishanneupane.ecommerce.Repository.CartRepo;
import com.nishanneupane.ecommerce.dto.cart.AddtoCartDto;
import com.nishanneupane.ecommerce.dto.cart.CartDto;
import com.nishanneupane.ecommerce.dto.cart.CartItemDto;
import com.nishanneupane.ecommerce.exception.CustomException;
import com.nishanneupane.ecommerce.model.Cart;
import com.nishanneupane.ecommerce.model.Product;
import com.nishanneupane.ecommerce.model.User;

@Service
public class CartService {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartRepo cartRepo;

	public Cart addToCart(AddtoCartDto addtoCartDto, User user) {
		
		//validate if the product id is valid
		
		Product product = productService.findById(addtoCartDto.getProductId());
		
		Cart cart=new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addtoCartDto.getQuantity());
		cart.setCreatedDate(new Date());
		
		//save to the cart
		
		return cartRepo.save(cart);
	}

	public CartDto listCartItems(User user) {
		
		List<Cart> cartlists = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
		
		List<CartItemDto> cartItem=new ArrayList<>();
		
		double totalCost=0;
		
		for(Cart cart:cartlists) {
			CartItemDto cartItemDto=new CartItemDto();
			cartItemDto.setId(cart.getId());
			cartItemDto.setProduct(cart.getProduct());
			cartItemDto.setQuantity(cart.getQuantity());
			totalCost+=cartItemDto.getQuantity()*cart.getProduct().getPrice();
			
			cartItem.add(cartItemDto);
		}
		
		CartDto cartDto=new CartDto();
		cartDto.setCartItems(cartItem);
		cartDto.setTotalCost(totalCost);
		return cartDto;
	}

	public void deleteItem(Integer cartItemId, User user) {
		// the item id belongs to user
		
		Optional<Cart> optcart=cartRepo.findById(cartItemId);
		
		if(optcart.isEmpty()) {
			throw new CustomException("cart item not valid with id  "+cartItemId);
		}
		
		Cart cart=optcart.get();
		
		if(cart.getUser()!=user) {
			throw new CustomException("card item doesnt belongs to user : "+cartItemId);
		}
		
		cartRepo.delete(cart);
		
	}

}
