package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FeedbackException;
import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.model.Feedback;
import com.masai.repository.AdminDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.FeedbackDao;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao aDao;
	
	@Autowired
	private CustomerDao cDao;
	
	@Autowired
	private FeedbackDao fDao;

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> opt= cDao.findById(customerId);
		if(opt.isPresent()) {
			Customer customer=opt.get();
			return customer;
		}
		throw new CustomerException("User not found with id :"+customerId);
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws CustomerException {
		// TODO Auto-generated method stub
		List<Customer> customers= cDao.findAll();
		if(customers.size()>0) {
			return customers;
		}
		throw new CustomerException("No user found!");
	}

	@Override
	public List<Customer> getCustomerDetailsByAddress(String address) throws CustomerException {
		// TODO Auto-generated method stub
		List<Customer> customers = cDao.findByAddress(address);
		if(customers.size()>0) {
			return customers;
		}
		throw new CustomerException("No user found in this address :"+address);
	}

	@Override
	public List<Customer> getCustomerDetailsByName(String name) throws CustomerException {
		// TODO Auto-generated method stub
		List<Customer> customers = cDao.findByName(name);
		if(customers.size()>0) {
			return customers;
		}
		throw new CustomerException("No user found with this name :"+name);
	}

	@Override
	public List<Feedback> getAllFeedbackByCustomerId(Integer cid) throws CustomerException,FeedbackException{
		// TODO Auto-generated method stub
		Optional<Customer> existedCustomer =cDao.findById(cid);
		if(existedCustomer.isPresent()) {
			List<Feedback> feedbacks =fDao.findByCustomerId(cid);
			if(feedbacks.size()>0) {
				return feedbacks;
			}
			else {
				throw new FeedbackException("No feedback given by this customerId :"+cid);
			}
		}
		throw new CustomerException("No user found with this customerId :"+cid);
	}

	@Override
	public Feedback getFeedbackById(Integer fid) throws FeedbackException {
		// TODO Auto-generated method stub
		Optional<Feedback> feedback=fDao.findById(fid);
		if(feedback.isPresent()) {
			Feedback feedback2=feedback.get();
			return feedback2;
		}
		throw new FeedbackException("Invalid feedback Id :"+fid);
	}

	@Override
	public List<Feedback> getAllFeedback() throws FeedbackException{
		
		List<Feedback> feedbacks=fDao.findAll();
		if(feedbacks.size()>0) {
			return feedbacks;
		}
		throw new FeedbackException("No feedback found.");
		
	}
	
	

	
}
