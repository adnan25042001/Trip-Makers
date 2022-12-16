package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
import com.masai.model.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer>{

	//Checking feedback by customerId.
	public abstract List<Feedback> findByCustomerId(Integer cid);
}
