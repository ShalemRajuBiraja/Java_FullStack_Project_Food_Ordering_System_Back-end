package com.shalemraju.food_ordering_website.pojo;

import com.shalemraju.food_ordering_website.entity.OrderEntity.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderApiData {

    // Food item
    @NotBlank(message = "Food name is required")
    private String foodName;

    private String imageUrl;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    // Delivery address
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Address line is required")
    private String addressLine;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Pincode is required")
    private String pincode;

    // Payment
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod; // COD or UPI

    // Totals
    @NotNull(message = "Delivery fee is required")
    private BigDecimal deliveryFee;

    @NotNull(message = "Total is required")
    private BigDecimal total;

    // Getters and Setters
}