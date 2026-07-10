package com.shalemraju.food_ordering_website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shalemraju.food_ordering_website.entity.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);
	

}
