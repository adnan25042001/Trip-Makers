package com.masai.service;

import java.util.List;

import com.masai.exception.BookingException;
import com.masai.model.Booking;
import com.masai.model.BookingDto;

public interface BookingService {

	public Booking addBooking(Booking booking, String key) throws BookingException;

	public Booking DeleteBooking(Integer bookingId, String key) throws BookingException;

	public Booking viewBooking(Integer bookingId) throws BookingException;

	public List<Booking> viewAllBookings() throws BookingException;

	public BookingDto booking(Integer bookingId, String key) throws BookingException;

	public String cancelBooking(Integer bookingID, String key) throws BookingException;
	
}
