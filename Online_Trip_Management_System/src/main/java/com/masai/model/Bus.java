package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Bus {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	private String busType;

	@NotNull(message = "Bus number should not be null")
	@NotEmpty(message = "Buss number should not be null")
	private String busNo;
	private Integer capacity;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(referencedColumnName = "routeId")
//	private Route route;

}
