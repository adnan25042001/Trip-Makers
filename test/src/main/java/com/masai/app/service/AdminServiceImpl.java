package com.masai.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exception.UserException;
import com.masai.app.model.Customer;
import com.masai.app.repository.AdminDao;
import com.masai.app.repository.CustomerDao;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao aDao;
	
	@Autowired
	private CustomerDao cDao;

	@Override
	public Customer getCustomerById(Integer customerId) throws UserException {
		// TODO Auto-generated method stub
		Optional<Customer> opt= cDao.findById(customerId);
		if(opt.isPresent()) {
			Customer customer=opt.get();
			return customer;
		}
		throw new UserException("User not found with id :"+customerId);
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws UserException {
		// TODO Auto-generated method stub
		List<Customer> customers= cDao.findAll();
		if(customers.size()>0) {
			return customers;
		}
		throw new UserException("No user found!");
	}

	@Override
	public List<Customer> getCustomerDetailsByAddress(String address) throws UserException {
		// TODO Auto-generated method stub
		List<Customer> customers = cDao.findByAddress(address);
		if(customers.size()>0) {
			return customers;
		}
		throw new UserException("No user found in this address :"+address);
	}

	@Override
	public List<Customer> getCustomerDetailsByName(String name) throws UserException {
		// TODO Auto-generated method stub
		List<Customer> customers = cDao.findByName(name);
		if(customers.size()>0) {
			return customers;
		}
		throw new UserException("No user found with this name :"+name);
	}
	
	

	
}
