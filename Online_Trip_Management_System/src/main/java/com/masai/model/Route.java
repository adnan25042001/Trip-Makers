package com.masai.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	@NotNull(message = "route name is mandatory")
	@NotEmpty
	@Size(min = 3, max = 20, message = "route name should be minimum = 3 and maximum = 20 characters")
	private String routeFrom;
	
	@NotNull(message = "route name is mandatory")
	@NotEmpty
	@Size(min = 3, max = 20, message = "route name should be minimum = 3 and maximum = 20 characters")
	private String routeTo;
	
	@NotNull
	private LocalDateTime departureTime;
	@NotNull
	private LocalDateTime arrivalTime;
	@NotNull
	private LocalDate dateOfJourney;
	
	@NotNull(message = "Pickup point name is mandatory")
	@NotEmpty
	@Size(min = 3, max = 20, message = "Pickup point name should be minimum = 3 and maximum = 20 characters")
	private String pickupPoint;
	
	@Min(value = 0, message = "Fare should be atleast 0")
	private Double fare;

}
