package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookingException.class)
	public ResponseEntity<MyErrDetails> bookingExceptionhandler(BookingException ie, WebRequest req){
		
		MyErrDetails err = new MyErrDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessege(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	
	
	@ExceptionHandler(PackageException.class)
	public ResponseEntity<MyErrDetails> PackageExceptionhandler(PackageException ie, WebRequest req){
		
		MyErrDetails err = new MyErrDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessege(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrDetails>(err,HttpStatus.BAD_GATEWAY);
	}
}
