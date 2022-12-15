package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserExcepotion;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;
import com.masai.model.UserType;
import com.masai.repository.AdminDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private AdminDao adao;
	
	@Autowired
	private UserSessionDao usdao;

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
			sdt.setAuthkey(UUID.randomUUID().toString());
			sdt.setSessionTime(LocalDateTime.now());
			
			CurrentUserSession cus = new CurrentUserSession();
			cus.setAuthkey(sdt.getAuthkey());
			cus.setEmail(user.getEmail());
			cus.setSessionTime(sdt.getSessionTime());
			
			usdao.save(cus);
			
			return sdt;

		} else {

			Optional<Customer> opt = cdao.findByEmail(user.getEmail());

			if (opt.isEmpty()) {
			}

			Customer customer = opt.get();

			if (customer.getPassword() != user.getPassword()) {
			}

			SessionDTO sdt = new SessionDTO();
			sdt.setAuthkey(UUID.randomUUID().toString());
			sdt.setSessionTime(LocalDateTime.now());
			
			CurrentUserSession cus = new CurrentUserSession();
			cus.setAuthkey(sdt.getAuthkey());
			cus.setEmail(user.getEmail());
			cus.setSessionTime(sdt.getSessionTime());
			
			usdao.save(cus);
			
			return sdt;

		}

	}

	@Override
	public SessionDTO customerSignup(Customer customer) {

		Optional<Customer> opt = cdao.findByEmail(customer.getEmail());

		SessionDTO sdt = new SessionDTO();

		if (opt.isPresent()) {

			throw new UserExcepotion("account already exist with email : " + customer.getEmail());

		} else {

			sdt.setAuthkey(UUID.randomUUID().toString());
			sdt.setSessionTime(LocalDateTime.now());

			cdao.save(customer);
			
			CurrentUserSession cus = new CurrentUserSession();
			cus.setAuthkey(sdt.getAuthkey());
			cus.setEmail(customer.getEmail());
			cus.setSessionTime(sdt.getSessionTime());
			
			usdao.save(cus);

			return sdt;

		}

	}

	@Override
	public SessionDTO adminSignup(Admin admin) {
		
		Optional<Admin> opt = adao.findByEmail(admin.getEmail());

		SessionDTO sdt = new SessionDTO();

		if (opt.isPresent()) {

			throw new UserExcepotion("account already exist with email : " + admin.getEmail());

		} else {

			sdt.setAuthkey(UUID.randomUUID().toString());
			sdt.setSessionTime(LocalDateTime.now());

			adao.save(admin);
			
			CurrentUserSession cus = new CurrentUserSession();
			cus.setAuthkey(sdt.getAuthkey());
			cus.setEmail(admin.getEmail());
			cus.setSessionTime(sdt.getSessionTime());
			
			usdao.save(cus);

			return sdt;

		}
		
	}

	@Override
	public boolean logout(String authKey) {
		
		Optional<CurrentUserSession> opt = usdao.findByAuthKey(authKey);
		
		if(opt.isEmpty()) return false;
		
		CurrentUserSession cus = opt.get();
		
		usdao.delete(cus);
		
		return true;
		
	}

}
