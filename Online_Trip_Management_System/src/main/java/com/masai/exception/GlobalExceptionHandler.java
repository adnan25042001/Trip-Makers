package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDetails> MyInvalidDataExpHandler(MethodArgumentNotValidException ma, WebRequest wr) {

		ExceptionDetails me = new ExceptionDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(ma.getBindingResult().getFieldError().getDefaultMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(me, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> myAnyExpHandler(Exception e, WebRequest wr) {

		ExceptionDetails me = new ExceptionDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(e.getMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(me, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionDetails> myNoHandler(NoHandlerFoundException he, WebRequest wr) {

		ExceptionDetails me = new ExceptionDetails();
		me.setTimeStamp(LocalDateTime.now());
		me.setMessage(he.getMessage());
		me.setDetails(wr.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(me, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ExceptionDetails> customerExceptionhandler(CustomerException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ExceptionDetails> adminExceptionhandler(AdminException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(BookingException.class)
	public ResponseEntity<ExceptionDetails> bookingExceptionhandler(BookingException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(BusException.class)
	public ResponseEntity<ExceptionDetails> busExceptionhandler(BusException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(PackageException.class)
	public ResponseEntity<ExceptionDetails> PackageExceptionhandler(PackageException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<ExceptionDetails> feedbackExceptionhandler(FeedbackException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(HotelException.class)
	public ResponseEntity<ExceptionDetails> hotelExceptionhandler(HotelException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(RouteException.class)
	public ResponseEntity<ExceptionDetails> routeExceptionhandler(RouteException ex, WebRequest req) {

		ExceptionDetails err = new ExceptionDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDetails(req.getDescription(false));

		return new ResponseEntity<ExceptionDetails>(err, HttpStatus.BAD_GATEWAY);
	}

}
