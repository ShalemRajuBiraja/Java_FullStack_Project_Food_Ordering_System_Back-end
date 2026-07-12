package com.shalemraju.food_ordering_website.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FoodResponseDto {

	private Long foodId;
    private String foodName;
    private String description;
    private BigDecimal foodPrice;
    private String imageUrl;
}
