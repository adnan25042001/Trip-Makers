package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserExcepotion;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;
import com.masai.model.UserType;
import com.masai.repository.AdminDao;
import com.masai.repository.CustomerDao;

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

	@Autowired
	private CustomerDao cdao;

	private AdminDao adao;

	@Override
	public SessionDTO login(UserDTO user) {

		if (user.getUserType().equals(UserType.ADMIN)) {

			Optional<Admin> opt = adao.findByEmail(user.getEmail());

			if (opt.isEmpty()) {
			}

			Admin admin = opt.get();

			if (admin.getPassword() != user.getPassword()) {
			}

			SessionDTO sdt = new SessionDTO();
			sdt.setAuthkey(UUID.randomUUID().hashCode());
			sdt.setSessionTime(LocalDateTime.now());
			return sdt;

		} else {

			Optional<Customer> opt = cdao.findByEmail(user.getEmail());

			if (opt.isEmpty()) {
			}

			Customer customer = opt.get();

			if (customer.getPassword() != user.getPassword()) {
			}

			SessionDTO sdt = new SessionDTO();
			sdt.setAuthkey(UUID.randomUUID().hashCode());
			sdt.setSessionTime(LocalDateTime.now());
			return sdt;

		}

	}

	@Override
	public SessionDTO signup(Customer customer) {

		Optional<Customer> opt = cdao.findByEmail(customer.getEmail());

		SessionDTO sdt = new SessionDTO();

		if (opt.isPresent()) {

			throw new UserExcepotion("account already exist with email : " + customer.getEmail());

		} else {

			sdt.setAuthkey(UUID.randomUUID().hashCode());
			sdt.setSessionTime(LocalDateTime.now());

			cdao.save(customer);

			return sdt;

		}

	}

}
