package com.shalemraju.food_ordering_website.controller;

import com.shalemraju.food_ordering_website.dto.OrderResponse;
import com.shalemraju.food_ordering_website.payload.ApiResponse;
import com.shalemraju.food_ordering_website.pojo.OrderApiData;
import com.shalemraju.food_ordering_website.repository.AuthRepository;
import com.shalemraju.food_ordering_website.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	
    @PostMapping("placeOrder")
    public ResponseEntity<ApiResponse<String>> placeOrder( @Valid @RequestBody OrderApiData orderApiData, Authentication authentication) {

    	String email = authentication.getName();
         orderService.placeOrder(orderApiData, email);

        ApiResponse<String> response = new ApiResponse<>(true, "Order placed successfully", null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    
    @GetMapping("getOrdersList")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrders( Authentication authentication) {

    	String email = authentication.getName();
    	
    	List<OrderResponse> orders = orderService.getOrders(email);

        ApiResponse<List<OrderResponse> > response = new ApiResponse<>(true, "Orders fetched successfully", orders);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}