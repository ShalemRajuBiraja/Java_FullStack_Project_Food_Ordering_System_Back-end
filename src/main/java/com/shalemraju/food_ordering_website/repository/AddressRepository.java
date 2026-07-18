package com.shalemraju.food_ordering_website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shalemraju.food_ordering_website.entity.AddressEntity;


public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
	
	Optional<AddressEntity> findByUserId (Long userId);
	Optional<AddressEntity> deleteByUserId (Long userId);

}
