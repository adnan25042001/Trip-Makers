package com.masai.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

	private Integer bookingId;

	private String bookingType;

	private String description;

	private String bookingTitle;

	private LocalDateTime date;

}
