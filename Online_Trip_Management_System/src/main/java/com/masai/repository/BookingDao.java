package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.exception.BookingException;
import com.masai.model.Booking;
import com.masai.model.BookingDto;

@Repository
public interface BookingDao extends JpaRepository<Booking ,Integer>{

	@Query("select new com.masai.model.BookingDto(b.bookingId, b.bookingType, b.description, b.bookingTitle, b.date) from Booking b where b.bookingId=?1")
	public BookingDto getBookingDto(Integer id) throws BookingException;
	
}
