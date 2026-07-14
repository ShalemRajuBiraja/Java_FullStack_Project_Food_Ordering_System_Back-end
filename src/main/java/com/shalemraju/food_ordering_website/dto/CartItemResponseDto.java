package com.shalemraju.food_ordering_website.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartItemResponseDto {
	
	private Long cartId;
	private String foodName;
	private String imageUrl;
	private BigDecimal price;
	private Integer quantity;

}
