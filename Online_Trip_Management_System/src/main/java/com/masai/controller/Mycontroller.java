package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;
import com.masai.service.ICustomerService;
import com.masai.service.LoginSignupService;

@RestController
public class Mycontroller {
	
	@Autowired
	private LoginSignupService lss;
	
	@Autowired
	private ICustomerService ics;
	
	@PostMapping("/login/customer")
	public ResponseEntity<SessionDTO> customerLoginHandler(@Valid @RequestBody UserDTO user){
		
		SessionDTO sdt = lss.loginCustomer(user);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.OK);
		
	}
	
	@PostMapping("/login/admin")
	public ResponseEntity<SessionDTO> adminLoginHandler(@Valid @RequestBody UserDTO user){
		
		SessionDTO sdt = lss.loginAdmin(user);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.OK);
		
	}
	
	@PostMapping("/signup/customer")
	public ResponseEntity<SessionDTO> signupHandler(@Valid @RequestBody Customer customer){
		
		SessionDTO sdt = lss.customerSignup(customer);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/signup/admin")
	public ResponseEntity<SessionDTO> signupHandler(@Valid @RequestBody Admin admin){
		
		SessionDTO sdt = lss.adminSignup(admin);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/logout/{key}")
	public ResponseEntity<String> logout(@PathVariable("key") String authKey){
		
		String msg = lss.logout(authKey);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/closeaccount/{key}")
	public ResponseEntity<String> deleteCustomerAccount(@PathVariable("key") String authKey){
		
		String msg = ics.deleteCustomer(authKey);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
		
	}

}
