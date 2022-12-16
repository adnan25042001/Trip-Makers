package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;

@Service
public interface LoginSignupService {
	
	public SessionDTO customerSignup(Customer customer) throws CustomerException; 
	
	public SessionDTO adminSignup(Admin admin) throws AdminException;
	
	public SessionDTO loginAdmin(UserDTO user) throws AdminException;
	

	public boolean isLoggedInByUUID(String authKey);
	
	public boolean logout(String authKey);

	public SessionDTO loginCustomer(UserDTO user) throws CustomerException;
	
	public String logout(String authKey) throws CustomerException, AdminException;

	
}
