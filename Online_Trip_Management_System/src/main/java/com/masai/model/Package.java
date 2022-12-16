package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Package {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer packageId;

	@NotNull(message = "Package name is mandatory")
	@NotBlank
	@NotEmpty
	@Size(min = 5, max = 15, message = "Package name should be of 5-7 charecters")
	private String packageName;

	@NotNull(message = "Package type should be mandatory")
	@NotBlank
	@NotEmpty
	@Size(min = 5, max = 10, message = "Package Type should be of 5-10 charecters")
	private String packageType;

	@NotNull(message = " is mandatory")
	@NotBlank
	@NotEmpty
	@Size(min = 5, max = 10, message = "Package Description should be of 5-50 charecters")
	private String packageDescription;

	@NotNull
	@NotBlank
	@NotEmpty
	private Integer packageCost;


}
