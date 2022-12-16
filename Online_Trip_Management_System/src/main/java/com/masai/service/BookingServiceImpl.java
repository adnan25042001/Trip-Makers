package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BookingException;
import com.masai.model.Booking;
import com.masai.repository.BookingDao;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	public BookingDao bDao;

	@Override
	public Booking addBooking(Booking booking) throws BookingException {
		
		Booking newBooking = bDao.save(booking);
		if( newBooking != null) {
			return newBooking;
		}
 throw new BookingException("Failed to add booking");
	}

	@Override
	public Booking DeleteBooking(Integer bookingId) throws BookingException {
		
		Optional<Booking> bookingOptional = bDao.findById(bookingId);
		if (bookingOptional.isPresent()) {
			Booking booking = bookingOptional.get();
			bDao.deleteById(bookingId);

			return booking;
		} else {
			throw new BookingException("Invalid Booking ID");
		}
	}

	@Override
	public Booking viewBooking(Integer bookingId) throws BookingException {
	Optional<Booking > view = bDao.findById(bookingId);
	if(view == null) {
		throw new BookingException("BOokingId is not available");
	}else {
		Booking viewBooking = view.get();
		return viewBooking;
	}

	}

	@Override
	public List<Booking> viewAllBookings() throws BookingException {
		
		List<Booking> allBookings = bDao.findAll();
		
		if(allBookings!= null)return allBookings;
		
		else throw new BookingException("Not found"); 
		
		}

	
}
