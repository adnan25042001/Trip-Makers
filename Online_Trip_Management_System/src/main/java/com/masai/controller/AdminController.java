package com.masai.controller;

import java.util.List;

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

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerByIdHandler(@PathVariable("id") Integer id) {

		CustomerDto customer = aService.getCustomerById(id);

		return new ResponseEntity<CustomerDto>(customer, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<CustomerDto>> getAllCustomerHandler() {
		List<CustomerDto> customers = aService.getAllCustomerDetails();

		return new ResponseEntity<List<CustomerDto>>(customers, HttpStatus.OK);
	}

	@GetMapping("/usersbyname/{name}")
	public ResponseEntity<List<CustomerDto>> getAllCustomerByAddressHandler(@PathVariable("name") String name) {
		List<CustomerDto> customers = aService.getCustomerDetailsByName(name);

		return new ResponseEntity<List<CustomerDto>>(customers, HttpStatus.OK);
	}

	@GetMapping("/usersfeedbackbyfid/{feedbackId}")
	public ResponseEntity<Feedback> getFeedbackByFeedbackIdHandler(@PathVariable("feedbackId") Integer id) {
		Feedback feedback = aService.getFeedbackById(id);

		return new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
	}

	@GetMapping("/usersfeedback/{customerId}")
	public ResponseEntity<List<Feedback>> getFeedbackByCustomerIdHandler(@PathVariable("customerId") Integer cid) {
		List<Feedback> feedbacks = aService.getAllFeedbackByCustomerId(cid);

		return new ResponseEntity<List<Feedback>>(feedbacks, HttpStatus.OK);
	}

	@GetMapping("/usersfeedbacks")
	public ResponseEntity<List<Feedback>> getAllCustomerFeedbackHandler() {
		List<Feedback> allFeedbacks = aService.getAllFeedback();

		return new ResponseEntity<List<Feedback>>(allFeedbacks, HttpStatus.OK);
	}

}
