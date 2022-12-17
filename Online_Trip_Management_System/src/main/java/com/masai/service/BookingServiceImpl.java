package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BookingException;
import com.masai.exception.CustomerException;
import com.masai.model.Booking;
import com.masai.model.BookingDto;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.UserType;
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
	public Booking addBooking(Booking booking, String key) throws BookingException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		return bDao.save(booking);

	}

	@Override
	public Booking DeleteBooking(Integer bookingId, String key) throws BookingException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		if (optCurrcustomer.get().getUserType().equals(UserType.ADMIN))
			throw new CustomerException("Invalid Authentication key : " + key);

		Booking booking = bDao.findById(bookingId).orElseThrow(() -> new BookingException("Invalid Booking ID"));

		bDao.delete(booking);

		return booking;

	}

	@Override
	public Booking viewBooking(Integer bookingId) throws BookingException {

		return bDao.findById(bookingId).orElseThrow(() -> new BookingException("BOokingId is not available"));

	}

	@Override
	public List<Booking> viewAllBookings() throws BookingException {

		List<Booking> allBookings = bDao.findAll();

		if (allBookings != null)
			return allBookings;

		else
			throw new BookingException("Not found");

	}

	@Override
	public BookingDto booking(Integer bookingId, String key) throws BookingException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		if (optCurrcustomer.get().getUserType().equals(UserType.ADMIN))
			throw new CustomerException("Invalid authKey : " + key);

		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail())
				.orElseThrow(() -> new CustomerException("Customer does not exist"));

		Booking b = bDao.findById(bookingId)
				.orElseThrow(() -> new BookingException("BooingId :- " + bookingId + "is not available"));

		customer.getBookings().add(b);
		b.getCustomers().add(customer);

		cdao.save(customer);
		bDao.save(b);

		BookingDto bdto = bDao.getBookingDto(bookingId);
		return bdto;

	}

	@Override
	public String cancelBooking(Integer bookingID, String key) throws BookingException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail())
				.orElseThrow(() -> new CustomerException("Customer does not exist"));

		Booking b = bDao.findById(bookingID)
				.orElseThrow(() -> new BookingException("BooingId :- " + bookingID + "is not available"));

		customer.getBookings().remove(b);
		b.getCustomers().remove(customer);

		cdao.save(customer);
		bDao.save(b);

		return "Canceled booking";

	}

}
