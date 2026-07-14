package com.shalemraju.food_ordering_website.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.shalemraju.food_ordering_website.dto.CartItemResponseDto;
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
	

// GET CART ITEMS FUNCTION`	
	  public List<CartItemResponseDto> getCartItems() {
	        // The JwtRequestFilter puts the logged-in user's email into the
	        // SecurityContext as the "username" (see: userDetails.username(email))
	        String email = SecurityContextHolder.getContext().getAuthentication().getName();
	 
	        UserEntity user = authRepository.findByEmail(email)
	                .orElseThrow(() -> new ResponseStatusException(
	                        HttpStatus.UNAUTHORIZED, "User not found"));
	 
	        List<CartEntity> cartEntities = cartRepository.findByUserId(user.getUserId());
			
	        List<CartItemResponseDto> cartList = new ArrayList<CartItemResponseDto>();
	        
	        for(CartEntity eachCart : cartEntities) {
	        	
	        	CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
	        	
	        	cartItemResponseDto.setCartId(eachCart.getCartId());
	        	cartItemResponseDto.setFoodName(eachCart.getFoodName());
	        	cartItemResponseDto.setPrice(eachCart.getPrice());
	        	cartItemResponseDto.setQuantity(eachCart.getQuantity());
	        	cartItemResponseDto.setImageUrl(eachCart.getImageUrl());
	        	
	        	cartList.add(cartItemResponseDto);
	        	
	        }
	        
	        return cartList;
	   }
	  
	 
// DELTE FUNCTION	  
	  		public void deleteCartItem(Long cartId, String email) {

		    // Find logged-in user
		    UserEntity user = authRepository.findByEmail(email)
		            .orElseThrow(() -> new RuntimeException("User not found"));

		    // Find cart item
		    CartEntity cart = cartRepository.findById(cartId)
		            .orElseThrow(() -> new RuntimeException("Cart item not found"));

		    // Security check
		    if (!cart.getUserId().equals(user.getUserId())) {
		        throw new RuntimeException("You are not authorized to delete this cart item");
		    }

		    // Delete
		    cartRepository.deleteById(cartId);
		}
	  
	  

}//final cloging
