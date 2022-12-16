package com.masai.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 3, max = 20, message = "Name shuld contain minimum 3 and maximum 20 character")
	private String name;

	@NotNull
	@Email(message = "Please provide a valid email")
	private String email;

	@NotNull
	@Pattern(regexp = "^[789][0-9]{9}", message = "Mobile number should be of 10 digits")
	private String mobile;

	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 20, message = "Address shuld contain minimum 3 and maximum 30 character")
	private String address;

	@NotNull
	@Min(value = 6)
	@Pattern(regexp = "[A-Za-z0-9]{15}", message = "Password must be 6 to 15 characters and must have at least 1 alphabate and 1 number")
	private String password;

}
