package com.masai.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.app.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

}
