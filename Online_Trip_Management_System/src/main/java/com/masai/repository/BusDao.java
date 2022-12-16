package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Bus;

public interface BusDao extends JpaRepository<Bus, Integer>{
	
	public Optional<Bus> findByBusNo(String busNo);

}
