package com.shalemraju.food_ordering_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shalemraju.food_ordering_website.payload.ApiResponse;
import com.shalemraju.food_ordering_website.pojo.ContactApiData;
import com.shalemraju.food_ordering_website.service.ContactService;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/submit-contact-form")
    public ResponseEntity<ApiResponse<String>> createFeedback(
            @Validated @RequestBody ContactApiData contactApiData,
            @AuthenticationPrincipal UserDetails userDetails) {

        contactService.createFeedback(contactApiData, userDetails);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Feedback Submitted Successfully",
                        null)
        );

    }

}