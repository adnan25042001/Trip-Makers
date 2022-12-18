package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.CustomerDto;
import com.masai.model.Hotel;
import com.masai.model.HotelDto;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;
import com.masai.service.HotelService;
import com.masai.service.ICustomerService;

@RestController
public class AdminCustomerServiceController {

	@Autowired
	ICustomerService customerService;
	@Autowired
	HotelService hotelService;
	@Autowired
	UserSessionDao uSesDao;
	@Autowired
	CustomerDao cusDao;

	@PutMapping("/updatecustomer/{authKey}")
	public ResponseEntity<CustomerDto> updateUserHandler(@Valid @RequestBody CustomerDto cusDto,
			@PathVariable("authKey") String key) {

		return new ResponseEntity<CustomerDto>(customerService.updateCustomer(cusDto, key), HttpStatus.OK);
	}

	@GetMapping("/getcustomerbyemail/{authKey}/{email}")
	public ResponseEntity<CustomerDto> deleteUserHandler(@PathVariable("email") String email,
			@PathVariable("authKey") String key) {

		return new ResponseEntity<CustomerDto>(customerService.viewCustomerbyEmail(email, key), HttpStatus.OK);
	}

	@GetMapping("/getallcustomers/{authKey}")
	public ResponseEntity<List<CustomerDto>> viewAllCustomerHandler(@PathVariable("authKey") String key) {
		return new ResponseEntity<List<CustomerDto>>(customerService.viewallCustomer(key), HttpStatus.OK);
	}

	@PutMapping("/addhotel/{authKey}")
	public ResponseEntity<Hotel> addHotelHandler(@Valid @RequestBody Hotel hotel, @RequestParam("authKey") String key) {

		return new ResponseEntity<Hotel>(hotelService.addHotel(hotel, key), HttpStatus.OK);
	}

	@PutMapping("/updatehotel/{authKey}")
	public ResponseEntity<Hotel> updateHotelHandler(@Valid @RequestBody HotelDto hotel,
			@RequestParam("authKey") String key) {

		return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel, key), HttpStatus.OK);
	}

	@DeleteMapping("/deletehotel/{authKey}?id")
	public ResponseEntity<String> deleteHotelHandler(@RequestParam("id") Integer id,
			@PathVariable("authKey") String key) {

		return new ResponseEntity<String>(hotelService.deleteHotel(id, key), HttpStatus.OK);
	}

	@GetMapping("/getallhotels")
	public ResponseEntity<List<Hotel>> viewAllHotelHandler() {
		return new ResponseEntity<List<Hotel>>(hotelService.viewAllHotel(), HttpStatus.OK);
	}

}
