package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
import com.masai.model.CustomerDto;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByEmail(String email);

	public abstract List<Customer> findByAddress(String address);

	public abstract List<Customer> findByName(String name);

	@Query("select new com.masai.model.CustomerDto(c.name, c.email, c.address, c.mobile) from Customer c where c.email=?1")
	public Optional<CustomerDto> getCustomerDtoByEmail(String email);

	@Query("select new com.masai.model.CustomerDto(c.name, c.email, c.address, c.mobile) from Customer c")
	public List<CustomerDto> getAllCustomerDto();

	@Query("select new com.masai.model.CustomerDto(c.name, c.email, c.address, c.mobile) from Customer c where c.address=?1")
	public List<CustomerDto> getCustomerDtoByAddress(String address);

	@Query("select new com.masai.model.CustomerDto(c.name, c.email, c.address, c.mobile) from Customer c where c.name=?1")
	public List<CustomerDto> getCustomerDtoByName(String name);
	
	@Query("select new com.masai.model.CustomerDto(c.name, c.email, c.address, c.mobile) from Customer c where c.customerId=?1")
	public Optional<CustomerDto> getCustomerDto(Integer id);

}
