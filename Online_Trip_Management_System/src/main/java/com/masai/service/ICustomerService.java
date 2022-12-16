package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.Feedback;
import com.masai.model.Customer;
import com.masai.model.CustomerDto;
import com.masai.model.UserDTO;

public interface ICustomerService {
	
	public CustomerDto updateCustomer(CustomerDto cusDto, String key);
	public String deleteCustomer(String key);
	public CustomerDto viewCustomerbyId(Integer id);
	public List<CustomerDto> viewallCustomer();
	
	public String giveFeedback(Feedback feedback)throws CustomerException; 

}
