package com.masai.service;

import java.util.List;

import com.masai.exception.FeedbackException;
import com.masai.exception.CustomerException;
import com.masai.model.CustomerDto;
import com.masai.model.Feedback;

public interface AdminService {

	//****
	public CustomerDto getCustomerByEmail(String email,String key)throws CustomerException;
	
	//*****
	public List<CustomerDto> getAllCustomerDetails(String key)throws CustomerException;
	
	//****
	public List<CustomerDto> getCustomerDetailsByAddress(String address,String key)throws CustomerException;
	
	//****
	public List<CustomerDto> getCustomerDetailsByName(String name,String key)throws CustomerException;
	
	//*****
	public List<Feedback> getAllFeedbackByCustomerId(Integer id,String key)throws CustomerException,FeedbackException;
	
	//****
	public Feedback getFeedbackById(Integer fid)throws FeedbackException;
	
	//*****
	public List<Feedback> getAllFeedback(String key)throws FeedbackException;
	
}
