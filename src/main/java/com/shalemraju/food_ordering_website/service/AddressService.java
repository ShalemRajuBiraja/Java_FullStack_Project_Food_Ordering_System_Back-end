package com.shalemraju.food_ordering_website.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.shalemraju.food_ordering_website.entity.AddressEntity;
import com.shalemraju.food_ordering_website.entity.UserEntity;
import com.shalemraju.food_ordering_website.pojo.AddAddressData;
import com.shalemraju.food_ordering_website.repository.AddressRepository;
import com.shalemraju.food_ordering_website.repository.AuthRepository;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AuthRepository authRepository;

    // Add Address
    public void addAddress(AddAddressData addAddressData, String email){

    	//getting userId with email
    	Optional<UserEntity> ifEmailExistsOptional = authRepository.findByEmail(email);
    		if(ifEmailExistsOptional.isEmpty()) {
    			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with given request");
    		}
    		
    	Long userId = ifEmailExistsOptional.get().getUserId();
    	
    	AddressEntity addressEntity = new AddressEntity();
    	addressEntity.setUserId(userId);
    	addressEntity.setFullName(addAddressData.getFullName());
    	addressEntity.setPhone(addAddressData.getPhone());
    	addressEntity.setAddressLine(addAddressData.getAddressLine());
    	addressEntity.setCity(addAddressData.getCity());
    	addressEntity.setState(addAddressData.getState());
    	addressEntity.setPincode(addAddressData.getPincode());
    	
    AddressEntity savedAddressEntity = 	addressRepository.save(addressEntity);
    }

    // GET ADDRESS
    public Optional<AddressEntity> getAddress(String email) {

        Optional<UserEntity> userOptional = authRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(	HttpStatus.BAD_REQUEST, "User not found");
        }

        Long userId = userOptional.get().getUserId();

       return  addressRepository.findByUserId(userId);
    }        
    
    // Delete Address
    public void deleteAddress(String email){

    	 Optional<UserEntity> userOptional = authRepository.findByEmail(email);

         if (userOptional.isEmpty()) {
             throw new ResponseStatusException(
                     HttpStatus.BAD_REQUEST,
                     "User not found");
         }
         
         Long userId = userOptional.get().getUserId();
          Optional<?> ifUserAddress = addressRepository.findByUserId(userId);
          	if(ifUserAddress.isEmpty()) {
          		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
          	}
         
          	System.out.println(userId);
          	addressRepository.deleteByUserId(userId);
      	

    }

}