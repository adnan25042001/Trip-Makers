package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotelId;
	@NotNull
	@Size(min = 3, max = 20, message = " Hotel Name should contain minimum 3 and maximum 20 character")
	private String hotelname;
	@NotNull
	private String hoteltype;
	@NotNull
	private String hoteldesc;
	@NotNull
	@Size(min = 3, max = 20, message = "Address should contain minimum 3 and maximum 20 character")
	private String address;
	@NotNull
	private double rent;
	@NotNull
	private String status;

}
