package com.masai.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
	
	@NotNull
	private Integer id;
	@NotNull
	private double rent;
	@NotNull
	private String status;

}
