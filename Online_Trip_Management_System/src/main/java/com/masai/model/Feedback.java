package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "Please pass your userId / CustomerId")
	private Integer customerId;
	
	private String message;
	
	@NotNull
	@Max(value =  5,message = "please rate us in between 0 to 5")
	private Integer rating;
	
	private LocalDateTime date;
	
}
