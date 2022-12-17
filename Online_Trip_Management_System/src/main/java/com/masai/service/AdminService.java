package com.masai.service;

import java.util.List;

import com.masai.exception.FeedbackException;
import com.masai.exception.CustomerException;
import com.masai.model.CustomerDto;
import com.masai.model.Feedback;

public interface AdminService {

	public CustomerDto getCustomerById(Integer customerId)throws CustomerException;
	
	public List<CustomerDto> getAllCustomerDetails()throws CustomerException;
	
	public List<CustomerDto> getCustomerDetailsByAddress(String address)throws CustomerException;
	
	public List<CustomerDto> getCustomerDetailsByName(String name)throws CustomerException;
	
	public List<Feedback> getAllFeedbackByCustomerId(Integer cid)throws CustomerException,FeedbackException;
	
	public Feedback getFeedbackById(Integer fid)throws FeedbackException;
	
	public List<Feedback> getAllFeedback()throws FeedbackException;
	
}
