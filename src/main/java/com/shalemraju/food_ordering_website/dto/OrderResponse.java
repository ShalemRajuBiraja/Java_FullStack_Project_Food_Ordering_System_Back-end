package com.shalemraju.food_ordering_website.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderResponse {

	private Long orderId;
    private String foodName;
    private String imageUrl;
    private BigDecimal price;
    private Integer quantity;
    private String fullName;
    private String phone;
    private String addressLine;
    private String city;
    private String state;
    private String pincode;
    private String paymentMethod;
    private String status;
    private BigDecimal total;

}