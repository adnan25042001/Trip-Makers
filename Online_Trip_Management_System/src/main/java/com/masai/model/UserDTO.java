package com.masai.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
	
	@NotBlank @NotEmpty @Size(min = 3, max = 20, message = "Name shuld contain minimum 3 and maximum 20 character")
	private String email;
	
	@Pattern(regexp = "[A-Za-z0-9@]{6,15}", message = "Password must be 6 to 15 characters and must have at least 1 alphabate and 1 number")
	private String password;
	
	@NotNull
	private UserType userType;

}
