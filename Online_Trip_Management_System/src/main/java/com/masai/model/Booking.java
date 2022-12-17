package com.masai.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
     
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	
	@NotNull(message="Booking Type should be mandatory  is mandatory")
	@NotBlank @NotEmpty
	@Size(min = 5, max = 15, message = "Type should be of 5-7 charecters")
	private String bookingType;
	
	@NotNull (message="Description should be is mandatory")
	@NotBlank @NotEmpty
	@Size(min = 5, max = 50, message = "Description should be of 5-50 charecters")
	private String description;
	
	@NotNull(message="Booking Title should be mandatory")
	@NotBlank @NotEmpty
	private String bookingTitle;
	
	@NotNull(message="Date should be mandatory")
	@NotBlank @NotEmpty
	private LocalDateTime date;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Customer> customers ;

	
	
	
}
