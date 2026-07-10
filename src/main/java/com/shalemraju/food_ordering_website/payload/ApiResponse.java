package com.shalemraju.food_ordering_website.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ee class response estadi, response eppatikapdu maratadi kabati different vi create chikunda GENERIC handle chistam
//vachina input ey data type vuntado ala set chisukuntadi
//data lo ki vachey data eppatikapud marepotuvuntadi, so different chikunda GENERIC tho handle chistam

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ApiResponse<T> {
	

	private boolean success;
	private String message;
	private T data;      
	
	
}
