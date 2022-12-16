package com.masai.app.service;

import java.util.List;

import com.masai.app.exception.UserException;
import com.masai.app.model.Customer;

public interface AdminService {

	public Customer getCustomerById(Integer customerId)throws UserException;
	
	public List<Customer> getAllCustomerDetails()throws UserException;
	
	public List<Customer> getCustomerDetailsByAddress(String address)throws UserException;
	
	public List<Customer> getCustomerDetailsByName(String name)throws UserException;
}
