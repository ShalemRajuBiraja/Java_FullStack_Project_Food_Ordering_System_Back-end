package com.shalemraju.food_ordering_website.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "users")
@Data 
public class UserEntity {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",nullable = false)
    private Long userId;


    @Column(name="full_name",nullable = false)
    private String fullName;


    @Column(name="email",nullable = false, unique = true)
    private String email;


    @Column(name="password", nullable = false)
    private String password;


    @Column(name="mobile_number", nullable = false, unique = true)
    private String mobileNumber;


    @Column(name="user",nullable = false)
    private String role = "USER";


    private LocalDateTime createdOn;


    private LocalDateTime updatedOn;


}
