package com.masai.service;

import java.util.List;

import com.masai.exception.BookingException;
import com.masai.model.Booking;

public interface BookingService {
	
	public Booking addBooking (Booking booking ) throws BookingException;
	public Booking DeleteBooking (Integer bookingId) throws BookingException;
	public Booking viewBooking (Integer bookingId) throws BookingException;
	public List<Booking> viewAllBookings( ) throws BookingException;

}
