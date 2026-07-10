package com.shalemraju.food_ordering_website.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAccountApiData {
	
	    @NotBlank(message = "Full name is required")
	    @Size(min = 3, max = 100, message = "Name min 3 chars")
	    private String fullName;

	    @NotBlank(message = "Email is required")
	    @Email(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$/", message = "Enter a valid email address")
	    private String email;

	    @NotBlank(message = "Mobile number is required")
	    @Pattern(regexp = "/^[0-9]{10}$/", message = "Enter 10-digit mobile number")
	    private String mobileNumber;

	    @NotBlank(message = "Password is required")
	    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
	    @Pattern(
	        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$",
	        message = "Password must contain uppercase, lowercase and number"
	    )
	    private String password;
}
