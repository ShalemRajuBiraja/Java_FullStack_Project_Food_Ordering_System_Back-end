package com.shalemraju.food_ordering_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shalemraju.food_ordering_website.entity.FoodEntity;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

}
