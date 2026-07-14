package com.shalemraju.food_ordering_website.pojo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddToCartApiData {
	
	 private String foodName;
	 private BigDecimal foodPrice;
	 private Integer foodId;
	 private Integer quantity;
	 private String imageUrl;

}
