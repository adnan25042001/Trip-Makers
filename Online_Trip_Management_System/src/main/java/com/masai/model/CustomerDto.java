package com.masai.model;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	
	
	@Size(min = 3, max = 20, message = "Name shuld contain minimum 3 and maximum 20 character")
	private String customerName;
	
	@Email(message="Enter your Email properly")
	private String email;
	
	@Size(min = 3, max = 20, message = "Name shuld contain minimum 3 and maximum 20 character")
	private String address;
	
	@Column(unique = true)
	@Pattern(regexp = "^[789][0-9]{9}", message = "Mobile number should be of 10 digits")
	private String mobile;
	
}
