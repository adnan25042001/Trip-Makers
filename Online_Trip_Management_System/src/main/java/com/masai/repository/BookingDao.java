package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.exception.BookingException;
import com.masai.exception.PackageException;
import com.masai.model.Booking;
import com.masai.model.PackageDto;
import com.masai.service.BookingDto;

@Repository
public interface BookingDao extends JpaRepository<Booking ,Integer>{

	@Query("select new com.masai.model.Booking(b.bookingID,b.bookingType,b.description,b.bookingTitle,b.date) from Booking b where b.BookingId=?1")
	public BookingDto getBookingDto(Integer id)throws BookingException;
}
