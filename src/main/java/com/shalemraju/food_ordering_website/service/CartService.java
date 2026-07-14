package com.shalemraju.food_ordering_website.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.shalemraju.food_ordering_website.entity.CartEntity;
import com.shalemraju.food_ordering_website.entity.UserEntity;
import com.shalemraju.food_ordering_website.pojo.AddToCartApiData;
import com.shalemraju.food_ordering_website.repository.AuthRepository;
import com.shalemraju.food_ordering_website.repository.CartRepository;
import com.shalemraju.food_ordering_website.repository.FoodRepository;

@Service
public class CartService {
	
	@Autowired
	FoodRepository foodRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	AuthRepository authRepository;
	
	
	
// ADD TO CART METHOD
	public void addToCart(AddToCartApiData addToCartApiData, String email) throws Exception {

		//Getting userId
		Optional<UserEntity> repoResponse = authRepository.findByEmail(email);
		UserEntity userData = repoResponse.get();
		
		Long userId = userData.getUserId();
		String foodName = addToCartApiData.getFoodName();
		
		
	
	    Optional<CartEntity> cartItem = cartRepository.findByUserIdAndFoodName( userId , foodName);

	    if (cartItem.isPresent()) {
	    	throw new ResponseStatusException(HttpStatus.CONFLICT,"Food already exists in cart");	    }

	    
	    CartEntity cart = new CartEntity();

	    cart.setUserId(userId);
	    cart.setFoodName(addToCartApiData.getFoodName());
	    cart.setPrice(addToCartApiData.getFoodPrice());
	    cart.setQuantity(addToCartApiData.getQuantity());
	    cart.setImageUrl(addToCartApiData.getImageUrl());
	    
	    cartRepository.save(cart);
	}// ADD TO CART METHOD CLOSED

}
