package com.shalemraju.food_ordering_website.dto;

import com.shalemraju.food_ordering_website.entity.UserEntity;

import lombok.Data;

@Data
public class LoginResponseDto {
	
	public UserEntity userData;
	public String token;
	

}
