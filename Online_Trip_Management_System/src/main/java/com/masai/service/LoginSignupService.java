package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;

@Service
public interface LoginSignupService {
	
	public SessionDTO customerSignup(Customer customer); 
	
	public SessionDTO adminSignup(Admin admin);
	
	public SessionDTO login(UserDTO user);
	
	public boolean logout(String authKey);
	
}
