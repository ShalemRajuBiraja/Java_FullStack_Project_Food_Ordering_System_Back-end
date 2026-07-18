package com.shalemraju.food_ordering_website.service;

import com.shalemraju.food_ordering_website.dto.OrderResponse;
import com.shalemraju.food_ordering_website.entity.OrderEntity;
import com.shalemraju.food_ordering_website.entity.UserEntity;
import com.shalemraju.food_ordering_website.pojo.OrderApiData;
import com.shalemraju.food_ordering_website.repository.AuthRepository;
import com.shalemraju.food_ordering_website.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

		@Autowired
		AuthRepository authRepository;
		@Autowired
		OrderRepository orderRepository;
	
		public void placeOrder(OrderApiData orderApiData, String email) {
    	
		//getting userId 
		Optional<UserEntity> userData = authRepository.findByEmail(email);
			Long userId = userData.get().getUserId();
			
			
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);

        // Food item
        order.setFoodName(orderApiData.getFoodName());
        order.setImageUrl(orderApiData.getImageUrl());
        order.setPrice(orderApiData.getPrice());
        order.setQuantity(orderApiData.getQuantity());

        // Delivery address
        order.setFullName(orderApiData.getFullName());
        order.setPhone(orderApiData.getPhone());
        order.setAddressLine(orderApiData.getAddressLine());
        order.setCity(orderApiData.getCity());
        order.setState(orderApiData.getState());
        order.setPincode(orderApiData.getPincode());

        // Payment
        order.setPaymentMethod(orderApiData.getPaymentMethod());

        // Totals
        order.setDeliveryFee(orderApiData.getDeliveryFee());
        order.setTotal(orderApiData.getTotal());

        // Status defaults to Pending (set in entity)
        orderRepository.save(order);

		}
		
		
		
		// GET ORDERS FUNCTION
		public List<OrderResponse> getOrders(String email) {

		    UserEntity user = authRepository.findByEmail(email)
		            .orElseThrow(() ->
		                    new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

		    Long userId = user.getUserId();

		    List<OrderEntity> orders = orderRepository.findByUserId(userId);

		    if (orders.isEmpty()) {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Orders Found");
		    }

		    List<OrderResponse> orderResponses = new ArrayList<>();

		    for (OrderEntity order : orders) {

		        OrderResponse response = new OrderResponse();

		        response.setOrderId(order.getOrderId());
		        response.setFoodName(order.getFoodName());
		        response.setImageUrl(order.getImageUrl());
		        response.setPrice(order.getPrice());
		        response.setQuantity(order.getQuantity());
		        response.setFullName(order.getFullName());
		        response.setPhone(order.getPhone());
		        response.setAddressLine(order.getAddressLine());
		        response.setCity(order.getCity());
		        response.setState(order.getState());
		        response.setPincode(order.getPincode());

		        // Use the status stored in the database
		        response.setStatus("Pending");

		        response.setTotal(order.getTotal());

		        orderResponses.add(response);
		    }

		    return orderResponses;
		}
	        
}