package com.shalemraju.food_ordering_website.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.shalemraju.food_ordering_website.entity.AddressEntity;
import com.shalemraju.food_ordering_website.payload.ApiResponse;
import com.shalemraju.food_ordering_website.pojo.AddAddressData;
import com.shalemraju.food_ordering_website.service.AddressService;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/addAddress")
    public ResponseEntity<ApiResponse<Object>> addAddress(@RequestBody AddAddressData addAddressData, Authentication authentication){
    	
	    String email =	authentication.getName();
	    
	    addressService.addAddress(addAddressData, email);
	    
	    ApiResponse<Object> response = new ApiResponse<Object>(true, "Address Added Successfully", null);
	    
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/getAddress")
    public ResponseEntity<ApiResponse<Optional<AddressEntity>> > getAddress( Authentication authentication){
    	
	    String email =	authentication.getName();
	    
	    Optional<AddressEntity> userAddresssData =  addressService.getAddress( email );
	    
	    ApiResponse<Optional<AddressEntity>> response = new ApiResponse<> (true, "Address fetched Successfully", userAddresssData);
	    
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<ApiResponse<String>> deleteAddress( Authentication authentication){

    	String email = authentication.getName();
        addressService.deleteAddress(email);
        
        ApiResponse<String> response = new ApiResponse<String>(true, "Address Deleted", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}