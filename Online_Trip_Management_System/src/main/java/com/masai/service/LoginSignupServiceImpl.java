package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.model.Admin;
import com.masai.model.AdminSignupDto;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.CustomerSignupDto;
import com.masai.model.SessionDto;
import com.masai.model.UserDto;
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
	public SessionDto loginAdmin(UserDto user) {

		Optional<Admin> opt = adao.findByEmail(user.getEmail());

		if (opt.isEmpty())
			throw new AdminException("Admin does not exist with email : " + user.getEmail());

		Admin admin = opt.get();

		if (!admin.getPassword().equals(user.getPassword()))
			throw new AdminException("Wrong password!");

		Optional<CurrentUserSession> opt1 = usdao.findByEmail(user.getEmail());

		if (opt1.isPresent())
			throw new AdminException("Admin already logged in!");

		SessionDto sdt = new SessionDto();
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
	public SessionDto loginCustomer(UserDto user) {

		System.out.println(user);

		Customer customer = cdao.findByEmail(user.getEmail())
				.orElseThrow(() -> new CustomerException("Email not found : " + user.getEmail()));

		if (!customer.getPassword().equals(user.getPassword()))
			throw new CustomerException("Wrong password!");

		Optional<CurrentUserSession> opt1 = usdao.findByEmail(user.getEmail());

		if (opt1.isPresent()) {

			throw new CustomerException("Customer already logged in!");

		}

		SessionDto sdt = new SessionDto();
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
	public SessionDto customerSignup(CustomerSignupDto customer) {

		Optional<Customer> opt = cdao.findByEmail(customer.getEmail());

		Optional<Admin> opt1 = adao.findByEmail(customer.getEmail());

		if (opt1.isPresent())
			throw new CustomerException("account already exist with email : " + customer.getEmail());

		SessionDto sdt = new SessionDto();

		if (opt.isPresent())
			throw new CustomerException("account already exist with email : " + customer.getEmail());

		Customer cust = new Customer();
		cust.setAddress(customer.getAddress());
		cust.setEmail(customer.getEmail());
		cust.setMobile(customer.getMobile());
		cust.setName(customer.getName());
		cust.setPassword(customer.getPassword());

		cdao.save(cust);

		sdt.setAuthkey(RandomString.make(6));
		sdt.setSessionTime(LocalDateTime.now());

		CurrentUserSession cus = new CurrentUserSession();
		cus.setAuthKey(sdt.getAuthkey());
		cus.setEmail(customer.getEmail());
		cus.setSessionTime(sdt.getSessionTime());
		cus.setUserType(UserType.CUSTOMER);

		usdao.save(cus);

		return sdt;

	}

	@Override
	public SessionDto adminSignup(AdminSignupDto admin) {

		Optional<Admin> opt = adao.findByEmail(admin.getEmail());

		Optional<Customer> opt1 = cdao.findByEmail(admin.getEmail());

		if (opt1.isPresent())
			throw new AdminException("account already exist with email : " + admin.getEmail());

		SessionDto sdt = new SessionDto();

		if (opt.isPresent())
			throw new AdminException("account already exist with email : " + admin.getEmail());

		Admin adm = new Admin();

		adm.setAddress(admin.getAddress());
		adm.setCompanyName(admin.getCompanyName());
		adm.setEmail(admin.getEmail());
		adm.setMobile(admin.getMobile());
		adm.setName(admin.getName());
		adm.setPassword(admin.getPassword());

		adao.save(adm);

		sdt.setAuthkey(RandomString.make(6));
		sdt.setSessionTime(LocalDateTime.now());

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

		if (opt.isEmpty())
			throw new RuntimeException("User alresdy logged out!");

		CurrentUserSession cus = opt.get();

		usdao.delete(cus);

		return "Logged out...";

	}

	@Override
	public String deleteAccout(String authKey) throws CustomerException {

		CurrentUserSession cus = usdao.findByAuthKey(authKey)
				.orElseThrow(() -> new RuntimeException("User is not logged in!"));

		Customer customer = cdao.findByEmail(cus.getEmail())
				.orElseThrow(() -> new CustomerException("User does not exist!"));

		cdao.delete(customer);
		usdao.delete(cus);

		return "User deleted successfully...";

	}

}
