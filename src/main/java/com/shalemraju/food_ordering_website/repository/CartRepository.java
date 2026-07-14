package com.shalemraju.food_ordering_website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalemraju.food_ordering_website.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

	Optional<CartEntity>  findByUserIdAndFoodName(Long userId, String foodName);
}
