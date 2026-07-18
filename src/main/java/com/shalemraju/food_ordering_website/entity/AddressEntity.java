
package com.shalemraju.food_ordering_website.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
@Table(name = "addresses")
@Data
public class AddressEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "address_id")
	    private Long addressId;

	    @Column(name = "user_id", nullable = false)
	    private Long userId;

	    @Column(name = "full_name", nullable = false)
	    private String fullName;

	    @Column(nullable = false, length = 10)
	    private String phone;

	    @Column(name = "address_line", nullable = false)
	    private String addressLine;

	    @Column(nullable = false)
	    private String city;

	    @Column(nullable = false)
	    private String state;

	    @Column(nullable = false, length = 6)
	    private String pincode;

	    @Column(name = "created_on", nullable = false)
	    private LocalDateTime createdOn;

	    @Column(name = "updated_on", nullable = false)
	    private LocalDateTime updatedOn;
	    
	    @PrePersist
	    public void prePersist() {

	        createdOn = LocalDateTime.now();
	        updatedOn = LocalDateTime.now();

	    }

	    @PreUpdate
	    public void preUpdate() {

	        updatedOn = LocalDateTime.now();

	    }

}