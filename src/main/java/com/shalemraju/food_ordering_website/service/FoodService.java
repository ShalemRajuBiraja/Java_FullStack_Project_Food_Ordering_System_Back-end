package com.shalemraju.food_ordering_website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shalemraju.food_ordering_website.dto.FoodResponseDto;
import com.shalemraju.food_ordering_website.entity.FoodEntity;
import com.shalemraju.food_ordering_website.repository.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;
	
	
	
	public Page<FoodResponseDto> getFoodsList( int page, int size) throws Exception {
		
        Pageable pageable = PageRequest.of(page, size);

		
        Page<FoodEntity> foodPage = 	foodRepository.findAll(pageable);
        
        if(foodPage.isEmpty()) {
        	throw new Exception("No data found");
        }
        
        Page<FoodResponseDto> response = foodPage.map( foodOne -> {
        	
        	FoodResponseDto foodResponseDto = new FoodResponseDto();
        	foodResponseDto.setFoodId(foodOne.getFoodId());
        	foodResponseDto.setFoodName(foodOne.getFoodName());
        	foodResponseDto.setDescription(foodOne.getDescription());
        	foodResponseDto.setFoodPrice(foodOne.getFoodPrice());
        	foodResponseDto.setImageUrl(foodOne.getImageUrl());
        	
        	return foodResponseDto;
        	
        });
        
        return response;
		
	}//getfoodlist closing

}//final closing
