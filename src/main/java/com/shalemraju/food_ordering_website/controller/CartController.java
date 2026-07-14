package com.shalemraju.food_ordering_website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shalemraju.food_ordering_website.dto.CartItemResponseDto;
import com.shalemraju.food_ordering_website.payload.ApiResponse;
import com.shalemraju.food_ordering_website.pojo.AddToCartApiData;
import com.shalemraju.food_ordering_website.service.CartService;

import jakarta.validation.constraints.Null;

@RestController
 class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/addToCart")
	public ResponseEntity<ApiResponse<Null>> addToCart( @RequestBody AddToCartApiData addToCartApiData, Authentication authentication) throws Exception {
		
		String email = authentication.getName();
		
		cartService.addToCart(addToCartApiData, email);
		
		ApiResponse<Null> apiResponse = new ApiResponse<>(true, "Item added to cart", null);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		
	}

	 @GetMapping
	    public ResponseEntity<ApiResponse<List<CartItemResponseDto>>> getCartItems() {
	 
	        List<CartItemResponseDto> cartItems = cartService.getCartItems();
	 
	        ApiResponse<List<CartItemResponseDto>> response = new ApiResponse<>(true, "Cart items fetched successfully", cartItems);
	 
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    }
	
	
}//final closing 
