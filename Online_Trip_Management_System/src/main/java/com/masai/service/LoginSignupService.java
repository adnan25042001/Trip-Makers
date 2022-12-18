package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.model.AdminSignupDto;
import com.masai.model.CustomerSignupDto;
import com.masai.model.SessionDto;
import com.masai.model.UserDto;

@Service
public interface LoginSignupService {

	public SessionDto customerSignup(CustomerSignupDto customer) throws CustomerException;

	public SessionDto adminSignup(AdminSignupDto admin) throws AdminException;

	public SessionDto loginAdmin(UserDto user) throws AdminException;

	public SessionDto loginCustomer(UserDto user) throws CustomerException;

	public String logout(String authKey) throws CustomerException, AdminException;

}
