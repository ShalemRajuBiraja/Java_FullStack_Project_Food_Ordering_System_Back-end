package com.shalemraju.food_ordering_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shalemraju.food_ordering_website.dto.FoodResponseDto;
import com.shalemraju.food_ordering_website.payload.ApiResponse;
import com.shalemraju.food_ordering_website.service.FoodService;

@RestController
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	@GetMapping("/api/get/foods")
	public ResponseEntity<ApiResponse<Page<FoodResponseDto>> > getFoodItems( @RequestParam int page, @RequestParam int size) throws Exception {
			
		Page<FoodResponseDto>  serviceResponsePage = foodService.getFoodsList(page,size);
		
		ApiResponse<Page<FoodResponseDto>> apiResponse = new ApiResponse<>(true, "Food data fetched successfully",serviceResponsePage);
			
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}//getfoods closing
	
	

	
	
	
}//final closing bracer
