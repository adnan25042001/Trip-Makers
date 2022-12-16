package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.model.Admin;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.SessionDTO;
import com.masai.model.UserDTO;
import com.masai.model.UserType;
import com.masai.repository.AdminDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

	@Autowired
	private CustomerDao cdao;

	@Autowired
	private AdminDao adao;

	@Autowired
	private UserSessionDao usdao;

	@Override
	public SessionDTO loginAdmin(UserDTO user) {

		Optional<Admin> opt = adao.findByEmail(user.getEmail());

		if (opt.isEmpty())
			throw new AdminException("Admin does not exist with email : " + user.getEmail());

		Admin admin = opt.get();

		if (admin.getPassword() != user.getPassword())
			throw new AdminException("Wrong password!");

		Optional<CurrentUserSession> opt1 = usdao.findByEmail(user.getEmail());

		if (opt1.isPresent())
			throw new AdminException("Admin already logged in!");

		if (opt1.get().getUserType().equals(UserType.CUSTOMER))
			throw new AdminException("Wrong email and password!");

		SessionDTO sdt = new SessionDTO();
		sdt.setAuthkey(RandomString.make(6));
		sdt.setSessionTime(LocalDateTime.now());

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(user.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.ADMIN);

		usdao.save(cus);

		return sdt;

	}

	@Override
	public SessionDTO loginCustomer(UserDTO user) {

		Optional<Customer> opt = cdao.findByEmail(user.getEmail());

		if (opt.isEmpty())
			throw new CustomerException("Email not found : " + user.getEmail());

		Customer customer = opt.get();

		if (customer.getPassword() != user.getPassword())
			throw new CustomerException("Wrong password!");

		Optional<CurrentUserSession> opt1 = usdao.findByEmail(user.getEmail());

		if (opt1.isPresent())
			throw new AdminException("Customer already logged in!");

		if (opt1.get().getUserType().equals(UserType.ADMIN))
			throw new AdminException("Wrong email and password!");

		SessionDTO sdt = new SessionDTO();
		sdt.setAuthkey(RandomString.make(6));
		sdt.setSessionTime(LocalDateTime.now());

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(user.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.CUSTOMER);

		usdao.save(cus);

		return sdt;

	}

	@Override
	public SessionDTO customerSignup(Customer customer) {

		Optional<Customer> opt = cdao.findByEmail(customer.getEmail());

		Optional<Admin> opt1 = adao.findByEmail(customer.getEmail());

		if (opt1.isPresent())
			throw new CustomerException("account already exist with email : " + customer.getEmail());

		SessionDTO sdt = new SessionDTO();

		if (opt.isPresent())
			throw new CustomerException("account already exist with email : " + customer.getEmail());

		sdt.setAuthkey(RandomString.make(6));
		sdt.setSessionTime(LocalDateTime.now());

		cdao.save(customer);

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(customer.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.CUSTOMER);

		usdao.save(cus);

		return sdt;

	}

	@Override
	public SessionDTO adminSignup(Admin admin) {

		Optional<Admin> opt = adao.findByEmail(admin.getEmail());

		Optional<Customer> opt1 = cdao.findByEmail(admin.getEmail());

		if (opt1.isPresent())
			throw new AdminException("account already exist with email : " + admin.getEmail());

		SessionDTO sdt = new SessionDTO();

		if (opt.isPresent())
			throw new AdminException("account already exist with email : " + admin.getEmail());

		sdt.setAuthkey(RandomString.make(6));
		sdt.setSessionTime(LocalDateTime.now());

		adao.save(admin);

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(admin.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.ADMIN);

		usdao.save(cus);

		return sdt;

	}

	@Override
	public String logout(String authKey) {

		Optional<CurrentUserSession> opt = usdao.findByAuthKey(authKey);

		if (opt.isEmpty()) throw new RuntimeException("User alresdy logged out!");

		CurrentUserSession cus = opt.get();

		usdao.delete(cus);

		return "Logged out...";

	}

}
