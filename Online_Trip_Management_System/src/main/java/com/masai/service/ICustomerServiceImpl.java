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

		cusDao.save(cust);
		CustomerDto cDto = new CustomerDto();
		cDto.setAddress(cust.getAddress());
		cDto.setCustomerName(cust.getName());
		cDto.setEmail(cust.getEmail());
		cDto.setMobile(cust.getMobile());

		return cDto;

	}

	@Override
	public String deleteCustomer(String key) {

		Optional<CurrentUserSession> cnew = uSesDao.findByAuthKey(key);

		if (cnew.isEmpty())
			throw new CustomerException("Customer is not logged in");

		CurrentUserSession curr = cnew.get();

		Optional<Customer> opt = cusDao.findByEmail(curr.getEmail());

		if (opt.isEmpty())
			throw new CustomerException("Customer not found with email : " + curr.getEmail());

		cusDao.delete(opt.get());

		uSesDao.delete(curr);

		return "Customer Deleted Successfully";

	}

	@Override
	public CustomerDto viewCustomerbyId(Integer id) {

		Optional<CustomerDto> opt = cusDao.getCustomerDto(id);

		return opt.orElseThrow(() -> new CustomerException("Customer not found"));

	}

	@Override
	public List<CustomerDto> viewallCustomer() {

		List<CustomerDto> customers = cusDao.getAllCustomerDto();

		if (customers.size() == 0)
			throw new CustomerException("Customer not found");

		return customers;

	}

	@Override
	public String giveFeedback(Feedback feedback) throws CustomerException {

		Optional<Customer> existedCustomer = cusDao.findById(feedback.getCustomerId());
		
		if (existedCustomer.isPresent()) {
			feedback.setDate(LocalDateTime.now());
			fDao.save(feedback);
			return "Thank You for your feedback.";
		}
		
		throw new CustomerException("Please pass a valid userId");
		
	}
}
