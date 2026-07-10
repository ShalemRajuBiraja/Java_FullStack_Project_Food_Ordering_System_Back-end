package com.shalemraju.food_ordering_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shalemraju.food_ordering_website.entity.UserEntity;
import com.shalemraju.food_ordering_website.payload.ApiResponse;
import com.shalemraju.food_ordering_website.pojo.CreateAccountApiData;
import com.shalemraju.food_ordering_website.pojo.LoginApiData;
import com.shalemraju.food_ordering_website.service.AuthService;
import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	
	@PostMapping("/auth/create-account")
	public ResponseEntity<ApiResponse<UserEntity>> login( @Valid @RequestBody CreateAccountApiData createAccountApiData) {
		
		authService.createAccount(createAccountApiData);
		
		ApiResponse<UserEntity> apiResponse = new ApiResponse<>(true, "Account created successfully", null);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@PostMapping("/auth/login")
	public String login( @Valid  @RequestBody  LoginApiData loginApiData) {
		
		
		return "Login successful";
	}



}
