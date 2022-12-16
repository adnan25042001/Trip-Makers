package com.masai.service;

import java.util.List;

import com.masai.exception.FeedbackException;
import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.model.Feedback;

public interface AdminService {

	public Customer getCustomerById(Integer customerId)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;
	
	public List<Customer> getCustomerDetailsByAddress(String address)throws CustomerException;
	
	public List<Customer> getCustomerDetailsByName(String name)throws CustomerException;
	
	public List<Feedback> getAllFeedbackByCustomerId(Integer cid)throws CustomerException,FeedbackException;
	
	public Feedback getFeedbackById(Integer fid)throws FeedbackException;
	
	public List<Feedback> getAllFeedback()throws FeedbackException;
}
