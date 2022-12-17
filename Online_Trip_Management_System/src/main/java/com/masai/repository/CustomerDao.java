package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	public Optional<Customer> findByEmail(String email);
	
	public abstract List<Customer> findByAddress(String address);
	
	public abstract List<Customer> findByName(String name);

}
