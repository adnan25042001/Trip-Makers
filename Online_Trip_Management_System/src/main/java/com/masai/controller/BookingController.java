package com.masai.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.BookingException;
import com.masai.exception.PackageException;
import com.masai.model.Booking;
import com.masai.model.BookingDto;
import com.masai.model.PackageDto;
import com.masai.service.BookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/booking")

public class BookingController {
	
	@Autowired
	public BookingService bservice;
		
//  @PostMapping("/book/{key}")
//	public ResponseEntity<Booking> makebooking(@RequestBody Booking booking ){
//		
//	}
	@GetMapping("/viewBooking/{id}/{key}")
	public ResponseEntity<Booking> viewBooKing(@PathVariable("id") Integer bookingId, @PathVariable("key")String key ) throws BookingException{
		
	Booking b = bservice.viewBooking(bookingId, key);
		return new ResponseEntity<Booking>(b,HttpStatus.OK);
	}
	
	@GetMapping("/{key}")
	public ResponseEntity<List<Booking> > vieAllwBooKing( @PathVariable("key")String key ) throws BookingException{
		
	List<Booking> b = bservice.viewAllBookings(key);
		return new ResponseEntity<List<Booking> >(b,HttpStatus.OK);
	}
	
	@GetMapping("/book/{id}/{key}")
    public ResponseEntity<BookingDto> book(@PathVariable("id") Integer id , @PathVariable("key") String key) throws BookingException, PackageException{
	
    PackageDto b= bservice.bookPackage(id, key);
	return new ResponseEntity<BookingDto>(HttpStatus.OK);
	
    }
	
	@GetMapping("/book/{id2}/{key2}")
    public ResponseEntity<String> cancelBooking(@PathVariable("id2") Integer id , @PathVariable("key2") String key) throws BookingException, PackageException{
	
	String b= bservice.cancelPackage(id, key);
	return new ResponseEntity<String>(b,HttpStatus.OK);
	
    }
	
	
}
