package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Feedback;
import com.masai.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService cService;

	//Rajibul
	@PostMapping("/usersfeedback/{key}")
	public ResponseEntity<String> postFeedbackController(@Valid @RequestBody Feedback feedback,@PathVariable("key") String key) {
		return new ResponseEntity<String>(cService.giveFeedback(feedback,key), HttpStatus.OK);

	}

}
