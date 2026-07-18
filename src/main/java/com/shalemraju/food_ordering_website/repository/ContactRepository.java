package com.shalemraju.food_ordering_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shalemraju.food_ordering_website.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

}
