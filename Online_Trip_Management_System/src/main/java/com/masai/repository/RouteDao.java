package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Route;

public interface RouteDao extends JpaRepository<Route, Integer> {

	public List<Route> findByPickupPoint(String pickup);

}
