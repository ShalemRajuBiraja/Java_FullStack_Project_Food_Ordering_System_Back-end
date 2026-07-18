package com.shalemraju.food_ordering_website.repository;

import com.shalemraju.food_ordering_website.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // Fetch all orders for a specific user, newest first
    List<OrderEntity> findByUserId(Long userId);
}