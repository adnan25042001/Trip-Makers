package com.masai.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.repository.FeedbackDao;
import com.masai.exception.CustomerException;
import com.masai.model.Feedback;
import com.masai.exception.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;

import com.masai.model.CustomerDto;
import com.masai.model.UserType;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

@Service
public class ICustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerDao cusDao;
	
	@Autowired
	private FeedbackDao fDao;

	@Autowired
	private UserSessionDao uSesDao;

	@Override
	public CustomerDto updateCustomer(CustomerDto cusDto, String key) {
		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isEmpty()) {
			throw new CustomerException("Invalid Authentication Id of Customer :" + key);
		}
		CurrentUserSession cnew = optCurrcustomer.get();
		
		if(cnew.getUserType().equals(UserType.ADMIN)) throw new CustomerException("Customer not found with email : " + cnew.getEmail());

		Optional<Customer> customer = cusDao.findByEmail(cnew.getEmail());
		if (customer.isEmpty()) {
			throw new CustomerException("Customer not found with email " + cnew.getEmail());
		}
		Customer cust = customer.get();
		
		
		
		if (!cusDto.getCustomerName().equals(null)) {
			cust.setName(cusDto.getCustomerName());

		}
		if (!cusDto.getAddress().equals(null)) {
			cust.setAddress(cusDto.getAddress());

		}
		if (!cusDto.getEmail().equals(null)) {
			cnew.setEmail(cusDto.getEmail());

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
			throw new CustomerException("Customer is not logged in");
		}

		CurrentUserSession curr = cnew.get();

		Optional<Customer> opt = cusDao.findByEmail(curr.getEmail());

		if (opt.isEmpty()) {
			throw new CustomerException("Customer not found with email : " + curr.getEmail());
		}

		cusDao.delete(opt.get());

		uSesDao.delete(curr);
		
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

	@Override
	public String giveFeedback(Feedback feedback) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> existedCustomer =cusDao.findById(feedback.getCustomerId());
		if(existedCustomer.isPresent()) {
			feedback.setDate(LocalDateTime.now());
			fDao.save(feedback);
//			System.out.println(feedback.getDate());
			return "Thank You for your feedback.";
		}
		throw new CustomerException("Please pass a valid userId");
	}
}
