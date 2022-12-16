package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Feedback;
import com.masai.service.ICustomerService;

@RestController
public class CustomerController {

	@Autowired
	private ICustomerService cService;
	
	
	@PostMapping("/usersfeedback")
	public ResponseEntity<String> postFeedbackController(@RequestBody Feedback feedback) {
		return new ResponseEntity<String>(cService.giveFeedback(feedback),HttpStatus.OK);
		
	}
}
