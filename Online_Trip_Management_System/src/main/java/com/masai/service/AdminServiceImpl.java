package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FeedbackException;
import com.masai.exception.CustomerException;
import com.masai.model.CurrentUserSession;
import com.masai.model.CustomerDto;
import com.masai.model.Feedback;
import com.masai.model.UserType;
import com.masai.repository.CustomerDao;
import com.masai.repository.FeedbackDao;
import com.masai.repository.UserSessionDao;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private FeedbackDao fDao;

	@Autowired
	private UserSessionDao uSesDao;

	@Override
	public CustomerDto getCustomerByEmail(String email, String key) throws CustomerException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isPresent()) {
			CurrentUserSession anew = optCurrcustomer.get();

			if (anew.getUserType().equals(UserType.ADMIN)) {

				Optional<CustomerDto> cnew = cDao.getCustomerDtoByEmail(email);
				if (cnew.isPresent()) {
					CustomerDto cnewdto = cnew.get();
					return cnewdto;
				} else {
					throw new CustomerException("User not found with this email :" + email);
				}
			} else {
				throw new CustomerException("You are not an admin. Please log in as admin");
			}
		} else {
			throw new CustomerException("Invalid Admin Authentication key");
		}

	}

	@Override
	public List<CustomerDto> getAllCustomerDetails(String key) throws CustomerException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isPresent()) {
			CurrentUserSession anew = optCurrcustomer.get();

			if (anew.getUserType().equals(UserType.ADMIN)) {

				List<CustomerDto> cnew = cDao.getAllCustomerDto();
				if (cnew.size() > 0) {
					return cnew;
				} else {
					throw new CustomerException("No user found!!");
				}
			} else {
				throw new CustomerException("You are not an admin. Please log in as admin");
			}
		} else {
			throw new CustomerException("Invalid Auth Key");
		}

	}

	@Override
	public List<CustomerDto> getCustomerDetailsByAddress(String address, String key) throws CustomerException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isPresent()) {
			CurrentUserSession anew = optCurrcustomer.get();

			if (anew.getUserType().equals(UserType.ADMIN)) {

				List<CustomerDto> cnew = cDao.getCustomerDtoByAddress(address);
				if (cnew.size() > 0) {
					return cnew;
				} else {
					throw new CustomerException("No user found!!");
				}
			} else {
				throw new CustomerException("You are not an admin. Please log in as admin");
			}
		} else {
			throw new CustomerException("Invalid Auth Key");
		}

	}

	@Override
	public List<CustomerDto> getCustomerDetailsByName(String name, String key) throws CustomerException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isPresent()) {
			CurrentUserSession anew = optCurrcustomer.get();

			if (anew.getUserType().equals(UserType.ADMIN)) {

				List<CustomerDto> cnew = cDao.getCustomerDtoByName(name);
				if (cnew.size() > 0) {
					return cnew;
				} else {
					throw new CustomerException("No user found!!");
				}
			} else {
				throw new CustomerException("You are not an admin. Please log in as admin");
			}
		} else {
			throw new CustomerException("Invalid Auth Key");
		}
	}

	@Override
	public List<Feedback> getAllFeedbackByCustomerId(Integer id, String key)
			throws CustomerException, FeedbackException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isPresent()) {
			CurrentUserSession anew = optCurrcustomer.get();

			if (anew.getUserType().equals(UserType.ADMIN)) {

				List<Feedback> feedbacks = fDao.findByCustomerId(id);
				if (feedbacks.size() > 0) {
					return feedbacks;
				} else {
					throw new CustomerException("No feedback given!!");
				}
			} else {
				throw new CustomerException("You are not an admin. Please log in as admin");
			}
		} else {
			throw new CustomerException("Invalid Auth Key");
		}

	}

	@Override
	public Feedback getFeedbackById(Integer fid) throws FeedbackException {

		return fDao.findById(fid).orElseThrow(() -> new FeedbackException("Invalid feedback Id :" + fid));

	}

	@Override
	public List<Feedback> getAllFeedback(String key) throws FeedbackException {

		Optional<CurrentUserSession> optCurrcustomer = uSesDao.findByAuthKey(key);
		if (optCurrcustomer.isPresent()) {
			CurrentUserSession anew = optCurrcustomer.get();

			if (anew.getUserType().equals(UserType.ADMIN)) {

				List<Feedback> feedbacks = fDao.findAll();
				if (feedbacks.size() > 0) {
					return feedbacks;
				} else {
					throw new CustomerException("No feedback given!!");
				}
			} else {
				throw new CustomerException("You are not an admin. Please log in as admin");
			}
		} else {
			throw new CustomerException("Invalid Auth Key");
		}

	}

}
