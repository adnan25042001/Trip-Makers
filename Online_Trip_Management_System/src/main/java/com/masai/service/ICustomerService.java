package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.Feedback;
import com.masai.model.CustomerDto;

public interface ICustomerService {

	public CustomerDto updateCustomer(CustomerDto cusDto, String key);

	public CustomerDto viewCustomerbyEmail(String email, String key);

	public List<CustomerDto> viewallCustomer(String key);

	public String giveFeedback(Feedback feedback, String key) throws CustomerException;

}
