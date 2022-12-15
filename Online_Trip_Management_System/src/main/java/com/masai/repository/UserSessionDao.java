package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurrentUserSession;

@Repository
public interface UserSessionDao extends JpaRepository<CurrentUserSession, Integer>{
	
	public Optional<CurrentUserSession> findByAuthKey(String authKey);

}
