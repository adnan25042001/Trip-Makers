package com.masai.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.model.CustomerDto;
import com.masai.model.Hotel;
import com.masai.model.HotelDto;
import com.masai.model.SessionDto;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;
import com.masai.service.HotelService;
import com.masai.service.ICustomerService;
import com.masai.service.LoginSignupService;



@RestController
public class AdminCustomerServiceController {
	
	@Autowired ICustomerService customerService;
	@Autowired HotelService hotelService;
	@Autowired UserSessionDao uSesDao;
	@Autowired CustomerDao cusDao;
	
	@Autowired private LoginSignupService lss;
	
	
	@PutMapping("/updatecustomer/{key}")
	public ResponseEntity<CustomerDto> updateUserHandler(@RequestBody CustomerDto cusDto, @RequestParam String key) {
		
		return new ResponseEntity<CustomerDto>(customerService.updateCustomer(cusDto, key),HttpStatus.OK);
	}
	

	
	@GetMapping("/getcustomerbyemail/{email}/{key}")
	public ResponseEntity<CustomerDto> deleteUserHandler(@PathVariable String email, @PathVariable String key) {
		
		return new ResponseEntity<CustomerDto>(customerService.viewCustomerbyEmail(email, key),HttpStatus.OK);
	}
	@GetMapping("/getallcustomers/{key}")
	public ResponseEntity<List<CustomerDto>> viewAllCustomerHandler(String key){
		return new ResponseEntity<List<CustomerDto>> (customerService.viewallCustomer(key),HttpStatus.OK);
	}
	
	@PutMapping("/addhotel/{key}")
	public ResponseEntity<Hotel> addHotelHandler(@RequestBody Hotel hotel, @RequestParam String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<Hotel>(hotelService.addHotel(hotel, key),HttpStatus.OK);
	}
	
	@PutMapping("/updatehotel/{key}")
	public ResponseEntity<Hotel> updateHotelHandler(@RequestBody HotelDto hotel, @RequestParam String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel, key),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletehotel/{id}")
	public ResponseEntity<String> deleteHotelHandler(@PathVariable Integer id, @RequestParam String key) {
		lss.isLoggedInByUUID(key);
		return new ResponseEntity<String>(hotelService.deleteHotel(id,key),HttpStatus.OK);
	}
	
	@GetMapping("/getallhotels")
	public ResponseEntity<List<Hotel>> viewAllHotelHandler(){
		return new ResponseEntity<List<Hotel>> (hotelService.viewAllHotel(),HttpStatus.OK);
	}
	
	

}
