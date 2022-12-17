package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Hotel;

@Repository
public interface HotelDao extends JpaRepository<Hotel, Integer> {
	
	public Optional<Hotel> findByHotelname(String name);

}
