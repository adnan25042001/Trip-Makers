package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;

import com.masai.model.CustomerDto;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

@Service
public class ICustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerDao cusDao;

	@Autowired
	private UserSessionDao uSesDao;

	@Override
	public CustomerDto updateCustomer(CustomerDto cusDto, String key) {
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		CurrentUserSession cnew = new CurrentUserSession();

		Optional<Customer> customer = cusDao.findByEmail(cnew.getEmail());
		if (customer.isEmpty()) {
			throw new CustomerException("Customer not found with Id");
		}
		Customer cust = customer.get();
		if (!cusDto.getCustomerName().equals(null)) {
			cust.setName(cusDto.getCustomerName());

		}
		if (!cusDto.getAddress().equals(null)) {
			cust.setAddress(cusDto.getAddress());

		}
		if (!cusDto.getEmail().equals(null)) {

			List<CurrentUserSession> lCus = uSesDao.findByEmail(cnew.getEmail());
			for (int i = 0; i < lCus.size(); i++) {
				lCus.get(i).setEmail(cusDto.getEmail());
				uSesDao.save(lCus.get(i));
			}

			cust.setEmail(cusDto.getEmail());

		}
		if (!cusDto.getMobile().equals(null)) {
			cust.setMobile(cusDto.getMobile());
		}

		cusDao.save(cust);
		CustomerDto cDto = new CustomerDto();
		cDto.setAddress(cust.getAddress());
		cDto.setCustomerName(cust.getName());
		cDto.setEmail(cust.getEmail());
		cDto.setMobile(cust.getMobile());

		return cDto;

	}

	@Override
	public String deleteCustomer(String key) {

		Optional<CurrentUserSession> cnew = uSesDao.findByAuthKey(key);
		if (cnew.isEmpty()) {
			throw new CustomerException("Customer is not login with this details :" + key);
		}

		CurrentUserSession curr = new CurrentUserSession();

		List<CurrentUserSession> lCus = uSesDao.findByEmail(curr.getEmail());

		Optional<Customer> opt = cusDao.findByEmail(curr.getEmail());

		if (opt.isEmpty()) {
			throw new CustomerException("Customer is not login with this email");
		}

		cusDao.delete(opt.get());

		for (int i = 0; i < lCus.size(); i++) {

			uSesDao.delete(lCus.get(i));
		}
		return "Customer Deleted Successfully";

	}

	@Override
	public CustomerDto viewCustomerbyId(Integer id) {
		
		Optional<Customer> opt=cusDao.findById(id);
		if(opt.isEmpty()) {
			throw new CustomerException("Customer is not login with this id :"+id);
		}
		CustomerDto cDto=new CustomerDto();
		Customer customer=opt.get();
		cDto.setAddress(customer.getAddress());
		cDto.setCustomerName(customer.getName());
		cDto.setEmail(customer.getEmail());
		cDto.setMobile(customer.getMobile());
		
		return cDto;

	}

	@Override
	public List<CustomerDto> viewallCustomer() {
		
		List<Customer> customer=cusDao.findAll();
		
		if(customer.size()==0) {
			throw new CustomerException("Customer not found");
		}
		List<CustomerDto> cDto=new ArrayList<>();
		for(Customer c:customer) {
			CustomerDto curr=new CustomerDto();
			curr.setAddress(c.getAddress());
			curr.setEmail(c.getEmail());
			curr.setCustomerName(c.getName());
			curr.setMobile(c.getMobile());
			cDto.add(curr);
		}
		return cDto;

	}

}
