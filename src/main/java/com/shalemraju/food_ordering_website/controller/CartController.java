package com.shalemraju.food_ordering_website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.shalemraju.food_ordering_website.dto.CartItemResponseDto;
import com.shalemraju.food_ordering_website.payload.ApiResponse;
import com.shalemraju.food_ordering_website.pojo.AddToCartApiData;
import com.shalemraju.food_ordering_website.service.CartService;
import com.shalemraju.food_ordering_website.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;

@RestController
 class CartController {
	
	@Autowired
	CartService cartService;
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/addToCart")
	public ResponseEntity<ApiResponse<Null>> addToCart( @RequestBody AddToCartApiData addToCartApiData, Authentication authentication) throws Exception {
		
		String email = authentication.getName();
		
		cartService.addToCart(addToCartApiData, email);
		
		ApiResponse<Null> apiResponse = new ApiResponse<>(true, "Item added to cart", null);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		
	}

	 @GetMapping("/getCartIems")
	    public ResponseEntity<ApiResponse<List<CartItemResponseDto>>> getCartItems() {
	 
	        List<CartItemResponseDto> cartItems = cartService.getCartItems();
	 
	        ApiResponse<List<CartItemResponseDto>> response = new ApiResponse<>(true, "Cart items fetched successfully", cartItems);
	 
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	   }
	 
	 
	 	
	 @DeleteMapping("/deleteCartItem/{cartId}")
	 public ResponseEntity<ApiResponse<Object>> deleteCartItem( @PathVariable Long cartId,  HttpServletRequest request) {

	     String token = request.getHeader("Authorization").substring(7);

	     String email = jwtService.getJwtClaims(token).getSubject();

	     cartService.deleteCartItem(cartId, email);

	     ApiResponse<Object> response = new ApiResponse<>(true, "Item Deleted Successfully", null);

	     return ResponseEntity.ok(response);
	 }
	
	
}//final closing 
