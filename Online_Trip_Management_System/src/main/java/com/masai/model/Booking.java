package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



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

	public Booking(Integer bookingId,
			@NotNull @NotBlank @NotEmpty @Size(min = 5, max = 15, message = "Type should be of 5-7 charecters") String bookingType,
			@NotNull @NotBlank @NotEmpty @Size(min = 5, max = 50, message = "Description should be of 5-50 charecters") String description,
			@NotNull @NotBlank @NotEmpty String bookingTitle, @NotNull @NotBlank @NotEmpty LocalDateTime date) {
		super();
		this.bookingId = bookingId;
		this.bookingType = bookingType;
		this.description = description;
		this.bookingTitle = bookingTitle;
		this.date = date;
	}
	
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}


	public Integer getBookingId() {
		return bookingId;
	}


	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}


	public String getBookingType() {
		return bookingType;
	}


	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBookingTitle() {
		return bookingTitle;
	}


	public void setBookingTitle(String bookingTitle) {
		this.bookingTitle = bookingTitle;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingType=" + bookingType + ", description=" + description
				+ ", bookingTitle=" + bookingTitle + ", date=" + date + "]";
	}
	
	
	
}
