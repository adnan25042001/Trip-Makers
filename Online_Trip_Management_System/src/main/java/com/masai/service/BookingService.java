package com.masai.service;

import java.util.List;

import com.masai.exception.BookingException;
import com.masai.exception.PackageException;
import com.masai.model.Booking;
import com.masai.model.BookingDto;
import com.masai.model.PackageDto;

public interface BookingService {
	
public PackageDto bookPackage(Integer packageId, String key)throws PackageException;
	
	public String cancelPackage(Integer packageId,String key)throws PackageException;

	public Booking viewBooking(Integer bookingId,String key) throws BookingException;

	public List<Booking> viewAllBookings(String key) throws BookingException;

	
	
}
