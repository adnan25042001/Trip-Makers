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
	public ResponseEntity<ExceptionDetails> bookingExceptionhandler(BookingException ie, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(PackageException.class)
	public ResponseEntity<ExceptionDetails> PackageExceptionhandler(PackageException ie, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}
}
