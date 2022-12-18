package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.CustomerDto;
import com.masai.model.Feedback;
import com.masai.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService aService;

	@GetMapping("/{email}/{key}")
	public ResponseEntity<CustomerDto> getCustomerByEmailHandler(@Valid @PathVariable("email") String email,@PathVariable("key") String key) {

		CustomerDto customer = aService.getCustomerByEmail(email,key);

		return new ResponseEntity<CustomerDto>(customer, HttpStatus.OK);
	}

	@GetMapping("/{key}")
	public ResponseEntity<List<CustomerDto>> getAllCustomerHandler(@Valid @PathVariable("key") String key) {
		List<CustomerDto> customers = aService.getAllCustomerDetails(key);

		return new ResponseEntity<List<CustomerDto>>(customers, HttpStatus.OK);
	}

	@GetMapping("/usersbyaddress/{address}/{key}")
	public ResponseEntity<List<CustomerDto>> getAllCustomerByAddressHandler(@Valid @PathVariable("address") String address,@PathVariable("key") String key) {
		List<CustomerDto> customers = aService.getCustomerDetailsByName(address,key);

		return new ResponseEntity<List<CustomerDto>>(customers, HttpStatus.OK);
	}

	@GetMapping("/usersfeedbackbyfid/{feedbackId}")
	public ResponseEntity<Feedback> getFeedbackByFeedbackIdHandler(@Valid @PathVariable("feedbackId") Integer id) {
		Feedback feedback = aService.getFeedbackById(id);

		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
	}

	@GetMapping("/usersfeedback/{customerId}/{key}")
	public ResponseEntity<List<Feedback>> getFeedbackByCustomerIdHandler(@Valid @PathVariable("customerId") Integer cid,@PathVariable("key") String key ) {
		List<Feedback> feedbacks = aService.getAllFeedbackByCustomerId(cid,key);

		return new ResponseEntity<List<Feedback>>(feedbacks, HttpStatus.OK);
	}

	@GetMapping("/allusersfeedbacks/{key}")
	public ResponseEntity<List<Feedback>> getAllCustomerFeedbackHandler(@PathVariable("key") String key) {
		List<Feedback> allFeedbacks = aService.getAllFeedback(key);

		return new ResponseEntity<List<Feedback>>(allFeedbacks, HttpStatus.OK);
	}

	@GetMapping("/usersbyname/{name}/{key}")
	public ResponseEntity<List<CustomerDto>> getAllCustomerByNameHandler(@Valid @PathVariable("name") String name,@PathVariable("key") String key) {
		List<CustomerDto> customers = aService.getCustomerDetailsByName(name, key);

		return new ResponseEntity<List<CustomerDto>>(customers, HttpStatus.OK);
	}
}
