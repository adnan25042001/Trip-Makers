package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FeedbackException;
import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.model.CustomerDto;
import com.masai.model.Feedback;
import com.masai.repository.CustomerDao;
import com.masai.repository.FeedbackDao;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private FeedbackDao fDao;

	@Override
	public CustomerDto getCustomerById(Integer customerId) throws CustomerException {

		Optional<CustomerDto> opt = cDao.getCustomerDto(customerId);

		if (opt.isEmpty())
			throw new CustomerException("User not found with id :" + customerId);

		CustomerDto customer = opt.get();

		return customer;

	}

	@Override
	public List<CustomerDto> getAllCustomerDetails() throws CustomerException {

		List<CustomerDto> customers = cDao.getAllCustomerDto();

		if (customers.size() == 0)
			throw new CustomerException("No user found!");

		return customers;

	}

	@Override
	public List<CustomerDto> getCustomerDetailsByAddress(String address) throws CustomerException {

		List<CustomerDto> customers = cDao.getCustomerDtoByAddress(address);

		if (customers.size() == 0)
			throw new CustomerException("No user found in this address :" + address);

		return customers;

	}

	@Override
	public List<CustomerDto> getCustomerDetailsByName(String name) throws CustomerException {

		List<CustomerDto> customers = cDao.getCustomerDtoByName(name);

		if (customers.size() == 0)
			throw new CustomerException("No user found with this name :" + name);

		return customers;

	}

	@Override
	public List<Feedback> getAllFeedbackByCustomerId(Integer cid) throws CustomerException, FeedbackException {

		Optional<Customer> existedCustomer = cDao.findById(cid);

		if (existedCustomer.isEmpty())
			throw new CustomerException("No user found with this customerId :" + cid);

		List<Feedback> feedbacks = fDao.findByCustomerId(cid);

		if (feedbacks.size() == 0)
			throw new FeedbackException("No feedback given by this customerId :" + cid);

		return feedbacks;

	}

	@Override
	public Feedback getFeedbackById(Integer fid) throws FeedbackException {

		return fDao.findById(fid).orElseThrow(() -> new FeedbackException("Invalid feedback Id :" + fid));

	}

	@Override
	public List<Feedback> getAllFeedback() throws FeedbackException {

		List<Feedback> feedbacks = fDao.findAll();

		if (feedbacks.size() > 0)
			throw new FeedbackException("No feedback found.");

		return feedbacks;

	}

}
