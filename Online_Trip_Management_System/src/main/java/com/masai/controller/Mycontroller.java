package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;
import com.masai.service.LoginSignupService;

@RestController
public class Mycontroller {
	
	@Autowired
	private LoginSignupService lss;
	
	@PostMapping("/login")
	public ResponseEntity<SessionDTO> loginHandler(@Valid @RequestBody UserDTO user){
		
		SessionDTO sdt = lss.login(user);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.OK);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SessionDTO> signupHandler(@RequestBody Customer customer){
		
		SessionDTO sdt = lss.signup(customer);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.CREATED);
		
	}

}
