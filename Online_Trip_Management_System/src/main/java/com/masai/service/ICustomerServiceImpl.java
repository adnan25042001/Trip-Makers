package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.repository.FeedbackDao;
import com.masai.exception.CustomerException;
import com.masai.model.Feedback;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;

import com.masai.model.CustomerDto;
import com.masai.model.UserType;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

@Service
public class ICustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerDao cusDao;

	@Autowired
	private FeedbackDao fDao;

	@Autowired
	private UserSessionDao uSesDao;
	
//	for customer only

	@Override
	public CustomerDto updateCustomer(CustomerDto cusDto, String key) {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		CurrentUserSession cnew = optCurrcustomer.get();
		

		if (cnew.getUserType().equals(UserType.ADMIN))
			throw new CustomerException("Customer not found with email : " + cnew.getEmail());

		Optional<Customer> customer = cusDao.findByEmail(cnew.getEmail());
		if (customer.isEmpty()) {
			throw new CustomerException("Customer not found with email " + cnew.getEmail());
		}
		Customer cust = customer.get();

		if (!cusDto.getCustomerName().equals(null)) {
			cust.setName(cusDto.getCustomerName());

		}
		if (!cusDto.getAddress().equals(null)) {
			cust.setAddress(cusDto.getAddress());

		}
		if (!cusDto.getEmail().equals(null)) {
			cnew.setEmail(cusDto.getEmail());

		}
		if (!cusDto.getMobile().equals(null)) {
			cust.setMobile(cusDto.getMobile());
		}
		

		Customer c=cusDao.save(cust);
		CustomerDto cDto = new CustomerDto();
		cDto.setAddress(c.getAddress());
		cDto.setCustomerName(c.getName());
		cDto.setEmail(c.getEmail());
		cDto.setMobile(c.getMobile());
		cnew.setEmail(c.getEmail());

//		System.out.println(cDto);
		return cDto;

	}
	


	
	
//	both for admin and customer

	@Override
	public CustomerDto viewCustomerbyEmail(String email,String key) {
		
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);

		if (optCurrcustomer.isEmpty())
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);

		CurrentUserSession cnew = optCurrcustomer.get();
             
		Optional<CustomerDto> opt = cusDao.getCustomerDtoByEmail(email);

		return opt.orElseThrow(() -> new CustomerException("Customer not found"));

	}
	
//	for admin only

	@Override
	public List<CustomerDto> viewallCustomer(String key) {
		

		Optional<CurrentUserSession> cnew = uSesDao.findByAuthKey(key);
		
		

		if (cnew.isEmpty())
			throw new CustomerException("Customer is not logged in");

		CurrentUserSession curr = cnew.get();
		
		if (curr.getUserType().equals(UserType.CUSTOMER))
			throw new CustomerException("Customer not found with email : " + curr.getEmail());

		List<CustomerDto> customers = cusDao.getAllCustomerDto();

		if (customers.size() == 0)
			throw new CustomerException("Customer not found");

		return customers;

	}

	
	//Rajibul
	@Override
	public String giveFeedback(Feedback feedback,String key) throws CustomerException {
		
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		
		if(optCurrcustomer.isPresent()) {
			
			CurrentUserSession cnew = optCurrcustomer.get();
			
			if (cnew.getUserType().equals(UserType.ADMIN))
				throw new CustomerException("Please log in as a User");

			Optional<Customer> existedCustomer = cusDao.findByEmail(cnew.getEmail());
			
			
			if (existedCustomer.isPresent()) {
				feedback.setDate(LocalDateTime.now());
				fDao.save(feedback);
				return "Thank You for your feedback.";
			}
		}

		
		
		throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		
	}
}
