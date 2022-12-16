package com.masai.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.masai.app.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	public abstract List<Customer> findByAddress(String address);
	
	public abstract List<Customer> findByName(String name);
}
