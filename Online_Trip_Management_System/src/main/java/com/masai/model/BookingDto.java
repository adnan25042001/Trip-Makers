package com.masai.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookingDto {
private Integer bookingId;
	private String bookingType;
	private String description;
	private String bookingTitle;
	private LocalDateTime date;
}
