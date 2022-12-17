package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
public class Feedback {

	//id refers to customer Id.By which admin can find which customer give this feedback;
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
