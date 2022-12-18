package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BookingException;
import com.masai.exception.CustomerException;
import com.masai.exception.PackageException;
import com.masai.model.Booking;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Package;
import com.masai.model.PackageDto;
import com.masai.repository.BookingDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.PackageDao;
import com.masai.repository.UserSessionDao;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	public BookingDao bDao;

	@Autowired
	public CustomerDao cdao;

	@Autowired
	private UserSessionDao uSesDao;
	@Autowired
	private PackageDao pdao;

	@Override
	public PackageDto bookPackage(Integer packageId, String key) throws PackageException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail())
				.orElseThrow(() -> new CustomerException("Customer does not exist"));

		Package p = pdao.findById(packageId)
				.orElseThrow(() -> new PackageException("Package does not exist with packageId:- " + packageId));

		customer.getPackages().add(p);
		p.getCustomer().add(customer);

		cdao.save(customer);
		pdao.save(p);

		PackageDto packagedto = pdao.getPackageDto(packageId);
		return packagedto;

	}

	@Override
	public String cancelPackage(Integer packageId, String key) throws PackageException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		Customer customer = cdao.findByEmail(optCurrcustomer.get().getEmail())
				.orElseThrow(() -> new CustomerException("Customer does not exist"));

		Package p = pdao.findById(packageId)
				.orElseThrow(() -> new PackageException("Package does not exist with packageId:- " + packageId));

		customer.getPackages().remove(p);

		cdao.save(customer);

		return "Package Removed Successfully";

	}

	@Override
	public Booking viewBooking(Integer bookingId, String key) throws BookingException {

		return bDao.findById(bookingId).orElseThrow(() -> new BookingException("BOokingId is not available"));

	}

	@Override
	public List<Booking> viewAllBookings(String key) throws BookingException {

		List<Booking> allBookings = bDao.findAll();

		if (allBookings != null)
			return allBookings;

		else
			throw new BookingException("Not found");

	}

}
