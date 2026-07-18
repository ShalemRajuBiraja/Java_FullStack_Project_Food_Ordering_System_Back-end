package com.shalemraju.food_ordering_website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.shalemraju.food_ordering_website.entity.ContactEntity;
import com.shalemraju.food_ordering_website.entity.UserEntity;
import com.shalemraju.food_ordering_website.pojo.ContactApiData;
import com.shalemraju.food_ordering_website.repository.AuthRepository;
import com.shalemraju.food_ordering_website.repository.ContactRepository;


@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AuthRepository authRepository;

    public void createFeedback(ContactApiData contactApiData, UserDetails userDetails) {

        UserEntity user = authRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        ContactEntity entity = new ContactEntity();

        entity.setUserId(user.getUserId());
        entity.setFullName(contactApiData.getFullName());
        entity.setEmail(contactApiData.getEmail());
        entity.setPhone(contactApiData.getPhone());
        entity.setSubject(contactApiData.getSubject());
        entity.setIssueType(contactApiData.getIssueType());
        entity.setOrderId(contactApiData.getOrderId());
        entity.setMessage(contactApiData.getMessage());
        entity.setStatus("OPEN");

        System.out.println(entity);
        contactRepository.save(entity);

    }

}