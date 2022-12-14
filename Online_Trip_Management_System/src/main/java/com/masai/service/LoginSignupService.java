package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;

@Service
public interface LoginSignupService {
	
	public SessionDTO signup(Customer customer); 
	
	public SessionDTO login(UserDTO user);
	
}
