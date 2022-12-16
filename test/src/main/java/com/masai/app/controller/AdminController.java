package com.masai.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.model.Customer;
import com.masai.app.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService aService;
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable("id") Integer id){
		Customer customer= aService.getCustomerById(id);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Customer>> getAllCustomerHandler(){
		List<Customer> customers= aService.getAllCustomerDetails();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@GetMapping("/usersname/{name}")
	public ResponseEntity<List<Customer>> getAllCustomerByAddressHandler(@PathVariable("name") String name){
		List<Customer> customers= aService.getCustomerDetailsByName(name);
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
}
