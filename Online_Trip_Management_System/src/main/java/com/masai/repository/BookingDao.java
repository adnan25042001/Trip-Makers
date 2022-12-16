package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Booking;

@Repository
public interface BookingDao extends JpaRepository<Booking ,Integer>{

}
