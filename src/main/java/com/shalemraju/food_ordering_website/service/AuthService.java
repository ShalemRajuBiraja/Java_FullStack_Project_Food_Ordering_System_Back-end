package com.shalemraju.food_ordering_website.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalemraju.food_ordering_website.entity.UserEntity;
import com.shalemraju.food_ordering_website.pojo.CreateAccountApiData;
import com.shalemraju.food_ordering_website.repository.AuthRepository;

@Service
public class AuthService {
	
	@Autowired
	AuthRepository authRepository;
	
	public void createAccount(CreateAccountApiData createAccountApiData) {
		
	Optional<UserEntity> isEmailExists = authRepository.findByEmail(createAccountApiData.getEmail());
		
		if(isEmailExists.isPresent()) {
			throw new RuntimeException("Email already exists");
		}
		
		UserEntity userEntity = new UserEntity();
		userEntity.setFullName(createAccountApiData.getFullName());
		userEntity.setEmail(createAccountApiData.getEmail());
		userEntity.setMobileNumber(createAccountApiData.getMobileNumber());
		userEntity.setPassword(createAccountApiData.getPassword());
	
		authRepository.save(userEntity);
		
		
	}
	
	public void login(String username, String password) {
		
		
	}

}
