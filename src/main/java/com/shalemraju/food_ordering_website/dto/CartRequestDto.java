package com.shalemraju.food_ordering_website.dto;

import lombok.Data;

@Data
public class CartRequestDto {

    private Long foodId;
    private Integer quantity;

    // Getters and Setters
}