package com.masai.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.masai.exception.BookingException;
import com.masai.exception.CustomerException;
import com.masai.exception.PackageException;
import com.masai.model.Booking;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Package;
import com.masai.repository.BookingDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	public BookingDao bDao;

	@Autowired
	public CustomerDao cdao;
	
	
	
	@Autowired
	private UserSessionDao uSesDao;
	
	@Override
	public Booking addBooking(Booking booking,String key) throws BookingException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		
		
		Booking newBooking = bDao.save(booking);
		if( newBooking != null) {
			return newBooking;
		}
 throw new BookingException("Failed to add booking");
	}

	@Override
	public Booking DeleteBooking(Integer bookingId,String key) throws BookingException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		 
		
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

	@Override
	public BookingDto booking(Integer bookingId, String key) throws BookingException {
	
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		
		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail()).orElseThrow(()-> new CustomerException("Customer does not exist") );
		
//		Package p= pdao.findById(packageId).orElseThrow(()-> new PackageException("PAckage does not exist with packageId:- "+packageId));
		Booking b = bDao.findById(bookingId).orElseThrow(()->new BookingException("BooingId :- "+bookingId +"is not available"));
		
		customer.getBookings().add(b);
		b.getCustomers().add(customer);
		
		BookingDto bdto = bDao.getBookingDto(bookingId);
		return bdto;
	}

	@Override
	public String cancelBooking(Integer bookingID, String key) throws BookingException {
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		
		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail()).orElseThrow(()-> new CustomerException("Customer does not exist") );
		
//		Package p= pdao.findById(packageId).orElseThrow(()-> new PackageException("PAckage does not exist with packageId:- "+packageId));
		Booking b = bDao.findById(bookingID).orElseThrow(()->new BookingException("BooingId :- "+bookingID +"is not available"));
		
		customer.getBookings().remove(b);
		
		
		
		return "Canceled booking";
	}

	
	
}
