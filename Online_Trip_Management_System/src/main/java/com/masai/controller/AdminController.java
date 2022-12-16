package com.masai.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.CustomerDto;
import com.masai.model.Hotel;
import com.masai.model.HotelDto;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;
import com.masai.repository.CustomerDao;

import com.masai.repository.UserSessionDao;
import com.masai.service.HotelService;
import com.masai.service.ICustomerService;
import com.masai.service.LoginSignupService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired ICustomerService customerService;
	@Autowired HotelService hotelService;
	@Autowired UserSessionDao uSesDao;
	@Autowired CustomerDao cusDao;
	
	@Autowired private LoginSignupService lss;
	
	@PostMapping("/login")
	public ResponseEntity<SessionDTO> loginHandler(@Valid @RequestBody UserDTO user){
		
		SessionDTO sdt = lss.login(user);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.OK);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SessionDTO> signupHandler(@Valid @RequestBody Customer customer){
		
		SessionDTO sdt = lss.customerSignup(customer);
		
		return new ResponseEntity<SessionDTO>(sdt, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updatecustomer")
	public ResponseEntity<CustomerDto> updateUserHandler(@RequestBody CustomerDto cusDto, @RequestParam(required = false) String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<CustomerDto>(customerService.updateCustomer(cusDto, key),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecustomer")
	public ResponseEntity<String> deleteUserHandler(@RequestParam(required = false) String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<String>(customerService.deleteCustomer(key),HttpStatus.OK);
	}
	
	@GetMapping("/getcustomerbyid/{id}")
	public ResponseEntity<CustomerDto> deleteUserHandler(@PathVariable Integer id) {
		
		return new ResponseEntity<CustomerDto>(customerService.viewCustomerbyId(id),HttpStatus.OK);
	}
	@GetMapping("/getallcustomers")
	public ResponseEntity<List<CustomerDto>> viewAllCustomerHandler(){
		return new ResponseEntity<List<CustomerDto>> (customerService.viewallCustomer(),HttpStatus.OK);
	}
	
	@PutMapping("/addhotel")
	public ResponseEntity<Hotel> addHotelHandler(@RequestBody Hotel hotel, @RequestParam(required = false) String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<Hotel>(hotelService.addHotel(hotel, key),HttpStatus.OK);
	}
	
	@PutMapping("/updatehotel")
	public ResponseEntity<Hotel> updateHotelHandler(@RequestBody HotelDto hotel, @RequestParam(required = false) String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel, key),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletehotel/{id}")
	public ResponseEntity<String> deleteHotelHandler(@PathVariable Integer id, @RequestParam(required = false) String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<String>(hotelService.deleteHotel(id,key),HttpStatus.OK);
	}
	
	@GetMapping("/getallcustomers")
	public ResponseEntity<List<Hotel>> viewAllHotelHandler(){
		return new ResponseEntity<List<Hotel>> (hotelService.viewAllHotel(),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.model.Feedback;
import com.masai.service.AdminService;

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
	
	@GetMapping("/usersfeedbackbyfid/{feedbackId}")
	public ResponseEntity<Feedback> getFeedbackByFeedbackIdHandler(@PathVariable("feedbackId") Integer id){
		Feedback feedback =aService.getFeedbackById(id);
		
		return new ResponseEntity<Feedback>(feedback,HttpStatus.OK);
	}
	
	@GetMapping("/usersfeedback/{customerId}")
	public ResponseEntity<List<Feedback>> getFeedbackByCustomerIdHandler(@PathVariable("customerId") Integer cid){
		List<Feedback> feedbacks =aService.getAllFeedbackByCustomerId(cid);
		
		return new ResponseEntity<List<Feedback>>(feedbacks,HttpStatus.OK);
	}
	
	@GetMapping("/usersfeedbacks")
	public ResponseEntity<List<Feedback>> getAllCustomerFeedbackHandler(){
		List<Feedback> allFeedbacks= aService.getAllFeedback();
		
		return new ResponseEntity<List<Feedback>>(allFeedbacks,HttpStatus.OK);
	}

}
